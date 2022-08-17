package com.mdsdev.springboot.tutorial.service;

import com.mdsdev.springboot.tutorial.entity.Department;

import java.util.List;

public interface DepartmentService {

    public void deleteDepartmentById(Long departmentId);
    public Department fetchDepartmentById(Long departmentId);
    public Department fetchDepartmentByName(String departmentName);
    public List<Department> fetchDepartmentList();
    public Department saveDepartment(Department department);
    public Department updateDepartment(Long departmentId, Department department);

}
