package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SUBJECT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {

    @Id
    @Column(name = "SUBJECT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME",nullable = false)
    private String name;

}
