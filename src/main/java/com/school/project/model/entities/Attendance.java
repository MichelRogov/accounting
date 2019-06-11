package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ATTENDANCE")
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "LESSON_ID", nullable = false)
    private Lesson lesson;
    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @Column(name = "IS_PRESENT")
    private boolean isPresent;

}
