package com.mdsdev.springboot.tutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepartmentDTO {

    private Long departmentId;

    @NotBlank(message = "Department name can't be null.")
    private String departmentName;

    private String departmentAddress;
    private String departmentCode;

}
