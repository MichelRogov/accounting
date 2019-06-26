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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        when(userService.getUserById(1L))
                .thenReturn(getSampleUser());

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

    @Test
    public void testUpdateUser() throws Exception {
        User user = getSampleUser();
        when(userService.getUserById(1L)).thenReturn(getUpdatedUser());
        mvc.perform(put("/users/1")
                .content(UPDATE_USER_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Tanya"))
                .andExpect(jsonPath("$.lastName").value("Pawlova"));;
        //verify(userService).update( getUpdatedUser(), 1L);
        // when(userService.update(user, 9L )).thenReturn(getUpdatedUser())

    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(getAllUsers());
        mvc.perform(get("/users")
                .content(UPDATE_USER_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName").value("Ivan"))
                .andExpect(jsonPath("$.[0].lastName").value("Ivanov"))
            .andExpect(jsonPath("$.[1].firstName").value("Nikolay"))
                .andExpect(jsonPath("$.[1].lastName").value("Nikolayev"))
                .andExpect(jsonPath("$.[3].firstName").value("Mark"))
                .andExpect(jsonPath("$.[3].lastName").value("Markov"))
            .andExpect(jsonPath("$.[2].firstName").value("Stas"))
                .andExpect(jsonPath("$.[2].lastName").value("Stasov"));

    }

    private List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        userList.add(new User(1L, "Ivan", "Ivanov", new Date(), "ivan_@mail.ru", "+49333300", new Date(), new Date()));
        userList.add(new User(2L, "Nikolay", "Nikolayev", new Date(), "nikolay@mail.ru", "+17612345679", new Date(), new Date()));
        userList.add(new User(3L, "Stas", "Stasov", new Date(), "stas@mail.ru", "+49333300", new Date(), new Date()));
        userList.add(new User(4L, "Mark", "Markov", new Date(), "mark@mail.ru", "+49333300", new Date(), new Date()));
return userList;
    }

    private User getUpdatedUser() {
        Date date = new Date( 0L );
        return new User(1L, "Tanya", "Pawlova", date, "ivan@mail.ru", "178912", date, date);
    }

    private User getSampleUser() {
        return new User(1L, "sergey", "lukichev", new Date(), "sergey@example.com", "+49333300", new Date(), new Date());
    }

    private static String NEW_USER_JSON_STRING = "{\"firstName\":\"Ivan\", \"lastName\":\"Ivanov\",\"email\":\"ivan_@mail.ru\",\"phoneNumber\":\"17612345678\"}";
    private static String UPDATE_USER_JSON_STRING = "{\"firstName\":\"Tanya\", \"lastName\":\"Pawlova\",\"email\":\"ivan@mail.ru\",\"phoneNumber\":\"178912\"}";
}
