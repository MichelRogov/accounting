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

import java.util.Date;

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
        //check for all fields returned by the call
    }

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

    private static String NEW_USER_JSON_STRING = "{\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"email\":\"ivan_@mail.ru\",\"phoneNumber\":\"17612345678\"}";

}
