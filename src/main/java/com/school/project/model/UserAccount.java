package com.school.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private User userId;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    private Date registrationDate;

    @Enumerated
    @Column(name = "ACCOUNT_ROLE", nullable = false)
    private AccountType accountRole;


}
