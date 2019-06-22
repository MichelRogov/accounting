package com.school.project.controller;


import com.school.project.model.entities.User;
import com.school.project.service.UserService;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Unit test with dependency mocked.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@Import(ModelMapper.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(getSampleUser());

        mvc.perform(get("/users/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())//good for simple debugging
                .andExpect(jsonPath("$.firstName").value("sergey"))
                .andExpect(jsonPath("$.lastName").value("lukichev"));

        verify(userService.getUserById(1L));
        //check for all fields returned by the call
    }

   /* @Test
    public void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(getSampleUserList());

        mvc.perform(get("/users")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].firstName").value("sergey"))
                .andExpect(jsonPath("$.[0].lastName").value("lukichev"))
                .andExpect(jsonPath("$.[0].email").value("sergey@example.com"))
                .andExpect(jsonPath("$.[0].phoneNumber").value("+49333300"))
                .andExpect(jsonPath("$.[1].id").value(2L))
                .andExpect(jsonPath("$.[1].firstName").value("ivan"))
                .andExpect(jsonPath("$.[1].lastName").value("pupkin"))
                .andExpect(jsonPath("$.[1].email").value("ivan@example.com"))
                .andExpect(jsonPath("$.[1].phoneNumber").value("+380501413552"));

        verify(userService.getAllUsers());
    }*/

    @Test
    public void testCreateNewUser() throws Exception {

        mvc.perform(post("/users")
                .content(NEW_USER_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //Date date = format.parse("2019-05-04T00:00:00");
        //dates are a bit tricky, lets omit them for now
        verify(userService).create(new User(null, "Ivan", "Ivanov", null, "ivan_@mail.ru", "17612345678", null, null));
    }

    private User getSampleUser() {
        return new User(1L, "sergey", "lukichev", new Date(), "sergey@example.com", "+49333300", new Date(), new Date());
    }

    private List<User> getSampleUserList() {
        List<User> users = Arrays.asList(new User(1L, "sergey", "lukichev", new Date(), "sergey@example.com", "+49333300", new Date(), new Date()),
                new User(2L, "ivan", "pupkin", new Date(), "ivan@example.com", "+380501413552", new Date(), new Date()));
        return users;
    }

    private static String NEW_USER_JSON_STRING = "{\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"email\":\"ivan_@mail.ru\",\"phoneNumber\":\"17612345678\"}";

    @Test
    public void updateUser() {
    }
}
