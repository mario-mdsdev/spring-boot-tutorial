package com.mdsdev.springboot.tutorial.service;

import com.mdsdev.springboot.tutorial.entity.Department;
import com.mdsdev.springboot.tutorial.repository.DepartmentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        final Department depDB = departmentRepository.findById(departmentId).get();
        boolean hasChanges = false;

        if (Objects.nonNull(department.getDepartmentName()) && StringUtils.isNotBlank(department.getDepartmentName())) {
            depDB.setDepartmentName(department.getDepartmentName());
            hasChanges = true;
        }

        if (Objects.nonNull(department.getDepartmentAddress()) && StringUtils.isNotBlank(department.getDepartmentAddress())) {
            depDB.setDepartmentAddress(department.getDepartmentAddress());
            hasChanges = true;
        }

        if (Objects.nonNull(department.getDepartmentCode()) && StringUtils.isNotBlank(department.getDepartmentCode())) {
            depDB.setDepartmentCode(department.getDepartmentCode());
            hasChanges = true;
        }

        if (hasChanges) departmentRepository.save(depDB);

        return depDB;
    }

}
