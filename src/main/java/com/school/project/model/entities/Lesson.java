package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "LESSON")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Lesson {

    @Id
    @Column(name = "LESSON_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="THEMA", nullable = false)
    private String thema;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE",nullable = false)
    private Date date;

    @OneToOne
    @JoinTable(name = "LESSON_GROUP",joinColumns = {@JoinColumn(name = "ID",nullable = false)})
    private Group group;
    @ManyToOne
    @JoinColumn(name ="USER_ID", nullable = false)
    private User teacher;

}
