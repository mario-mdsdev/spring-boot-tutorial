package br.com.mdsdev.springboot.tutorial.service;

import br.com.mdsdev.springboot.tutorial.dto.DepartmentDTO;
import br.com.mdsdev.springboot.tutorial.entity.Department;
import br.com.mdsdev.springboot.tutorial.error.DepartmentNotFoundException;
import br.com.mdsdev.springboot.tutorial.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public DepartmentDTO fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        final Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not available.");
        }
        return mapper.map(department.get(), DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO fetchDepartmentByName(String departmentName) {
        final var department = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        return mapper.map(department, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> fetchDepartmentList() {
        final List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> mapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        final var department = Department.builder()
                .departmentName(departmentDTO.getDepartmentName())
                .departmentAddress(departmentDTO.getDepartmentAddress())
                .departmentCode(departmentDTO.getDepartmentCode())
                .build();
        departmentRepository.save(department);
        return mapper.map(department, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId,
                                          DepartmentDTO departmentDTO) throws DepartmentNotFoundException {
        if (departmentId == null) throw new DepartmentNotFoundException("Invalid department data.");

        final Department depDB = departmentRepository.findById(departmentId).orElseThrow();
        var hasChanges = false;

        final String departmentName = departmentDTO.getDepartmentName();
        if (Objects.nonNull(departmentName)
                && StringUtils.isNotBlank(departmentName)
                && !departmentName.equalsIgnoreCase(depDB.getDepartmentName()))
        {
            depDB.setDepartmentName(departmentName);
            hasChanges = true;
        }

        final String departmentAddress = departmentDTO.getDepartmentAddress();
        if (Objects.nonNull(departmentAddress)
                && StringUtils.isNotBlank(departmentAddress)
                && !departmentAddress.equalsIgnoreCase(depDB.getDepartmentAddress()))
        {
            depDB.setDepartmentAddress(departmentAddress);
            hasChanges = true;
        }

        final String departmentCode = departmentDTO.getDepartmentCode();
        if (Objects.nonNull(departmentCode)
                && StringUtils.isNotBlank(departmentCode)
                && !departmentCode.equalsIgnoreCase(depDB.getDepartmentCode()))
        {
            depDB.setDepartmentCode(departmentCode);
            hasChanges = true;
        }

        if (hasChanges) {
            departmentRepository.save(depDB);
            return mapper.map(depDB, DepartmentDTO.class);
        } else {
            return departmentDTO;
        }
    }

}
