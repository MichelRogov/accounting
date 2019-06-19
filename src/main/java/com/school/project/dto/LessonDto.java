package com.school.project.dto;

import com.school.project.model.entities.Subject;
import com.school.project.web.GroupWeb;
import com.school.project.web.UserWeb;
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
    private GroupWeb group;
    private UserWeb teacher;
}
