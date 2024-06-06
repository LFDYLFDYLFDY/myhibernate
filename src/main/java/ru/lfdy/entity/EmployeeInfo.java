package ru.lfdy.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="employees_info")
@Data
public class EmployeeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne (mappedBy = "info")
    Employee employee;


}