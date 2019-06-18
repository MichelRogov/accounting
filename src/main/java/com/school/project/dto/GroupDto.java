package com.school.project.dto;

import com.school.project.model.entities.Module;
import com.school.project.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private Date startDate;

    private Module module;

    private List<User> users;
}
