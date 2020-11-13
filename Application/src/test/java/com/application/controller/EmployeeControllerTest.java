/**
 * 
 */
package com.application.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.application.entity.EmployeeEntity;
import com.application.service.EmployeeService;

/**
 * @author Abhishek Amar
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeServices;

	@Test
	public void testUpdateEmployeeDetailsById() {
		EmployeeEntity emp = new EmployeeEntity();
		emp.setTitle("abc");
		emp.setSupervisorId(1);
		EmployeeEntity empDetails = employeeServices.findByPlaceAndEmployeeId("abc", 1);
		Mockito.when(empDetails).thenReturn(emp);
	}
}
