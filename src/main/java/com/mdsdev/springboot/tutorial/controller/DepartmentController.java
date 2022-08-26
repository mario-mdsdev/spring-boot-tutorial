package com.mdsdev.springboot.tutorial.controller;

import com.mdsdev.springboot.tutorial.dto.DepartmentDTO;
import com.mdsdev.springboot.tutorial.error.DepartmentNotFoundException;
import com.mdsdev.springboot.tutorial.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final DepartmentService departmentService;

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted successfully!";
    }

    @GetMapping("/departments/{id}")
    public DepartmentDTO fetchDepartmentById(@PathVariable("id") Long departmentId)
            throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @GetMapping("/departments/name/{name}")
    public DepartmentDTO fetchDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.fetchDepartmentByName(departmentName);
    }

    @GetMapping("/departments")
    public List<DepartmentDTO> fetchDepartmentList() {
        return departmentService.fetchDepartmentList();
    }

    @PostMapping("/departments")
    public DepartmentDTO saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.saveDepartment(departmentDTO);
    }

    @PutMapping("/departments/{id}")
    public DepartmentDTO updateDepartment(@PathVariable("id") Long departmentId,
                                       @Valid @RequestBody DepartmentDTO departmentDTO)
            throws DepartmentNotFoundException {
        return departmentService.updateDepartment(departmentId, departmentDTO);
    }

}
