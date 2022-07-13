package com.prominentpixel.flywaydemo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String role;

    @Size(max = 50)
    private String project;
}
