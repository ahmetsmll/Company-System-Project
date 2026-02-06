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
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getAEmploye(Long id){
		return employeeRepository.findById(id);
	}
	
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}
	
	public void updateEmployee(Long id, Employee employee) {
		Employee tEmployee= employeeRepository.findById(id).get();
		tEmployee.setName(employee.getName());
		tEmployee.setSurname(employee.getSurname());
		tEmployee.setSalary(employee.getSalary());
		employeeRepository.save(tEmployee);
	}
	
	public List<Employee> getEmployeesByDepartment(Long departmentId){
		return employeeRepository.findByDepartmentId(departmentId);
	}
	
	public void changeEmployeeDepartment(Long employeeId, Long newDepartmentId) {
		
		Employee employee = employeeRepository.findById(employeeId).get();
		Department newDepartment = departmentRepository.findById(newDepartmentId).get();
		
		employee.setDepartment(newDepartment);
		
		employeeRepository.save(employee);
	}
	
	public List<Employee> getEmployeesNullDepartment(){
		List<Employee> allEmployees = employeeRepository.findAll();
		return allEmployees.stream()
			.filter(employee -> employee.getDepartment()==null)
			.toList();
		
	}
	
	public List<Employee> getEmployeesByFilterSalary(Double salary){
	
		List<Employee> allEmployees=employeeRepository.findAll();
		
		return allEmployees.stream()
				.filter(employee -> employee.getSalary()>= salary)
				.toList();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

