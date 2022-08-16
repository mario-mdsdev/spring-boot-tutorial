package com.mdsdev.springboot.tutorial.service;

import com.mdsdev.springboot.tutorial.entity.Department;

import java.util.List;

public interface DepartmentService {

    public Department saveDepartment(Department department);
    public List<Department> fetchDepartmentList();
    public Department fetchDeparmentById(Long departmentId);
    public void deleteDepartmentById(Long departmentId);
    public Department updateDepartment(Long departmentId, Department department);

}
