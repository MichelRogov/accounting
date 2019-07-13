package com.school.project.controller;

import com.school.project.model.entities.User;
import com.school.project.service.UserService;
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
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@Import(ModelMapper.class)
public class UserControllerTest {

    private static String NEW_USER_FOR_UPDATE_JSON_STRING = "{\"id\":3,\"firstName\":\"Stas\",\"lastName\":\"Stasov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"stas@mail.ru\",\"phoneNumber\":\"17612345677\"}";

    private static String RESULT_GET_JSON_USER = "{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"lukichev\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"sergey@example.com\",\"phoneNumber\":\"+49333300\"}";

    private static String RESULT_GET_JSON_ALL_USER = "[{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"ivan_@mail.ru\",\"phoneNumber\":\"17612345678\"},{\"id\":2,\"firstName\":\"Nikolay\",\"lastName\":\"Nikolayev\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"nikolay@mail.ru\",\"phoneNumber\":\"17612345679\"},{\"id\":3,\"firstName\":\"Stas\",\"lastName\":\"Stasov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"stas@mail.ru\",\"phoneNumber\":\"17612345677\"}]";

    private static String NEW_USER_JSON_STRING = "{\"firstName\":\"Gustav\",\"lastName\":\"Merkel\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"merkel@mail.ru\",\"phoneNumber\":\"012\"}";

    private static final User createUser =new User("Gustav", "Merkel", new Date(0), "merkel@mail.ru", "012");

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateNewUser() throws Exception {
        mvc.perform(post("/users")
                .content(NEW_USER_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).create(createUser);
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.getUserById(3L)).thenReturn(getSampleUserToUpdate());
        mvc.perform(put("/users/" + 3)
                .content(NEW_USER_FOR_UPDATE_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userService, Mockito.times(1)).update(getSampleUserToUpdate(), 3L);
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(getSampleUser());
        mvc.perform(get("/users/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())//good for simple debugging
        .andExpect(content().string(RESULT_GET_JSON_USER));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(getSampleUserList());
        mvc.perform(get("/users")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(RESULT_GET_JSON_ALL_USER));
    }

    private User getSampleUser() {
        User user = new User("Sergey", "lukichev", new Date(), "sergey@example.com", "+49333300");
        Date date = new Date(0L);
        user.setId(1L);
        user.setBirthDate(date);
        return user;
    }

    private User getSampleUserToUpdate() {
        Date date = new Date(0L);
        User userStas = new User("Stas", "Stasov", date, "stas@mail.ru", "17612345677");

        userStas.setId(3L);
        userStas.setBirthDate(date);
    return userStas;
    }

    private List<User> getSampleUserList() {
        Date date = new Date(0L);
        User userStas = new User("Stas", "Stasov", date, "stas@mail.ru", "17612345677");

        userStas.setId(3L);
        userStas.setBirthDate(date);

        User userIvan =new User("Ivan", "Ivanov", date, "ivan_@mail.ru", "17612345678");
        userIvan.setId(1L);
        userIvan.setBirthDate(date);

        User userNikolay = new User("Nikolay", "Nikolayev", date, "nikolay@mail.ru", "17612345679");
        userNikolay.setId(2L);
        userNikolay.setBirthDate(date);

        List<User> users = Arrays.asList(  userIvan,  userNikolay, userStas );
        return users;
    }
}
