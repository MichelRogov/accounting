package com.school.project.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_TYPE")
@AllArgsConstructor
@NoArgsConstructor
public enum AccountType {
    ;
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TYPE")
    private String type;
}
