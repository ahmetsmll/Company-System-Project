package com.ahmetsamil.CompanySystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmetsamil.CompanySystem.Entity.Department;
import com.ahmetsamil.CompanySystem.Service.DepartmentService;





@RestController
@RequestMapping("departments")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@GetMapping
	public List<Department> getDepartments(){
		return departmentService.getDepartments();
	}
	
	@GetMapping("/{id}")
	public Optional<Department> getADepartment(@PathVariable Long id){
		return departmentService.getADepartment(id);
	}
	@PostMapping("/save")
	public String saveDepartment(@RequestBody Department department) {
		departmentService.saveDepartment(department);
		return "Department Saved!";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		return "Department Deleted!";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
