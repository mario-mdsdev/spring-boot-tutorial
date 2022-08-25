package com.mdsdev.springboot.tutorial.service;

import com.mdsdev.springboot.tutorial.entity.Department;
import com.mdsdev.springboot.tutorial.error.DepartmentNotFoundException;
import com.mdsdev.springboot.tutorial.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        final Optional<Department> department = departmentRepository.findById(departmentId);
        if (!department.isPresent()) {
            throw new DepartmentNotFoundException("Department not available.");
        }
        return department.get();
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
        var hasChanges = false;

        if (Objects.nonNull(department.getDepartmentName())
                && StringUtils.isNotBlank(department.getDepartmentName())) {
            depDB.setDepartmentName(department.getDepartmentName());
            hasChanges = true;
        }

        if (Objects.nonNull(department.getDepartmentAddress())
                && StringUtils.isNotBlank(department.getDepartmentAddress())) {
            depDB.setDepartmentAddress(department.getDepartmentAddress());
            hasChanges = true;
        }

        if (Objects.nonNull(department.getDepartmentCode())
                && StringUtils.isNotBlank(department.getDepartmentCode())) {
            depDB.setDepartmentCode(department.getDepartmentCode());
            hasChanges = true;
        }

        if (hasChanges) departmentRepository.save(depDB);

        return depDB;
    }

}
