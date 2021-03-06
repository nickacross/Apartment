package com.infy.verizon_group_project.dao;

import java.util.List;

import com.infy.verizon_group_project.model.Apartment;

public interface ApartmentDAO {
	public Integer addApt(Apartment apt) throws Exception;

	public List<Apartment> getAllApts() throws Exception;

	public Apartment getAptByAptNo(Integer aptNo) throws Exception;

	public Integer modifyAvailability(Integer aptNo, Integer availability) throws Exception;

	public List<Apartment> getApts() throws Exception;
}
