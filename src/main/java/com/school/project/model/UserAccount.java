package com.school.project.model;

import lombok.Builder;
import lombok.Data;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID",nullable = false,unique = true)
    private User userId;

    @CreationTimestamp
    @Column(name = "REGISTRATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "ACCOUNT_ROLE")
    @Enumerated(EnumType.STRING)
    private AccountType accountRole;


}
