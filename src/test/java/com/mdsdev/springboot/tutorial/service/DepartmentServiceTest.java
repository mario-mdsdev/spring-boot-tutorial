package com.mdsdev.springboot.tutorial.service;

import com.mdsdev.springboot.tutorial.dto.DepartmentDTO;
import com.mdsdev.springboot.tutorial.entity.Department;
import com.mdsdev.springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        final Department department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Finest St.")
                .departmentCode("IT-06")
                .build();

        when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);
    }

    @Test
    @DisplayName("Get the department data based on a valid department name")
    void whenValidDepartmentName_thenDepartmentShouldBeFound() {
        final String departmentName = "IT";
        final DepartmentDTO departmentFound = departmentService.fetchDepartmentByName(departmentName);

        assertThat(departmentFound, is(notNullValue())                              );
        assertThat(departmentName , is(equalTo(departmentFound.getDepartmentName())));
    }

}