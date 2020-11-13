/**
 * 
 */
package com.application.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Abhishek Amar
 *
 */
/* instead of setter getters lambok can be used */
@Entity
@Table(name="account")
public class Account implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accountId;
	@OneToOne
	private EmployeeEntity employeeId;
	private Double salary;
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public EmployeeEntity getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(EmployeeEntity employeeId) {
		this.employeeId = employeeId;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", employeeId=" + employeeId + ", salary=" + salary + "]";
	}
	
}
