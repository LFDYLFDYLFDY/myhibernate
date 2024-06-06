package ru.lfdy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "info_id")
    EmployeeInfo info;


}
