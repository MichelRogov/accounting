package com.school.project.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

@Table(name = "ACCOUNT_TYPE")
public enum AccountType {
    TEACHER,
    ADMIN,
    STUDENT;

  
}
