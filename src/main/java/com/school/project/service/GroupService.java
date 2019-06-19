package com.school.project.service;

import com.school.project.model.entities.Group;
import com.school.project.model.entities.User;

import java.util.List;

public interface GroupService {

    Group create(Group group);

    Group getGroupById(Long id);

    List<Group> getAllGroups();

    Group addUserToGroup(Long id);
}
