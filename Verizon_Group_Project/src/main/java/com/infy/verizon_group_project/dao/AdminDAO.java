package com.infy.verizon_group_project.dao;

import com.infy.verizon_group_project.model.User;

public interface AdminDAO {

	public String getPasswordOfAdmin(String email);
	public User getAdminByEmailId(String email) throws Exception;
	public Boolean checkAvailabilityOfEmailId(String email) ;
	public String registerNewAdmin(User admin);
}
