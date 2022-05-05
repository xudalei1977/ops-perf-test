package com.ops.perf.dao;

import com.ops.perf.model.Customer;

public interface CustomerDao {

	public void insert(Customer customer);
	public Customer findCustomerById(int id);
	
}
