/**
 * 
 */
package com.application.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.entity.EmployeeEntity;
import com.application.repository.EmployeeRepository;
import com.application.service.EmployeeService;

/**
 * @author Abhishek Amar
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public EmployeeEntity updateEmployeeById(EmployeeEntity playerEntity) {
		return employeeRepo.save(playerEntity);
	}

	@Override
	public List<EmployeeEntity> getAllEmployeeDetails(Integer offSet, Integer limit) {
		return employeeRepo.findAllByLimitAndOffSet(offSet, limit);
	}

	@Override
	public EmployeeEntity findByPlaceAndEmployeeId(String place, Integer employeeId) {
		return employeeRepo.findByPlaceAndEmployeeId(place, employeeId);
	}

	@Override
	public List<EmployeeEntity> getAllEmployeeByCompetencies(String competencies) {
		 
		return employeeRepo.findByCompetencies(competencies);
	}

}
