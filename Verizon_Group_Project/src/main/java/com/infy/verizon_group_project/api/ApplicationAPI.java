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

import com.infy.verizon_group_project.model.Application;
import com.infy.verizon_group_project.service.ApplicationService;

@CrossOrigin
@RestController
@RequestMapping("ApplicationAPI")
public class ApplicationAPI {
	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private Environment environment;

	static Logger logger = LogManager.getLogger(ApartmentAPI.class.getName());

	@GetMapping(value = "approveApplication/{appId}")
	public ResponseEntity<String> approveApplication(@PathVariable("appId") Integer appId) {
		try {
			logger.info("Approving application, application Id: " + appId);

			applicationService.approveApplication(appId);
			String message = "Application approved successfully, application Id: " + appId;

			logger.info(message);
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

	@GetMapping(value = "allApp")
	public ResponseEntity<List<Application>> allApp() throws Exception {
		try {
			return new ResponseEntity<List<Application>>(applicationService.getAllApplications(), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}

	@PostMapping(value = "newApp")
	public ResponseEntity<String> newApp(@RequestBody Application app) throws Exception {
		try {
			String registerApp = applicationService.registerNewApp(app);
			return new ResponseEntity<String>(registerApp, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}

}
