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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LessonController.class)
@Import(ModelMapper.class)
public class LessonControllerTest {

    // Test JSON Result by id
    private static final String RESULT_JSON_GET_BY_ID
            = "{\"id\":12,\"thema\":\"Angular\"," +
            "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}]," +
            "\"createdDate\":0," +
            "\"group\":{\"id\":3,\"startDate\":0,\"moduleId\":1," +
            "\"userList\":[" +
            "{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}" +
            ",{\"id\":2,\"firstName\":\"Iurii\",\"lastName\":\"Vasiliev\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"vasiliev@email.com\",\"phoneNumber\":\"12345678\"}" +
            "]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}";
    // Test JSON result get all
    private static final String RESULT_JSON_GET_All_LESSONS
            // Lession 1
            = "[{\"id\":12,\"thema\":\"Angular\"," +
            "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}]," +
            "\"createdDate\":0," +
            "\"group\":{\"id\":3,\"startDate\":0,\"moduleId\":1," +
            "\"userList\":[" +
            "{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}" +
            ",{\"id\":2,\"firstName\":\"Iurii\",\"lastName\":\"Vasiliev\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"vasiliev@email.com\",\"phoneNumber\":\"12345678\"}" +
            "]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}},"  +

            // Lession2
            "{\"id\":4,\"thema\":\"SPRING_BOOT\"," +
            "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}]," +
            "\"createdDate\":0," +
            "\"group\":{\"id\":1,\"startDate\":0,\"moduleId\":2," +
            "\"userList\":[" +
            "{\"id\":3,\"firstName\":\"Stas\",\"lastName\":\"Stasov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"stas@mail.ru\",\"phoneNumber\":\"17612345677\"}]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}]";

    private static final String RESULT_JSON_GET_ALL_LESSONS__BY_GROUP =
            // Lesson Angular
            "[{\"id\":12,\"thema\":\"Angular\"," +
                    "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}]," +
                    "\"createdDate\":0," +
                    "\"group\":{\"id\":3,\"startDate\":0,\"moduleId\":1," +
                    "\"userList\":[" +
                    "{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}" +
                    ",{\"id\":2,\"firstName\":\"Iurii\",\"lastName\":\"Vasiliev\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"vasiliev@email.com\",\"phoneNumber\":\"12345678\"}" +
                    "]}," +
                    "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}"
                    // Lesson JS
                    + ",{\"id\":14,\"thema\":\"JS\"," +
                    "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}]," +
                    "\"createdDate\":0," +
                    "\"group\":{\"id\":3,\"startDate\":0,\"moduleId\":1," +
                    "\"userList\":[" +
                    "{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}" +
                    ",{\"id\":2,\"firstName\":\"Iurii\",\"lastName\":\"Vasiliev\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"vasiliev@email.com\",\"phoneNumber\":\"12345678\"}" +
                    "]}," +
                    "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}]";
    // Result by teacher == by group
    private static final String RESULT_JSON_GET_ALL_LESSONS_BY_TEACHER = RESULT_JSON_GET_ALL_LESSONS__BY_GROUP;

    // Result by teacher == by subject
    private static final String RESULT_JSON_GET_ALL_LESSONS_BY_SUBJECT = RESULT_JSON_GET_ALL_LESSONS__BY_GROUP;
    @Autowired
    private MockMvc mvc;

    static Date date;

    static Long time;

    private static String NEW_LESSON_JSON_STRING2;

    @MockBean
    private LessonService lessonService;

    @BeforeClass
    public static void beforeTest() throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        NEW_LESSON_JSON_STRING2 = "{\"thema\":\"Angular\",\"subjects\":[{\"id\":2},{\"id\":3}],\"group\":{\"id\":3},\"teacher\":{\"id\":3}}";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = format.parse("2019-05-04T00:00:00.000");
        time = date.getTime();
    }

    @Test
    public void testGetLessonById() throws Exception {
        when(lessonService.getLesson(12L)).thenReturn(getTestLessonForID());
        mvc.perform(get("/lessons/" + 12)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())

                .andDo(print())
                .andExpect(content().string(RESULT_JSON_GET_BY_ID));
    }

    @Test
    public void testGetAllLessons() throws Exception {
        when(lessonService.getAllLessons()).thenReturn(getTestLessons());
        mvc.perform(get("/lessons").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(RESULT_JSON_GET_All_LESSONS ));
    }

    @Test
    public void testGetAllLessonsByGroup() throws Exception {
        when(lessonService.getAllLessonsByGroup(3L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/group/" + 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(RESULT_JSON_GET_ALL_LESSONS__BY_GROUP ));
    }

    @Test
    public void testGetAllLessonsByTeacher() throws Exception {
        when(lessonService.getAllLessonsByTeacher(2L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/teacher/" + 2)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(RESULT_JSON_GET_ALL_LESSONS_BY_TEACHER ));
    }

    @Test
    public void testGetAllLessonsBySubject() throws Exception {
        when(lessonService.getAllLessonsBySubject(3L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/subject/" + 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(RESULT_JSON_GET_ALL_LESSONS_BY_SUBJECT ));
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
        verify(lessonService).createLesson(new Lesson("Angular",
                getListSubjectsForCreate(),
                new Group(null, null, null),
                new User(null, null, null, null, null)));
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

    private List<Lesson> getTestLessons() throws Exception {

        Lesson lessonOne = getTestLessonForID();

        Date date = new Date(0);
        Lesson lessonTwo = new Lesson(
                "SPRING_BOOT",
                getListSubjects(),
                getGroupForLessonTwo(),
                getTeacherForTest());
        lessonTwo.setId(4L);
        lessonTwo.setCreatedDate(date);
        lessonTwo.setUpdatedDate(date);

        return Arrays.asList(lessonOne, lessonTwo);
    }

    private Lesson getTestLessonForID() throws Exception {

        Date date = new Date(0);
        Lesson lesson = new Lesson(
                "Angular",
                getListSubjects(),
                getGroupForTest(),
                getTeacherForTest());
        lesson.setId(12L);
        lesson.setCreatedDate(date);
        lesson.setUpdatedDate(date);

        return lesson;
    }

    private Lesson getRealTestLesson() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");

        return new Lesson(
                "Angular",
                getListSubjectsForCreate(),
                new Group(null, null, null),
                new User(null, null, null, null, null));
    }

    private Lesson getLessonToUpdate() throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");

        return new Lesson(
                "Angular",
                getListSubjectsForCreate(),
                new Group(null, null, null),
                new User(null, null, null, null, null));
    }

    private List<Lesson> getListLessonsForTest() throws Exception {
        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //date = format.parse("2019-05-04");
        Date date = new Date(0);

        List<Lesson> listToTest = new ArrayList<>();

        Lesson lessonOne = new Lesson(
                "JS",
                getListSubjects(),
                getGroupForTest(),
                getTeacherForTest());
        lessonOne.setId(14L);
        lessonOne.setCreatedDate(date);
        Lesson lessonTwo = new Lesson(
                "Angular",
                getListSubjects(),
                getGroupForTest(),
                getTeacherForTest());
        lessonTwo.setId(12L);
        lessonTwo.setCreatedDate(date);
        Collections.addAll(listToTest, lessonTwo, lessonOne);
        return listToTest;
    }

    private List<Subject> getListSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Subject subjectOne = new Subject("QA");
        subjectOne.setId(3L);
        Subject subjectTwo = new Subject("FRONTEND");
        subjectTwo.setId(2L);

        Collections.addAll(subjects, subjectOne, subjectTwo);
        return subjects;
    }

    private List<Subject> getListSubjectsForCreate() {
        List<Subject> subjects = new ArrayList<>();
        Subject subjectOne = new Subject(null);
        Subject subjectTwo = new Subject(null);
        Collections.addAll(subjects, subjectOne, subjectTwo);
        return subjects;
    }

    private List<User> getListUsers() throws ParseException {
        // DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // date = format.parse("2019-05-04");

        Date date = new Date(0);

        User userOne = new User("Sergey", "Petrov", date, "petrov@email.com", "12345678");
        User userTwo = new User("Iurii", "Vasiliev", date, "vasiliev@email.com", "12345678");
        List<User> listUsers = new ArrayList<>();
        userOne.setId(1L);
        userOne.setCreatedDate(date);
        userOne.setUpdatedDate(date);
        userTwo.setId(2L);
        userTwo.setCreatedDate(date);
        userTwo.setUpdatedDate(date);
        Collections.addAll(listUsers, userOne, userTwo);
        return listUsers;
    }

    private List<User> getLessonTwoUser() {
        Date date = new Date(0);

        User userOne = new User("Stas", "Stasov", date, "stas@mail.ru", "17612345677");
        userOne.setId(3L);
        userOne.setCreatedDate(date);
        userOne.setUpdatedDate(date);

        return Arrays.asList(userOne);
    }

    private User getTeacherForTest() throws ParseException {
        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //date = format.parse("2019-05-04");
        User teacher = new User("Niko", "Teacher", new Date(0), "teacher@email.com", "12345678");
        teacher.setId(3L);
        return teacher;
    }

    private Group getGroupForLessonTwo() {
        Group group = new Group( getModuleForLessonTwo(), new Date(0), getLessonTwoUser() );
        group.setId(1L);
        return group;

    }

    private Group getGroupForTest() throws ParseException {
        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //date = format.parse("2019-05-04");
        Group group = new Group(getModuleForTest(), new Date(0), getListUsers());
        group.setId(3L);
        return group;
    }

    private Module getModuleForLessonTwo() {
        Module module = new Module("JAVA", 35, getListSubjects(), 700D);
        module.setId(2L);
        return module;
    }

    private Module getModuleForTest() {
        Module module = new Module("Basic_Java", 70, getListSubjects(), 300.00);
        module.setId(1L);
        return module;
    }
}
