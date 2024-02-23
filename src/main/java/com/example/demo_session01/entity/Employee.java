package com.example.demo_session01.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ma;

    @NotBlank (message = "Vui lòng nhập tên nhân viên")
    @Column(nullable = false)
    private String ten;

    @Column(nullable = false)
    private String diaChi;

    @Column(nullable = false)
    private String viTriCongViec;

    @Column(nullable = false)
    private int tuoi;

    @Column(nullable = false)
    private String email;

}