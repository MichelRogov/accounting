package com.school.project.model;

import lombok.Builder;
import lombok.Data;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "USER_ACCOUNT")
public class UserAccount {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @CreationTimestamp
    @Column(name = "REGISTRATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "ACCOUNT_ROLE")
    @Enumerated(EnumType.STRING)
    private AccountType accountRole;


}