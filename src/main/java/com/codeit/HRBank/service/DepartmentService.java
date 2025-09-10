package com.codeit.HRBank.service;

import com.codeit.HRBank.domain.Department;
import com.codeit.HRBank.dto.request.DepartmentCreateRequest;
import com.codeit.HRBank.dto.data.DepartmentDto;
import com.codeit.HRBank.dto.request.DepartmentUpdateRequest;
import com.codeit.HRBank.mapper.DepartmentMapper;
import com.codeit.HRBank.repository.DepartmentRepository;
import com.codeit.HRBank.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper departmentMapper;

    @Transactional
    public DepartmentDto create(DepartmentCreateRequest request) {
        if (departmentRepository.existsByName(request.name())) {
            throw new IllegalArgumentException("이미 존재하는 부서명 : " + request.name());
        }
        Department savedDepartment = departmentRepository.save(
                Department.builder()
                        .name(request.name())
                        .description(request.description())
                        .establishedDate(request.establishedDate())
                        .build()
        );
        return departmentMapper.toDto(savedDepartment);
    }

    @Transactional
    public DepartmentDto update(Long id, DepartmentUpdateRequest request) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("부서를 찾을 수 없음 : " + id)
        );

        if (!request.name().equals(department.getName())) {
            if (departmentRepository.existsByName(request.name())) {
                throw new IllegalArgumentException("중복된 부서명 : " + request.name());
            }
            department.setName(request.name());
        }

        if (request.description() != null) {
            department.setDescription(request.description());
        }

        if (request.establishedDate() != null) {
            department.setEstablishedDate(request.establishedDate());
        }

        return departmentMapper.toDto(department);
    }

    @Transactional
    public boolean delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            return false;
        }
        departmentRepository.deleteById(id);
        return true;
    }

}
