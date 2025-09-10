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
public class DepartmentCreateRequest {
        private String name;
        private String description;
        private LocalDateTime establishedDate;
}
