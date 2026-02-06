package com.ahmetsamil.CompanySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahmetsamil.CompanySystem.Entity.Department;


@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long>{

}

