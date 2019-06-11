package com.school.project.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LESSON")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "THEMA", nullable = false)
    private String thema;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;
    @Column(name = "LESSON_DATE", nullable = false)
    private Date date;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEACHER_ID", nullable = false)
    private User teacher;

}
