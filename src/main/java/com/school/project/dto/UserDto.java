package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

import static com.school.project.configuration.AppConfiguration.CreateValidation;
import static com.school.project.configuration.AppConfiguration.UpdateValidtion;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Null(groups = {CreateValidation.class})
    @NotNull(groups = {UpdateValidtion.class})
    private Long id;

    @NotEmpty(groups = {CreateValidation.class})
    @Length(min = 2, max = 20)
    private String firstName;

    @NotEmpty(groups = {CreateValidation.class})
    @Length(min = 2, max = 20)
    private String lastName;

    @NotNull(groups = {CreateValidation.class})
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date birthDate;

    @NotNull(groups = {CreateValidation.class})
    @Email
    private String email;

    @NotNull(groups = {CreateValidation.class})
    private String phoneNumber;

}
