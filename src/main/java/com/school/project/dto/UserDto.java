package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;

    private String lastName;

    private String group;

    private Date birthDate;
}
