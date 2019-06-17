package com.school.project.controller;


import com.school.project.model.entities.Group;
import com.school.project.model.entities.Module;
import com.school.project.service.GroupService;
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

    @Test
    public void testGetGroupById() throws Exception {
        when(groupService.getGroupById(6L)).thenReturn(getSampleGroup());

        mvc.perform(get("/group/" + 6)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())//good for simple debugging
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.module.hours").value(100))
                .andExpect(jsonPath("$.module.price").value(500.0));
        //check for all fields returned by the call
    }

    @Test
    public void testCreateNewGroup() throws Exception {

        mvc.perform(post("/group")
                .content(NEW_GROUP_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //Date date = format.parse("2019-05-04T00:00:00");
        //dates are a bit tricky, lets omit them for now
        verify(groupService).create(new Group(null, null, new Module(null, "Java-Advance", 400, null, 700.0),null));
    }

    private Group getSampleGroup() {
        Module module = new Module(null,"Java-Basic",100,null,500.0);
        return new Group(6L,  new Date(), module,null);
    }

    private static String NEW_GROUP_JSON_STRING = "{\"module\":{\"name\":\"Java-Advance\",\"hours\":\"400\",\"price\":\"700\"}}";

}
