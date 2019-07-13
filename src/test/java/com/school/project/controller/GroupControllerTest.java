package com.school.project.controller;

import com.school.project.model.entities.Group;
import com.school.project.model.entities.Module;
import com.school.project.model.entities.Subject;
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
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    public static final String RESULT_JSON_GET_GROUP_BY_ID
            = "{\"id\":2,\"startDate\":1556928000000,\"moduleId\":2," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"ivan_@mail.ru\",\"phoneNumber\":\"17612345678\"}]}";

    private static final String RESULT_JSON_GET_GROUP_ALL = "[" +
            "{\"id\":2,\"startDate\":1556928000000,\"moduleId\":2," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"ivan_@mail.ru\",\"phoneNumber\":\"17612345678\"}]}," +
            "{\"id\":10,\"startDate\":1556928000000,\"moduleId\":10," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"birthDate\":\"1970-01-01T00:00:00.000+0000\",\"email\":\"ivan_@mail.ru\",\"phoneNumber\":\"17612345678\"}]}]";

    private static String NEW_GROUP_JSON_STRING = "{\"id\":\"2\",\"startDate\":\"1556928000000\"}";

    private static String ADD_USER_TO_GROUP_JSON_STRING_ = "{\"id\":\"null\",\"startDate\":\"2019-11-01T00:00:00.000+0000\",\"userList\":\"[\"{\"id\":\"7\",\"firstName\":\"Misha\",\"lastName\":\"Mishin\",\"birthDate\":\"1980-05-05T00:00:00.000+0000\",\"email\":\"misha@mail.ru\",\"phoneNumber\":\"0491234567\",\"createDate\":\"2019-10-10T00:00:00.000+0000\", \"updateDate\":\"2019-10-11T00:00:00.000+0000\"}";

    @BeforeClass
    public static void beforeTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void testGetGroupById() throws Exception {
        when(groupService.getById(2L)).thenReturn(getSampleGroup());
        mvc.perform(get("/group/" + 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(RESULT_JSON_GET_GROUP_BY_ID));;
    }

    @Test
    public void testCreateNewGroup() throws Exception {
        when(groupService.createUpdate(any(Group.class))).thenReturn(getRealTestGroup());
        mvc.perform(post("/group")
                .content(NEW_GROUP_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
        date = format.parse("2019-05-04");
        verify(groupService).createUpdate(new Group(null, date, null));
    }

    @Test
    public void testAddUserToGroup() throws Exception {
        when(groupService.addUser(2L, 7L)).thenReturn(getGroupWIthAddedUser());
        mvc.perform(put("/group/2/add/7")
                .content(ADD_USER_TO_GROUP_JSON_STRING_)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllGroups() throws Exception {
        when(groupService.getAll()).thenReturn(getGroupList());

        mvc.perform(get("/groups")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(RESULT_JSON_GET_GROUP_ALL));
    }

    private Group getSampleGroup() throws ParseException {
        date = format.parse("2019-05-04");
        Module module = new Module("Java-Basic", 100, null, 500.0);
        module.setId(2L);
        Group group = new Group(module, date, getListUsers());
        group.setId(2L);
        return group;
    }

    private List<Group> getGroupList() throws ParseException{

        date = format.parse("2019-05-04");
        Module module = new Module("Java-Basic", 100, null, 500.0);
        module.setId(2L);
        Group group = new Group(module, date, getListUsers());
        group.setId(2L);

        Module secondModule = new Module("Java-Advanced", 100, null, 1500.0);
        secondModule.setId(10L);
        Group secondGroup = new Group(secondModule, date, getListUsers());
        secondGroup.setId(10L);

        return Arrays.asList( group, secondGroup );
    }

    private Group getRealTestGroup() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        Group group = new Group(null, date,
                null);
        group.setId(2L);

        return group;
    }

    private Group getGroupWIthAddedUser() throws Exception {
        Date birthDate = format.parse("1980-05-05");
        Date startDate = format.parse("2019-11-01");
        User addedUser = new User("Misha", "Mishin",
                birthDate, "misha@mail.ru", "0491234567");
        List<User> userList = new ArrayList<>();
        userList.add(addedUser);
        return new Group(null, startDate, userList);
    }

    private List<User> getListUsers() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        User us1 = new User("Ivan", "Ivanov", new Date(0), "ivan_@mail.ru", "17612345678");
        List<User> userList = new ArrayList<>();
        us1.setId(1L);

        userList.add(us1);
        return userList;
    }

}
