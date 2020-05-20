package com.infy.verizon_group_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.verizon_group_project.dao.ApartmentDAO;
import com.infy.verizon_group_project.model.Apartment;
import com.infy.verizon_group_project.validator.ApartmentValidator;

@Service(value = "apartmentService")
@Transactional
public class ApartmentServiceImpl implements ApartmentService {
	@Autowired
	ApartmentDAO apartmentDAO;

	@Override
	public Integer addApt(Apartment apt) throws Exception {
		ApartmentValidator.validateApt(apt);
		return apartmentDAO.addApt(apt);
	}

	@Override
	public List<Apartment> getAllApts() throws Exception {
		return apartmentDAO.getAllApts();
	}

	@Override
	public Apartment getAptByAptNo(Integer aptNo) throws Exception {
		Apartment apt = apartmentDAO.getAptByAptNo(aptNo);

		if (apt == null)
			throw new Exception("ApartmentService.APARTMENT_DOES_NOT_EXIST");

		ApartmentValidator.validateApt(apt);
		return apt;
	}

	@Override
	public Integer modifyAvailability(Integer aptNo, Integer availability) throws Exception {
		if (availability == null)
			throw new Exception("ApartmentService.NULL_AVAILABILITY");
		return apartmentDAO.modifyAvailability(aptNo, availability);
	}

	@Override
	public List<Apartment> getApts() throws Exception {
		return apartmentDAO.getApts();
	}
}
