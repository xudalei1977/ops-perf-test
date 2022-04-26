package com.ops.perf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ops.perf.dao.CustomerDao;
import com.ops.perf.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

public class Test {

    public static Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        if (args.length < 3) {
            log.error("--Usage : java -jar ops-perf-test-1.0-SNAPSHOT.jar <trino/hive/impala> thread-num batch-num");
            return;
        }

        log.info("args [0]" + args[0]);

        ApplicationContext context = null;
        if(args[0].equals("hive"))
            context = new ClassPathXmlApplicationContext("spring-hive-jdbc-connect.xml");
        else if(args[0].equals("trino"))
            context = new ClassPathXmlApplicationContext("spring-trino-jdbc-connect.xml");
        else if(args[0].equals("impala"))
            context = new ClassPathXmlApplicationContext("spring-impala-jdbc-connect.xml");
        else if(args[0].equals("mysql"))
            context = new ClassPathXmlApplicationContext("spring-mysql-jdbc-connect.xml");
        else{
            log.error("--Usage : java -jar ops-perf-test-1.0-SNAPSHOT.jar <trino/hive/impala>");
            return;
        }

        CustomerDao customerDAO = (CustomerDao) context.getBean("customerSimpleDAO");
        
//        Customer customer = new Customer(1, "Dalei", 45);
//        customerDAO.insert(customer);

        for(int i = 0; i < Integer.parseInt(args[1]); i++) {
            Thread t1 = new Thread(new Task(customerDAO, Integer.parseInt(args[2])));
            t1.start();
        }
	}

    public static class Task implements Runnable {
        public static Logger log = LoggerFactory.getLogger(Task.class);
        private CustomerDao customerDAO;
        private int batchNum;

        public Task(CustomerDao _customerDAO, int _batchNum){
            this.customerDAO = _customerDAO;
            this.batchNum = _batchNum;
        }

        @Override
        public void run() {
            int count = 0;
            long beginTime = (new Date()).getTime();

            while(count++ <= batchNum){
                Customer customer1 = customerDAO.findByCustomerById(1);
                log.debug("*********** Thread := " + Thread.currentThread() + "***** customer1 := " + customer1);
            }

            long endTime = (new Date()).getTime();
            log.info("*********** Thread := " + Thread.currentThread() + "***** use time := " + (endTime - beginTime));
        }
    }

}
