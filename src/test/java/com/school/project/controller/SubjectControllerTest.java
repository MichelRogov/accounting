package com.school.project.controller;

import com.school.project.model.entities.Subject;
import com.school.project.service.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectController.class)
@Import(ModelMapper.class)

public class SubjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SubjectService subjectService;


    @Test
    public void testCreateNewSubject() throws Exception {



        when(subjectService.create(any(Subject.class))).thenReturn(getTestSubject());
        mvc.perform(post("/subject")
                .content(NEW_SUBJECT_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //Date date = format.parse("2019-05-04T00:00:00");
        //dates are a bit tricky, lets omit them for now

        verify(subjectService).create(new Subject("Data"));
    }

    @Test
    public void testGetSubjectByID() throws Exception {
        when(subjectService.getSubjectById(1L)).thenReturn(getSampleSubject());

        mvc.perform(get("/subject/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("BACKEND"));
    }

    @Test
    public void testGetAllSubjects() throws Exception {
        when(subjectService.getAllSubjects()).thenReturn(getSampleSubjectList());

        mvc.perform(get("/subjects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(RESULT_JSON_GET_SUBJECTS));
           //     .andExpect(jsonPath("$.[0].id").value(3))
           //     .andExpect(jsonPath("$.[0].name").value("QA"))
           //     .andExpect(jsonPath("$.[1].id").value(2))
           //     .andExpect(jsonPath("$.[1].name").value("Frontend"));
    }

    @Test
    public void testUpdateSubject() throws Exception {

        mvc.perform(put("/subject/" + 1)
                .content(NEW_SUBJECT_FOR_UPDATE_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(subjectService, Mockito.times(1)).update( getSampleSubject(), 1L);
    }

    private Subject getSampleSubject() {
        Subject subject = new Subject( "BACKEND");
        subject.setId(1L);

        return subject;
    }

    private Subject getTestSubject(){
        Subject subject = new Subject( "Data");
        subject.setId(5L);

        return subject;
    }

    private List<Subject> getSampleSubjectList(){


        Subject subject1 = new Subject("QA");
        subject1.setId(3L);

        Subject subject2 = new Subject("Frontend");
        subject2.setId(2L);

        return Arrays.asList( subject1, subject2);
    }
    private static String NEW_SUBJECT_FOR_UPDATE_JSON_STRING = "{\"id\":\"1\",\"name\":\"BACKEND\"}";
    private static String NEW_SUBJECT_JSON_STRING = "{\"id\":\"5\",\"name\":\"Data\"}";

    private static final String RESULT_JSON_GET_SUBJECTS = "[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"Frontend\"}]";



}
