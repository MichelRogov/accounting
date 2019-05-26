package com.school.project.model;

import lombok.Data;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import sun.security.util.Password;

import javax.persistence.*;
import javax.validation.constraints.Email;

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

    @javax.validation.constraints.Email
    private String Email;

    private String Password;
}
