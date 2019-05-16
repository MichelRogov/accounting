package com.school.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="USER")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue()
    private Long id;

    @Column(name = "NAME")
    private String name;
}
