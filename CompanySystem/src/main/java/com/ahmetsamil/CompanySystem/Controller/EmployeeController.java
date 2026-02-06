package com.ahmetsamil.CompanySystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmetsamil.CompanySystem.Entity.Employee;
import com.ahmetsamil.CompanySystem.Service.EmployeeService;


@RestController
@RequestMapping("employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@GetMapping("{id}")
	public Optional<Employee> getAEmployee(@PathVariable Long id){
		return employeeService.getAEmploye(id);
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return "Employee Saved!";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return "Employee Deleted!";
	}
	
	@PostMapping("update/{id}")
	public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employeeService.updateEmployee(id, employee);
		return "Employee Updated!";
	}
	
	@GetMapping("/department/{departmentId}")
	public List<Employee> getEmployeesByDepartment(@PathVariable Long departmentId){
		
		return employeeService.getEmployeesByDepartment(departmentId);
	}
	
	@PutMapping("/{employeeId}/changeDepartment/{departmentId}")
	public String changeEmployeeDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId) {
		employeeService.changeEmployeeDepartment(employeeId, departmentId);
		return "Employee's Department Changed!";
	}
	
	@GetMapping("/nullDepartment")
	public List<Employee> getEmployeesNullDepartment(){
		return employeeService.getEmployeesNullDepartment();
	}
	

	@GetMapping("/salaryFilter/{salary}")
	public List<Employee> getEmployeesByFilterSalary(@PathVariable Double salary){
		return employeeService.getEmployeesByFilterSalary(salary);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
