package com.mdsdev.springboot.tutorial.service;

import com.mdsdev.springboot.tutorial.entity.Department;
import com.mdsdev.springboot.tutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    void deleteDepartmentById(Long departmentId);
    Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;
    Department fetchDepartmentByName(String departmentName);
    List<Department> fetchDepartmentList();
    Department saveDepartment(Department department);
    Department updateDepartment(Long departmentId, Department department);

}
