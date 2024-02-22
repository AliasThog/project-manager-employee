package com.example.demo_session01.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String ma;

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