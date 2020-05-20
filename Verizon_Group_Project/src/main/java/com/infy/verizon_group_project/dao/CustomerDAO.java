package com.infy.verizon_group_project.dao;

import com.infy.verizon_group_project.model.User;

public interface CustomerDAO {
	public Boolean checkAvailabilityOfEmailId(String email);
	public String registerNewCustomer(User customer);
	public String authenticateCustomer(String email, String password);
	public String getPasswordOfCustomer(String email) ;
	public User getCustomerByEmailId(String email);
	


}
