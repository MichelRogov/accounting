package com.school.project.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_TYPE")
@AllArgsConstructor
@NoArgsConstructor
public enum AccountType {
    TEACHER,
    ADMIN,
    STUDENT
}
