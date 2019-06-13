package com.school.project.dto;

import com.school.project.model.entities.Group;
import com.school.project.model.entities.Subject;
import com.school.project.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    private Long id;
    private String thema;
    private Subject subject;
    private Date date;
    private Group group;
    private User teacher;
}
