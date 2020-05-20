package com.infy.verizon_group_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.verizon_group_project.dao.ApplicationDAO;
import com.infy.verizon_group_project.model.Application;

@Service(value = "applicationService")
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	ApplicationDAO applicationDAO;

	@Override
	public List<Application> getAllApplications() throws Exception {
		List<Application> aList = applicationDAO.getAllApplications();
		if (aList == null)
			throw new Exception("ApplicationService.NULL_APPLICATION_LIST");
		return aList;
	}

	@Override
	public Integer approveApplication(Integer appId) throws Exception {
		Integer result = applicationDAO.approveApplication(appId);
		if (result == 0)
			throw new Exception("ApplicationService.APPLICATION_APPROVED");
		return result;
	}

	@Override
	public String registerNewApp(Application app) throws Exception {
		String add = applicationDAO.registerNewApp(app);
		return add;
	}
}
