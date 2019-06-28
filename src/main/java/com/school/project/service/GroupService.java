package com.school.project.service;

import com.school.project.model.entities.Group;

import java.util.List;

public interface GroupService {

    Group create(Group group);

    Group getGroupById(Long id);

    List<Group> getAllGroups();

}
