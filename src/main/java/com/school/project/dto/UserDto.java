package com.school.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String email;

    private String phoneNumber;

}
