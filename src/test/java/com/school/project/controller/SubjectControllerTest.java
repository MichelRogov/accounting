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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectController.class)
@Import(ModelMapper.class)

public class SubjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SubjectService subjectService;

    @Test
    public void createNewSubject() throws Exception {
        mvc.perform(post("/subject")
                .content(NEW_SUBJECT_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //Date date = format.parse("2019-05-04T00:00:00");
        //dates are a bit tricky, lets omit them for now
        verify(subjectService).create(new Subject("QA"));
    }

    @Test
    public void getSubjectByID() throws Exception {
        when(subjectService.getSubjectById(1L)).thenReturn(getSampleSubject());

        mvc.perform(get("/subject/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("QA"));
    }

    @Test
    public void getAllSubjects() throws Exception {
        when(subjectService.getAllSubjects()).thenReturn(getSampleSubjectList());

        mvc.perform(get("/subjects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("QA"))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Frontend"));
    }

    @Test
    public void updateSubject() throws Exception {

        mvc.perform(put("/subjects/" + 1)
                .content(NEW_SUBJECT_FOR_UPDATE_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(subjectService, Mockito.times(1)).update(getSampleSubjectToUpdate(), 1L);
    }

    public Subject getSampleSubject() {
        return new Subject("QA");
    }

    private static String NEW_SUBJECT_JSON_STRING = "{\"id\":\"1\",\"name\":\"QA\"}";

    private List<Subject> getSampleSubjectList(){
        List<Subject> subjects = Arrays.asList(new Subject("QA"), new Subject("Frontend"));
        return subjects;
    }
    private static String NEW_SUBJECT_FOR_UPDATE_JSON_STRING = "{\"id\":\"1\",\"name\":\"QA\"}";
    private Subject getSampleSubjectToUpdate() {
        return new Subject("QA");
    }


}
