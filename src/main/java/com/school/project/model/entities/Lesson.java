package com.school.project.model.entities;

import com.school.project.base.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "LESSON")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson extends AbstractEntity {

    @Column(name = "THEMA", nullable = false)
    private String thema;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;

    @OneToOne
    @JoinColumn(name = "TEACHER_ID", nullable = false)
    private User teacher;

}
