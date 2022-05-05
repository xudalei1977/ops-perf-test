package com.ops.perf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ops.perf.dao.WebPageDao;
import com.ops.perf.model.WebPage;
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
        else if(args[0].equals("spark"))
            context = new ClassPathXmlApplicationContext("spring-spark-jdbc-connect.xml");
        else if(args[0].equals("mysql"))
            context = new ClassPathXmlApplicationContext("spring-mysql-jdbc-connect.xml");
        else{
            log.error("--Usage : java -jar ops-perf-test-1.0-SNAPSHOT-jar-with-dependencies.jar <trino/hive/impala>");
            return;
        }

        WebPageDao webPageDAO = (WebPageDao) context.getBean("webPageSimpleDAO");
        
        WebPage webPage = new WebPage(2,
                "AAAAAAAACAAAAAAA",
                "1997-09-03",
                "2000-09-02",
                2450809,
                2452620,
                "N",
                0,
                "http://www.foo.com",
                "dynamic",
                3588,
                22,
                5,
                0,       "default", "default");
//        webPageDAO.insert(webPage);

        for(int i = 0; i < Integer.parseInt(args[1]); i++) {
            Thread t1 = new Thread(new Task(webPageDAO, Integer.parseInt(args[2])));
            t1.start();
        }
	}

    public static class Task implements Runnable {
        public static Logger log = LoggerFactory.getLogger(Task.class);
        private WebPageDao webPageDAO;
        private int batchNum;

        public Task(WebPageDao _webPageDAO, int _batchNum){
            this.webPageDAO = _webPageDAO;
            this.batchNum = _batchNum;
        }

        @Override
        public void run() {
            int count = 0;
            long beginTime = (new Date()).getTime();

            while(count++ <= batchNum){
                WebPage webPage1 = webPageDAO.findWebPageBySK(2);
                log.debug("*********** Thread := " + Thread.currentThread() + "***** webPage := " + webPage1);
            }

            long endTime = (new Date()).getTime();
            log.info("*********** Thread := " + Thread.currentThread() + "***** use time := " + (endTime - beginTime));
        }
    }

}
