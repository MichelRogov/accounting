package com.school.project.controller;


import com.school.project.model.entities.*;
import com.school.project.service.LessonService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LessonController.class)
@Import(ModelMapper.class)
public class LessonControllerTest {

    @Autowired
    private MockMvc mvc;

    static Date date;

    static Long time;

    private static String NEW_LESSON_JSON_STRING2;

    @MockBean
    private LessonService lessonService;

    public LessonControllerTest() throws Exception {
    }

    @BeforeClass
    public static void beforeTest() throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        NEW_LESSON_JSON_STRING2 = "{\"thema\":\"Angular\",\"subjects\":[{\"id\":2},{\"id\":3}],\"date\":\"2019-05-04T00:00:00.000+0000\",\"group\":{\"id\":3},\"teacher\":{\"id\":3}}";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = format.parse("2019-05-04T00:00:00.000");
        time = date.getTime();
    }

    @Test
    public void testGetLessonById() throws Exception {
        when(lessonService.getLesson(12L)).thenReturn(getTestLesson());

        mvc.perform(get("/lessons/" + 12)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(12))
                .andExpect(jsonPath("$.thema").value("Angular"))
                .andExpect(jsonPath("$.subjects[0].id").value(3))
                .andExpect(jsonPath("$.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.subjects[1].id").value(2))
                .andExpect(jsonPath("$.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.group.id").value(3))
                .andExpect(jsonPath("$.group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.group.module.id").value(1))
                .andExpect(jsonPath("$.group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.group.module.hours").value(70))
                .andExpect(jsonPath("$.group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.group.module.price").value(300))
                .andExpect(jsonPath("$.group.users[0].id").value(1))
                .andExpect(jsonPath("$.group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.group.users[1].id").value(2))
                .andExpect(jsonPath("$.group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.teacher.id").value(3))
                .andExpect(jsonPath("$.teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.teacher.phoneNumber").value("12345678"));

    }

    @Test
    public void testGetAllLessons() throws Exception {
        when(lessonService.getAllLessons()).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[0].thema").value("JS"))
                .andExpect(jsonPath("$.[0].subjects[0].id").value(3))
                .andExpect(jsonPath("$.[0].subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[0].subjects[1].id").value(2))
                .andExpect(jsonPath("$.[0].subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[0].creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.[0].group.id").value(3))
                .andExpect(jsonPath("$.[0].group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.module.id").value(1))
                .andExpect(jsonPath("$.[0].group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.[0].group.module.hours").value(70))
                .andExpect(jsonPath("$.[0].group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.[0].group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[0].group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.[0].group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[0].group.module.price").value(300))
                .andExpect(jsonPath("$.[0].group.users[0].id").value(1))
                .andExpect(jsonPath("$.[0].group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.[0].group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.[0].group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.[0].group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[0].group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].id").value(2))
                .andExpect(jsonPath("$.[0].group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.[0].group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.[0].group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.[0].group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[0].group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].teacher.id").value(3))
                .andExpect(jsonPath("$.[0].teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.[0].teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.[0].teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.[0].teacher.phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].id").value(11))
                .andExpect(jsonPath("$.[1].thema").value("Angular"))
                .andExpect(jsonPath("$.[1].subjects[0].id").value(3))
                .andExpect(jsonPath("$.[1].subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].subjects[1].id").value(2))
                .andExpect(jsonPath("$.[1].subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[1].creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.[1].group.id").value(3))
                .andExpect(jsonPath("$.[1].group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.module.id").value(1))
                .andExpect(jsonPath("$.[1].group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.[1].group.module.hours").value(70))
                .andExpect(jsonPath("$.[1].group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.[1].group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.[1].group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[1].group.module.price").value(300))
                .andExpect(jsonPath("$.[1].group.users[0].id").value(1))
                .andExpect(jsonPath("$.[1].group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.[1].group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.[1].group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.[1].group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].id").value(2))
                .andExpect(jsonPath("$.[1].group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.[1].group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.[1].group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.[1].group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].teacher.id").value(3))
                .andExpect(jsonPath("$.[1].teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.[1].teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.[1].teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.[1].teacher.phoneNumber").value("12345678"));

    }

    @Test
    public void testGetAllLessonsByGroup() throws Exception {
        when(lessonService.getAllLessonsByGroup(3L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/group/" + 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[0].thema").value("JS"))
                .andExpect(jsonPath("$.[0].subjects[0].id").value(3))
                .andExpect(jsonPath("$.[0].subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[0].subjects[1].id").value(2))
                .andExpect(jsonPath("$.[0].subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[0].creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.[0].group.id").value(3))
                .andExpect(jsonPath("$.[0].group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.module.id").value(1))
                .andExpect(jsonPath("$.[0].group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.[0].group.module.hours").value(70))
                .andExpect(jsonPath("$.[0].group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.[0].group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[0].group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.[0].group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[0].group.module.price").value(300))
                .andExpect(jsonPath("$.[0].group.users[0].id").value(1))
                .andExpect(jsonPath("$.[0].group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.[0].group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.[0].group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.[0].group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[0].group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].id").value(2))
                .andExpect(jsonPath("$.[0].group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.[0].group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.[0].group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.[0].group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[0].group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].teacher.id").value(3))
                .andExpect(jsonPath("$.[0].teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.[0].teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.[0].teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.[0].teacher.phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].id").value(11))
                .andExpect(jsonPath("$.[1].thema").value("Angular"))
                .andExpect(jsonPath("$.[1].subjects[0].id").value(3))
                .andExpect(jsonPath("$.[1].subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].subjects[1].id").value(2))
                .andExpect(jsonPath("$.[1].subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[1].creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.[1].group.id").value(3))
                .andExpect(jsonPath("$.[1].group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.module.id").value(1))
                .andExpect(jsonPath("$.[1].group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.[1].group.module.hours").value(70))
                .andExpect(jsonPath("$.[1].group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.[1].group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.[1].group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[1].group.module.price").value(300))
                .andExpect(jsonPath("$.[1].group.users[0].id").value(1))
                .andExpect(jsonPath("$.[1].group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.[1].group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.[1].group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.[1].group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].id").value(2))
                .andExpect(jsonPath("$.[1].group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.[1].group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.[1].group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.[1].group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].teacher.id").value(3))
                .andExpect(jsonPath("$.[1].teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.[1].teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.[1].teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.[1].teacher.phoneNumber").value("12345678"));
    }

    @Test
    public void testGetAllLessonsByTeacher() throws Exception {
        when(lessonService.getAllLessonsByTeacher(2L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/teacher/" + 2)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[0].thema").value("JS"))
                .andExpect(jsonPath("$.[0].subjects[0].id").value(3))
                .andExpect(jsonPath("$.[0].subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[0].subjects[1].id").value(2))
                .andExpect(jsonPath("$.[0].subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[0].creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.[0].group.id").value(3))
                .andExpect(jsonPath("$.[0].group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.module.id").value(1))
                .andExpect(jsonPath("$.[0].group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.[0].group.module.hours").value(70))
                .andExpect(jsonPath("$.[0].group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.[0].group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[0].group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.[0].group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[0].group.module.price").value(300))
                .andExpect(jsonPath("$.[0].group.users[0].id").value(1))
                .andExpect(jsonPath("$.[0].group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.[0].group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.[0].group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.[0].group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[0].group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].id").value(2))
                .andExpect(jsonPath("$.[0].group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.[0].group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.[0].group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.[0].group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[0].group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].teacher.id").value(3))
                .andExpect(jsonPath("$.[0].teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.[0].teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.[0].teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.[0].teacher.phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].id").value(11))
                .andExpect(jsonPath("$.[1].thema").value("Angular"))
                .andExpect(jsonPath("$.[1].subjects[0].id").value(3))
                .andExpect(jsonPath("$.[1].subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].subjects[1].id").value(2))
                .andExpect(jsonPath("$.[1].subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[1].creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.[1].group.id").value(3))
                .andExpect(jsonPath("$.[1].group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.module.id").value(1))
                .andExpect(jsonPath("$.[1].group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.[1].group.module.hours").value(70))
                .andExpect(jsonPath("$.[1].group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.[1].group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.[1].group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[1].group.module.price").value(300))
                .andExpect(jsonPath("$.[1].group.users[0].id").value(1))
                .andExpect(jsonPath("$.[1].group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.[1].group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.[1].group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.[1].group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].id").value(2))
                .andExpect(jsonPath("$.[1].group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.[1].group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.[1].group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.[1].group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].teacher.id").value(3))
                .andExpect(jsonPath("$.[1].teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.[1].teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.[1].teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.[1].teacher.phoneNumber").value("12345678"));
    }

    @Test
    public void testGetAllLessonsBySubject() throws Exception {
        System.out.println(time + "vasea");
        when(lessonService.getAllLessonsBySubject(3L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/subject/" + 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[0].thema").value("JS"))
                .andExpect(jsonPath("$.[0].subjects[0].id").value(3))
                .andExpect(jsonPath("$.[0].subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[0].subjects[1].id").value(2))
                .andExpect(jsonPath("$.[0].subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[0].creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.[0].group.id").value(3))
                .andExpect(jsonPath("$.[0].group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.module.id").value(1))
                .andExpect(jsonPath("$.[0].group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.[0].group.module.hours").value(70))
                .andExpect(jsonPath("$.[0].group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.[0].group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[0].group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.[0].group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[0].group.module.price").value(300))
                .andExpect(jsonPath("$.[0].group.users[0].id").value(1))
                .andExpect(jsonPath("$.[0].group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.[0].group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.[0].group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.[0].group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[0].group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].id").value(2))
                .andExpect(jsonPath("$.[0].group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.[0].group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.[0].group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.[0].group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[0].group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].teacher.id").value(3))
                .andExpect(jsonPath("$.[0].teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.[0].teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.[0].teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.[0].teacher.phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].id").value(11))
                .andExpect(jsonPath("$.[1].thema").value("Angular"))
                .andExpect(jsonPath("$.[1].subjects[0].id").value(3))
                .andExpect(jsonPath("$.[1].subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].subjects[1].id").value(2))
                .andExpect(jsonPath("$.[1].subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[1].creationDate").value(time.longValue()))
                .andExpect(jsonPath("$.[1].group.id").value(3))
                .andExpect(jsonPath("$.[1].group.startDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.module.id").value(1))
                .andExpect(jsonPath("$.[1].group.module.name").value("Basic_Java"))
                .andExpect(jsonPath("$.[1].group.module.hours").value(70))
                .andExpect(jsonPath("$.[1].group.module.subjects[0].id").value(3))
                .andExpect(jsonPath("$.[1].group.module.subjects[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].group.module.subjects[1].id").value(2))
                .andExpect(jsonPath("$.[1].group.module.subjects[1].name").value("FRONTEND"))
                .andExpect(jsonPath("$.[1].group.module.price").value(300))
                .andExpect(jsonPath("$.[1].group.users[0].id").value(1))
                .andExpect(jsonPath("$.[1].group.users[0].firstName").value("Sergey"))
                .andExpect(jsonPath("$.[1].group.users[0].lastName").value("Petrov"))
                .andExpect(jsonPath("$.[1].group.users[0].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[0].email").value("petrov@email.com"))
                .andExpect(jsonPath("$.[1].group.users[0].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].group.users[0].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[0].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].id").value(2))
                .andExpect(jsonPath("$.[1].group.users[1].firstName").value("Iurii"))
                .andExpect(jsonPath("$.[1].group.users[1].lastName").value("Vasiliev"))
                .andExpect(jsonPath("$.[1].group.users[1].birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].email").value("vasiliev@email.com"))
                .andExpect(jsonPath("$.[1].group.users[1].phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.[1].group.users[1].createdDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].group.users[1].updatedDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].teacher.id").value(3))
                .andExpect(jsonPath("$.[1].teacher.firstName").value("Niko"))
                .andExpect(jsonPath("$.[1].teacher.lastName").value("Teacher"))
                .andExpect(jsonPath("$.[1].teacher.birthDate").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].teacher.email").value("teacher@email.com"))
                .andExpect(jsonPath("$.[1].teacher.phoneNumber").value("12345678"));
    }

    @Test
    public void testCreateNewLesson() throws Exception {
        when(lessonService.createLesson(any(Lesson.class))).thenReturn(getRealTestLesson());
        mvc.perform(post("/lessons")
                .content(NEW_LESSON_JSON_STRING2)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = format.parse("2019-05-04T00:00:00.000");
        verify(lessonService).createLesson(new Lesson(null, "Angular",
                getListSubjectsForCreate(), null,
                new Group(3L, null, null, null),
                new User(3L, null, null, null, null, null, null, null)));
    }

    @Test
    public void testUpdateLesson() throws Exception {
        mvc.perform(put("/lessons/" + 12L)
                .content(NEW_LESSON_JSON_STRING2)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(lessonService, times(1)).updateLesson(getLessonToUpdate(), 12L);
    }

    private Lesson getTestLesson() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");

        return new Lesson(12L,
                "Angular",
                getListSubjects(),
                date,
                new Group(3L, date, new Module(1L, "Basic_Java", 70, getListSubjects(), 300.00), getListUsers()),
                getTeacherForTest());
    }


    private Lesson getRealTestLesson() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");

        return new Lesson(null,
                "Angular",
                getListSubjectsForCreate(),
                null,
                new Group(3L, null, null, null),
                new User(3L, null, null, null, null, null, null, null));
    }


    private Lesson getLessonToUpdate() throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");

        return new Lesson(null,
                "Angular",
                getListSubjectsForCreate(),
                null,
                new Group(3L, null, null, null),
                new User(3L, null, null, null, null, null, null, null));
    }

    private List<Lesson> getListLessonsForTest() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        List<Lesson> listToTest = new ArrayList<>();
        Lesson l1 = new Lesson(12L,
                "JS",
                getListSubjects(),
                date,
                new Group(3L, date,
                        new Module(1L, "Basic_Java", 70, getListSubjects(), 300.00),
                        getListUsers()),
                getTeacherForTest());
        Lesson l2 = new Lesson(11L,
                "Angular",
                getListSubjects(),
                date,
                new Group(3L, date,
                        new Module(1L, "Basic_Java", 70, getListSubjects(), 300.00),
                        getListUsers()),
                getTeacherForTest());
        Collections.addAll(listToTest, l1, l2);
        return listToTest;
    }

    private List<Subject> getListSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Subject sb1 = new Subject(3L, "QA");
        Subject sb2 = new Subject(2L, "FRONTEND");
        Collections.addAll(subjects, sb1, sb2);
        return subjects;
    }

    private List<Subject> getListSubjectsForCreate() {
        List<Subject> subjects = new ArrayList<>();
        Subject sb1 = new Subject(2L, null);
        Subject sb2 = new Subject(3L, null);
        Collections.addAll(subjects, sb1, sb2);
        return subjects;
    }


    private List<User> getListUsers() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        User us1 = new User(1L, "Sergey", "Petrov", date, "petrov@email.com", "12345678", date, date);
        User us2 = new User(2L, "Iurii", "Vasiliev", date, "vasiliev@email.com", "12345678", date, date);
        List<User> listUsers = new ArrayList<>();
        Collections.addAll(listUsers, us1, us2);
        return listUsers;
    }

    private User getTeacherForTest() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        return new User(3L, "Niko", "Teacher", date, "teacher@email.com", "12345678", date, date);
    }
}
