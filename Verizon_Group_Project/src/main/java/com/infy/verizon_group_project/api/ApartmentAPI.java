package com.infy.verizon_group_project.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.verizon_group_project.model.Apartment;
import com.infy.verizon_group_project.service.ApartmentService;

@CrossOrigin
@RestController
@RequestMapping("ApartmentAPI")
public class ApartmentAPI {
	@Autowired
	private ApartmentService apartmentService;

	@Autowired
	private Environment environment;

	static Logger logger = LogManager.getLogger(ApartmentAPI.class.getName());

	@PostMapping(value = "addApartment")
	public ResponseEntity<String> addApartment(@RequestBody Apartment apt) throws Exception {
		try {
			System.out.println(apt.getAptType());
			System.out.println(apt.getNoOfBaths());
			System.out.println(apt.getNoOfRooms());
			logger.info("Adding apartment, apartment type: " + apt.getAptType() + ", apartment flooring: "
					+ apt.getTypeOfFlooring() + ", apartment level: " + apt.getAptLevel());

			Integer result = apartmentService.addApt(apt);
			String message = environment.getProperty("ApartmentAPI.APARTMENT_ADDED_SUCCESS") + result;

			logger.info(message);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {
			if (e.getMessage().contains("Validator"))
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

	@GetMapping(value = "getAllApts")
	public ResponseEntity<List<Apartment>> getAllApts() {
		try {
			logger.info("Getting all apartments");

			List<Apartment> aList = apartmentService.getAllApts();

			logger.info(environment.getProperty("ApartmentAPI.GET_ALL_APARTMENTS_SUCCESS"));
			return new ResponseEntity<List<Apartment>>(aList, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

	@GetMapping(value = "getAptByAptNo/{aptNo}")
	public ResponseEntity<Apartment> getAptByAptNo(@PathVariable("aptNo") Integer aptNo) {
		try {
			logger.info("Obtaining apartment information, apartment number: " + aptNo);

			Apartment a = apartmentService.getAptByAptNo(aptNo);

			logger.info("Aparment information obtained, apartment number: " + a.getAptNo() + ", apartment type: "
					+ a.getAptType());

			return new ResponseEntity<Apartment>(a, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

	@GetMapping(value = "modifyAptAvailability/{aptNo}/{availability}")
	public ResponseEntity<String> checkAptAvailability(@PathVariable("aptNo") Integer aptNo,
			@PathVariable("availability") Integer availability) throws Exception {
		try {
			logger.info("Modifing apartment availability, apartment number: " + aptNo);

			Integer result = apartmentService.modifyAvailability(aptNo, availability);
			String rStr = (result == 0) ? "unavailable" : "available";
			String message = "Apartment availability modified successfully, apartment number: " + aptNo
					+ ", apartment availability: " + rStr;

			logger.info(message);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {
			if (e.getMessage().contains("Validator"))
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, environment.getProperty(e.getMessage()));
		}
	}

	/*
	 * http://localhost:8080/Verizon_Server/ApartmentAPI/getApts Returns All
	 * Available Apartments
	 */
	@PostMapping(value = "getApts")
	public ResponseEntity<List<Apartment>> getApts() {
		try {
			return new ResponseEntity<List<Apartment>>(apartmentService.getApts(), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

}
