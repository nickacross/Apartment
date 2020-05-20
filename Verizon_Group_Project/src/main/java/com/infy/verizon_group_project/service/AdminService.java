package com.infy.verizon_group_project.service;

import com.infy.verizon_group_project.model.User;

public interface AdminService {

	public String registerNewAdmin(User admin) throws Exception;

	public User authenticateAdmin(String email, String password) throws Exception;
}
