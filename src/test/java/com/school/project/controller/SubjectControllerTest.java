package com.school.project.controller;

import com.school.project.dto.SubjectDto;
import com.school.project.model.entities.Subject;
import com.school.project.repository.SubjectRepository;
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

    @MockBean
    private SubjectRepository subjectRepository;

    @Test
    public void testCreateNewSubject() throws Exception {

        mvc.perform(post("/subject")
                .content("{\"name\":\"Mathe\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(subjectService).create("Mathe");
    }

    @Test
    public void testGetSubjectById2() throws Exception {
        when(subjectService.getSubjectById(2L))
                .thenReturn(new Subject( "FRONTEND"));

        mvc.perform(get("/subjects/" + 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())//good for simple debugging
                .andExpect(jsonPath("$.name")
                        .value("FRONTEND"))
        ;
        //check for all fields returned by the call
    }

    @Test
    public void testGetSubjectById() throws Exception {
        when(subjectService.getSubjectById(1L))
                .thenReturn(new Subject("BACKEND"));

        mvc.perform(get("/subjects/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())//good for simple debugging
                .andExpect(jsonPath("$.name")
                        .value("BACKEND"))
        ;
        //check for all fields returned by the call
    }

}