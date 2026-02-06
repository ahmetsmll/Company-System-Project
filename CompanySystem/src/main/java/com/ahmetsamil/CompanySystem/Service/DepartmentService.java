package com.ahmetsamil.CompanySystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmetsamil.CompanySystem.Entity.Department;
import com.ahmetsamil.CompanySystem.Entity.Employee;
import com.ahmetsamil.CompanySystem.Repository.DepartmentRepository;
import com.ahmetsamil.CompanySystem.Repository.EmployeeRepository;




@Service
public class DepartmentService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	

    DepartmentService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	public List<Department> getDepartments(){
		return departmentRepository.findAll();
	}
	
	public Optional<Department> getADepartment(Long id){
		return departmentRepository.findById(id);
	}

	public void saveDepartment(Department department) {
		departmentRepository.save(department);
	}
	
	public void deleteDepartment(Long id) {
		
		Department department=departmentRepository.findById(id).get();
		
		List<Employee> employees = department.getEmployees();
		
		for( Employee employee : employees) {
			employee.setDepartment(null);
		}
		employeeRepository.saveAll(employees);
		
		departmentRepository.delete(department);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

