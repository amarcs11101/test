/**
 * 
 */
package com.application.service;

import java.util.List;

import com.application.entity.EmployeeEntity;

/**
 * @author Abhishek Amar
 *
 */
public interface EmployeeService {
	EmployeeEntity updateEmployeeById(EmployeeEntity employee);

	List<EmployeeEntity> getAllEmployeeDetails(Integer limit, Integer offSet);
	
	EmployeeEntity findByPlaceAndEmployeeId(String place,Integer employeeId);
	
	List<EmployeeEntity> getAllEmployeeByCompetencies(String competencies);
}
