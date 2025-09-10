package com.codeit.HRBank.dto.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime establishedDate;
    // private Long employeeCount; // 구현 전 컴파일 에러 방지 주석
}
