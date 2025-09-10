package com.codeit.HRBank.service;

import com.codeit.HRBank.domain.Department;
import com.codeit.HRBank.dto.Department.DepartmentCreateRequest;
import com.codeit.HRBank.dto.Department.DepartmentDto;
import com.codeit.HRBank.dto.Department.DepartmentUpdateRequest;
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

    @Transactional
    public DepartmentDto create(DepartmentCreateRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("이미 존재하는 부서명 : " + request.getName());
        }
        Department savedDepartment = departmentRepository.save(
                Department.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .establishedDate(request.getEstablishedDate())
                        .build()
        );
        return toDto(savedDepartment);
    }

    @Transactional
    public DepartmentDto update(Long id, DepartmentUpdateRequest request) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("부서를 찾을 수 없음 : " + id)
        );

        if (!request.getName().equals(department.getName())) {
            if (!departmentRepository.existsByName(request.getName())) {
                throw new IllegalArgumentException("중복된 부서명 : " + request.getName());
            }
            department.setName(request.getName());
        }

        if (request.getDescription() != null) {
            department.setDescription(request.getDescription());
        }

        if (request.getEstablishedDate() != null) {
            department.setEstablishedDate(request.getEstablishedDate());
        }

        return toDto(department);
    }

    @Transactional
    public boolean delete(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        }
        return true;
    }

    private DepartmentDto toDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getEstablishedDate()
                // department.getEmployeeCount() // 구현 전 컴파일 에러 방지 주석
        );
    }

}
