package com.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.entity.EmployeeEntity;
import com.application.exceptionhandler.EmployeeNotFoundException;
import com.application.response.ApiResponse;
import com.application.service.EmployeeService;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	private Logger logger = LogManager.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeServices;

	@Value("${id.not.found}")
	private String idNotFound;

	@Value("${employee.not.found}")
	private String employeeNotFound;

	@Value("${employee.updated.message}")
	private String employeeUpdated;
	
	@Value("${success.message}")
	private String successMessage;

	/**
	 * 
	 * @param place
	 * @param percentage
	 * @param employeeEntity
	 * @return
	 */
	@PutMapping("/place/{place}/salary/{percentage}")
	@ApiOperation(value = "Save vehicle details", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated employee details"),
			@ApiResponse(code = 401, message = "Unauthorised Access"), @ApiResponse(code = 403, message = "forbidden"),
			@ApiResponse(code = 404, message = "resource not found") })
	public ResponseEntity<Object> updateEmployeeDetailsById(@PathVariable("place") String place,
			@PathVariable("percentage") @Max(55) double percentage, @RequestBody EmployeeEntity employeeEntity) {
		ApiResponse response = null;
		EmployeeEntity emp = null;
		try {
			logger.info(":: /api/v1/employee :: inside updateEmployeeDetailsById :: " + employeeEntity);
			EmployeeEntity empDetails = employeeServices.findByPlaceAndEmployeeId(place,
					employeeEntity.getEmployeeId());
			if (empDetails != null) {
				emp = employeeServices.updateEmployeeById(employeeEntity);
				response = new ApiResponse(HttpStatus.OK, employeeUpdated, emp);
			} else {
				logger.error(":: /api/vi/employee :: inside updateEmployeeDetailsById :: exceptions ::");
				throw new EmployeeNotFoundException(idNotFound);
			}
		} catch (Exception e) {
			logger.error(":: /api/vi/employee :: inside updateEmployeeDetailsById :: exceptions ::", e);
			throw new EmployeeNotFoundException(idNotFound);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * @param limit
	 * @param offSet
	 * @return
	 */
	@GetMapping("/page")
	public ResponseEntity<Object> getAllEmployeeWithPagination(
			@RequestParam(value = "limit", defaultValue = "5") Integer limit,
			@RequestParam(value = "offSet", defaultValue = "0") Integer offSet) {
		ApiResponse response = null;
		List<EmployeeEntity> employeeList = employeeServices.getAllEmployeeDetails(offSet * limit, limit);
		if (employeeList.isEmpty() && employeeList == null) {
			logger.error(":: /api/employee :: inside getAllEmployeeWithPagination :: exceptions ::");
			throw new EmployeeNotFoundException(employeeNotFound);
		} else {
			logger.info(":: /api/employee :: inside getAllEmployeeWithPagination ::offset " + offSet + " and limit "
					+ limit);
			response = new ApiResponse(HttpStatus.OK, successMessage, employeeList);
			response.setLimit(limit);
			response.setOffSet(offSet);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @param competencies
	 * @return
	 */

	@GetMapping("/range/{competency}")
	public ResponseEntity<Object> getEmployeeDetailsByCompetecies(
			@PathVariable("competency") @NotNull String competencies) {
		ApiResponse response = null;
		List<EmployeeEntity> employeeList = employeeServices.getAllEmployeeByCompetencies(competencies);
		if (employeeList.isEmpty() && employeeList == null) {
			logger.error(":: /api/employee :: inside getAllEmployeeWithPagination :: exceptions ::");
			throw new EmployeeNotFoundException(employeeNotFound);
		} else {
			List<Double> salaryList = employeeList.stream().map(a -> a.getAccount().getSalary())
					.collect(Collectors.toList());
			return new ResponseEntity<>(salaryList, HttpStatus.OK);
		}

	}
}
