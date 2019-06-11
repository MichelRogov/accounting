package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MODULE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Module {

    @Id
    @Column(name = "MODULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "HOURS",nullable = false)
    private Integer hours;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinTable(name ="SUBJECT_MODULE",joinColumns = {@JoinColumn(name = "MODULE_ID")},inverseJoinColumns = {@JoinColumn(name="SUBJECT_ID")})
    private List<Subject> subjects;

    @Column(name = "PRICE")
    private Double price;

}
