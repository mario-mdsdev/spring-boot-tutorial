package com.mdsdev.springboot.tutorial.repository;

import com.mdsdev.springboot.tutorial.entity.Department;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DepartmentRepositoryTest {

    private final DepartmentRepository departmentRepository;
    private final TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        final Department department = Department.builder()
                .departmentName("Mechanical Engineering")
                .departmentCode("ME-011")
                .departmentAddress("Cheapest St.")
                .build();

        entityManager.persist(department);
    }

    @Test
    @DisplayName("Get department data by looking for id")
    void whenFindById_thenReturnDepartment() {
        final Department department = departmentRepository.findById(1l).get();

        assertThat(department, is(notNullValue()));
        assertThat(department.getDepartmentName(), is(equalTo("Mechanical Engineering")));
    }

}