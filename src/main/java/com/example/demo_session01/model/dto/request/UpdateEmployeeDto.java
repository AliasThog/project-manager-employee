package com.example.demo_session01.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeDto {

    @NotNull(message = "maCode is required")
    @NotBlank(message = "maCode is required")
    @NotEmpty(message = "ma cannot be empty")
    private String ma;

    @NotNull(message = "ten is required")
    @NotEmpty(message = "ten cannot be empty")
    private String ten;

    @NotNull(message = "diaChi cannot be null")
    @NotEmpty(message = "diaChi cannot be empty")
    @Column(nullable = false)
    private String diaChi;

    @NotNull(message = "viTriCongViec cannot be null")
    @NotEmpty(message = "viTriCongViec cannot be empty")
    @Column(nullable = false)
    private String viTriCongViec;

    @NotNull(message = "tuoi cannot be null")
    private int tuoi;

    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    private String email;
}
