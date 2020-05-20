package com.infy.verizon_group_project.service;

import com.infy.verizon_group_project.model.User;

public interface CustomerService {

	public User authenticateCustomer(String email, String password) throws Exception;

	public String registerNewCustomer(User customer) throws Exception;

}
