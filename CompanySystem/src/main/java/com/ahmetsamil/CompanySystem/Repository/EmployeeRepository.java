package com.ahmetsamil.CompanySystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahmetsamil.CompanySystem.Entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findByDepartmentId(Long departmentId);
}

