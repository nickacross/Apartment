package com.infy.verizon_group_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.infy.verizon_group_project.dao.AdminDAO;
import com.infy.verizon_group_project.model.User;
import com.infy.verizon_group_project.utility.HashingUtility;
import com.infy.verizon_group_project.validator.AdminValidator;

@Service(value = "adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;

	public String registerNewAdmin(User admin) throws Exception {
		String registeredWithEmailId = null;

		AdminValidator.validateAdminForRegistration(admin);

		Boolean available = adminDAO.checkAvailabilityOfEmailId(admin.getEmail().toLowerCase());

		if (available) {

			String emailIdToDB = admin.getEmail().toLowerCase();
			String passwordToDB = HashingUtility.getHashValue(admin.getPassword());

			admin.setEmail(emailIdToDB);
			admin.setPassword(passwordToDB);

			registeredWithEmailId = adminDAO.registerNewAdmin(admin);

		}

		else {
			throw new Exception("AdminService.EMAIL_ID_ALREADY_IN_USE");
		}

		return registeredWithEmailId;

	}

	@Override
	public User authenticateAdmin(String email, String password) throws Exception {

		User admin = null;
		email = email.toLowerCase();

		AdminValidator.validateAdminForLogin(email, password);

		String passwordFromDB = adminDAO.getPasswordOfAdmin(email);
		if (passwordFromDB != null) {
			String hashedPassword = HashingUtility.getHashValue(password);
			if (hashedPassword.equals(passwordFromDB)) {
				admin = adminDAO.getAdminByEmailId(email);
			} else
				throw new Exception("AdminService.INVALID_CREDENTIALS");
		} else
			throw new Exception("AdminService.INVALID_CREDENTIALS");

		return admin;
	}

}
