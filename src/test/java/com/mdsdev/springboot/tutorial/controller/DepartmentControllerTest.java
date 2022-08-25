package com.mdsdev.springboot.tutorial.controller;

import com.mdsdev.springboot.tutorial.entity.Department;
import com.mdsdev.springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Finest St.")
                .departmentCode("IT-06")
                .build();
    }

    @Test
    @DisplayName("Should save a new department")
    void shouldSaveANewDepartment() throws Exception {
        final Department inputDepartment = Department.builder()
                .departmentName("IT")
                .departmentAddress("Finest St.")
                .departmentCode("IT-06")
                .build();

        when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\": \"IT\",\n" +
                        "\t\"departmentAddress\": \"Finest St.\",\n" +
                        "\t\"departmentCode\": \"IT-06\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should fetch a department by id")
    void shouldFetchADepartmentById() throws Exception {
        when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get("/departments/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }

}
