package com.infy.verizon_group_project.service;

import java.util.List;

import com.infy.verizon_group_project.model.Application;

public interface ApplicationService {
	public List<Application> getAllApplications() throws Exception;

	public Integer approveApplication(Integer aptNo) throws Exception;

	public String registerNewApp(Application app) throws Exception;
}
