package com.ops.perf.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ops.perf.Test;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import com.ops.perf.dao.CustomerDao;
import com.ops.perf.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//if using hive, use the table.column as column name, e.g. customer.id instead of id
public class SimpleJdbcCustomerDAO extends NamedParameterJdbcDaoSupport implements CustomerDao{

	public static Logger log = LoggerFactory.getLogger(Test.class);

	@Override
	public void insert(Customer customer) {
//		String sql = "UPDATE CUSTOMER SET NAME = :name WHERE ID = :id";
		String sql = "insert into customer (id, name, age) values (:id, :name, :age)";

		SqlParameterSource ps = new BeanPropertySqlParameterSource(customer);
		int i = getNamedParameterJdbcTemplate().update(sql, ps);
		log.debug("***** affected row " + i);
	}
	
	@Override
	public Customer findByCustomerById(int id) {
		String sql = "select * from customer where id = :id";
		
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		
//		List<Map<String, Object>> list = getNamedParameterJdbcTemplate().queryForList("select * from default.customer", map);
//		System.out.println( list.toString() );
		
		Map<String, Object>  customerMap =  getNamedParameterJdbcTemplate().queryForMap(sql, map);

		Set<String> keys = customerMap.keySet();
		for(String key:keys){
			log.debug("****** key := " + key + " value := " + customerMap.get(key));
		}

		Customer customer = new Customer(
				Integer.parseInt(customerMap.get("id").toString()),
				customerMap.get("name").toString(),
				Integer.valueOf(customerMap.get("age").toString()));

		return customer;
	}
}
