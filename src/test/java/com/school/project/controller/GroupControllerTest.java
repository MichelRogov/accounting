package com.school.project.controller;

import com.school.project.dto.GroupDto;
import com.school.project.dto.UserDto;
import com.school.project.model.entities.Group;
import com.school.project.model.entities.Module;
import com.school.project.model.entities.User;
import com.school.project.service.GroupService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/*
 * Unit test with dependency mocked.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
@Import(ModelMapper.class)
public class GroupControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GroupService groupService;

    static Date date;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeClass
    public static void beforeTest() throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void testGetGroupById() throws Exception {

        when(groupService.getById(4L)).thenReturn(getSampleGroup());

        mvc.perform(get("/group/" + 4)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.moduleId").value(2))
                .andExpect(jsonPath("$.startDate").value("2018-01-08T00:00:00.000+0000"))
                .andExpect(jsonPath("$.userList.[0].id").value(2))
                .andExpect(jsonPath("userList.[0].firstName").value("Vasya"))
                .andExpect(jsonPath("userList.[0].lastName").value("Vasin"))
                .andExpect(jsonPath("userList.[0].birthDate").value("1980-02-03T00:00:00.000+0000"))
                .andExpect(jsonPath("userList.[0].email").value("vasya@mail.ru"))
                .andExpect(jsonPath("userList.[0].phoneNumber").value("1762345678"));

    }

    @Test
    public void testCreateNewGroup() throws Exception {

        when(groupService.createUpdate(getRealTestGroup())).thenReturn(getTestGroup());
        GroupDto groupDto = getTestGroupDto();

        mvc.perform(post("/group", groupDto)
                .content(NEW_GROUP_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        date = format.parse("2019-05-04");

        verify(groupService).createUpdate(new Group (2L, date , null,null));
    }

    @Test
    public void testAddUserToGroup() throws Exception {

        when(groupService.addUser(2L,7L)).thenReturn(getGroupWIthAddedUser());

        mvc.perform(put("/group/2/add/7")
            .content(ADD_USER_TO_GROUP_JSON_STRING_)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private Group getSampleGroup() throws Exception{

        date = format.parse("2018-01-08");
        Date birthDate = format.parse("1980-02-03");
        Module module = new Module(2L,"Java-Basic",100,null,500.0);
        List <User> userList = new ArrayList<>();
        userList.add(new User(2L,"Vasya","Vasin",birthDate,"vasya@mail.ru","1762345678",null, null));

        return new Group(4L,  date, module, userList);
    }

    private static String NEW_GROUP_JSON_STRING = "{\"id\":\"2\",\"startDate\":\"2019-05-04T00:00:00.000+0000\"}";
    private static String ADD_USER_TO_GROUP_JSON_STRING_ = "{\"id\":\"2\",\"startDate\":\"2019-11-01T00:00:00.000+0000\",\"userList\":\"[\"{\"id\":\"7\",\"firstName\":\"Misha\",\"lastName\":\"Mishin\",\"birthDate\":\"1980-05-05T00:00:00.000+0000\",\"email\":\"misha@mail.ru\",\"phoneNumber\":\"0491234567\",\"createDate\":\"2019-10-10T00:00:00.000+0000\", \"updateDate\":\"2019-10-11T00:00:00.000+0000\"}";

    private Group getTestGroup() throws Exception {
        date = format.parse("2019-05-04");

        return new Group(2L, date, null,
                new ArrayList<User>());
    }

    private Group getRealTestGroup() throws Exception {

        date = format.parse("2019-05-04");

        return new Group(2L, date, null,
                new ArrayList<User>());
    }

    private GroupDto getTestGroupDto() throws Exception {

        date = format.parse("2019-05-04");
        GroupDto groupDto = new GroupDto(null, date, 2L, new ArrayList<UserDto>());
        return groupDto;
    }

    private Group getGroupWIthAddedUser() throws Exception {

        Date birthDate = format.parse("1980-05-05");
        Date createDate = format.parse("2019-10-10");
        Date updateDate = format.parse("2019-10-11");
        Date startDate = format.parse("2019-11-01");

        User addedUser = new User(7L, "Misha","Mishin",
                birthDate,"misha@mail.ru","0491234567",createDate,updateDate);
        List<User> userList = new ArrayList<>();
        userList.add(addedUser);

        return new Group(2l, startDate, null,userList);
    }

}
