package com.ops.perf.dao.impl;

import java.util.Map;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ops.perf.dao.CustomerDao;
import com.ops.perf.model.Customer;

public class JdbcCustomerDAO extends JdbcDaoSupport implements CustomerDao {

	@Override
	public void insert(Customer customer) {
		String sql = "insert into customer (id, name, age) values (?, ?, ?)";

		getJdbcTemplate().update( sql, new Object[] { customer.getId(), customer.getName(),
						customer.getAge() });
	}

	@Override
	public Customer findCustomerById(int id) {
		String sql = "select * from customer where id = ?";
		Map<String, Object>  customerMap =  getJdbcTemplate().queryForMap(sql, id);
		
		Customer customer = new Customer( 
				Integer.parseInt(customerMap.get("id").toString()),
				customerMap.get("name").toString(),
				Integer.valueOf(customerMap.get("age").toString()));
		return customer;
	}
}
