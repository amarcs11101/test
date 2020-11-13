/**
 * 
 */
package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.entity.EmployeeEntity;

/**
 * @author Abhishek Amar
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	List<EmployeeEntity> findAll();

	@Query(value = "select * from employee e  order by e.employeeId desc offset ?1 limit ?2 ", nativeQuery = true)
	List<EmployeeEntity> findAllByLimitAndOffSet(Integer offSet, Integer limit);

	EmployeeEntity findEmployeeById(Integer id);

    EmployeeEntity findByPlaceAndEmployeeId(String place,Integer employeeId);
    
    List<EmployeeEntity> findByCompetencies(String competencies);

}
