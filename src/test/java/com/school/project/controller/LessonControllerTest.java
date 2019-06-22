package com.school.project.controller;


import com.school.project.dto.LessonDto;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Unit test with dependency mocked.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LessonController.class)
@Import(ModelMapper.class)
public class LessonControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ModelMapper modelMapper;
    static Date date;
    private static String NEW_LESSON_JSON_STRING2;
    @MockBean
    private LessonService lessonService;

    public LessonControllerTest() throws Exception {
    }

    @BeforeClass
    public static void beforeTest() throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        NEW_LESSON_JSON_STRING2 = "{\"thema\":\"Angular\",\"subjectId\":3,\"date\":\"2019-05-04T00:00:00\",\"groupId\":3,\"teacherId\":3}";

    }

    @Test
    public void testGetLessonById() throws Exception {
        when(lessonService.getLesson(12L)).thenReturn(getTestLesson());

        mvc.perform(get("/lessons/" + 12)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())//good for simple debugging
                .andExpect(jsonPath("$.id").value(12))
                .andExpect(jsonPath("$.subjectId").value(3))
                .andExpect(jsonPath("$.date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.groupId").value(3))
                .andExpect(jsonPath("$.teacherId").value(2));
        //check for all fields returned by the call

    }

    @Test
    public void testGetAllLessons() throws Exception {
        when(lessonService.getAllLessons()).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[0].subjectId").value(3))
                .andExpect(jsonPath("$.[0].date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].groupId").value(3))
                .andExpect(jsonPath("$.[0].teacherId").value(2))
                .andExpect(jsonPath("$.[1].id").value(11))
                .andExpect(jsonPath("$.[1].subjectId").value(3))
                .andExpect(jsonPath("$.[1].date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].groupId").value(3))
                .andExpect(jsonPath("$.[1].teacherId").value(2));
    }

    @Test
    public void testGetAllLessonsByGroup() throws Exception {
        when(lessonService.getAllLessonByGroup(3L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/group/" + 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[0].subjectId").value(3))
                .andExpect(jsonPath("$.[0].date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].groupId").value(3))
                .andExpect(jsonPath("$.[0].teacherId").value(2))
                .andExpect(jsonPath("$.[1].id").value(11))
                .andExpect(jsonPath("$.[1].subjectId").value(3))
                .andExpect(jsonPath("$.[1].date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].groupId").value(3))
                .andExpect(jsonPath("$.[1].teacherId").value(2));
    }

    @Test
    public void testGetAllLessonsByTeacher() throws Exception {
        when(lessonService.getAllLessonByTeacher(2L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/teacher/" + 2)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[0].subjectId").value(3))
                .andExpect(jsonPath("$.[0].date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].groupId").value(3))
                .andExpect(jsonPath("$.[0].teacherId").value(2))
                .andExpect(jsonPath("$.[1].id").value(11))
                .andExpect(jsonPath("$.[1].subjectId").value(3))
                .andExpect(jsonPath("$.[1].date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].groupId").value(3))
                .andExpect(jsonPath("$.[1].teacherId").value(2));
    }

    @Test
    public void testGetAllLessonsBySubject() throws Exception {
        when(lessonService.getAllLessonBySubject(3L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/subject/" + 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(12))
                .andExpect(jsonPath("$.[0].subjectId").value(3))
                .andExpect(jsonPath("$.[0].date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[0].groupId").value(3))
                .andExpect(jsonPath("$.[0].teacherId").value(2))
                .andExpect(jsonPath("$.[1].id").value(11))
                .andExpect(jsonPath("$.[1].subjectId").value(3))
                .andExpect(jsonPath("$.[1].date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.[1].groupId").value(3))
                .andExpect(jsonPath("$.[1].teacherId").value(2));
    }

    @Test
    public void testCreateNewLesson() throws Exception {
        when(lessonService.createLesson(getTestLesson())).thenReturn(getTestLesson());
        LessonDto lessonDto = getTestLessonDto();
        mvc.perform(post("/lessons", lessonDto)
                .content(NEW_LESSON_JSON_STRING2)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = format.parse("2019-05-04");
        verify(lessonService).createLesson(new Lesson(12L, "Angular",
                new Subject(3L, "Frontend"), date,
                new Group(3L, new Date(), new Module(), new ArrayList<User>()),
                new User(2L, null, null, new Date(), null, null, new Date(), new Date())));
    }

    @Test
    public void testUpdateLesson() throws Exception {
        when(lessonService.updateLesson(getLessonToUpdate(), 12L)).thenReturn(getTestLesson());
        mvc.perform(put("/lessons/" + 12L).content(NEW_LESSON_JSON_STRING2)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(12))
                .andExpect(jsonPath("$.subjectId").value(3))
                .andExpect(jsonPath("$.date").value("2019-05-04T00:00:00.000+0000"))
                .andExpect(jsonPath("$.groupId").value(3))
                .andExpect(jsonPath("$.teacherId").value(2));
        verify(lessonService).updateLesson(getLessonToUpdate(), 12L);
    }

    private Lesson getTestLesson() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");

        return new Lesson(12L,
                "JS",
                new Subject(3l, "QA"),
                date,
                new Group(3L, new Date(), new Module(), new ArrayList<User>()),
                new User(2L, null, null, new Date(), null, null, new Date(), new Date()));
    }

    private LessonDto getTestLessonDto() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        LessonDto lessonDto = new LessonDto(null, "JS", 3L, date, 3L, 4L);
        return lessonDto;

    }

    private Lesson getLessonToUpdate() throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");

        return new Lesson(12L,
                "Angular",
                new Subject(5l, "QA"),
                date,
                new Group(6L, new Date(), new Module(), new ArrayList<User>()),
                new User(5L, null, null, new Date(), null, null, new Date(), new Date()));
    }

    private List<Lesson> getListLessonsForTest() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        List<Lesson> listToTest = new ArrayList<>();
        Lesson l1 = new Lesson(12L,
                "JS",
                new Subject(3l, "QA"),
                date,
                new Group(3L, new Date(), new Module(), new ArrayList<User>()),
                new User(2L, null, null, new Date(), null, null, new Date(), new Date()));
        Lesson l2 = new Lesson(11L,
                "JS",
                new Subject(3l, "QA"),
                date,
                new Group(3L, new Date(), new Module(), new ArrayList<User>()),
                new User(2L, null, null, new Date(), null, null, new Date(), new Date()));
        Collections.addAll(listToTest, l1, l2);
        return listToTest;
    }

}
