package br.com.mdsdev.springboot.tutorial.service;

import br.com.mdsdev.springboot.tutorial.dto.DepartmentDTO;
import br.com.mdsdev.springboot.tutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    void deleteDepartmentById(Long departmentId);
    DepartmentDTO fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;
    DepartmentDTO fetchDepartmentByName(String departmentName);
    List<DepartmentDTO> fetchDepartmentList();
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO updateDepartment(Long departmentId,
                                   DepartmentDTO departmentDTO) throws DepartmentNotFoundException;

}
