package com.school.project.service;

import com.school.project.model.entities.Group;
import javassist.NotFoundException;

import java.util.List;

public interface GroupService {

    Group createUpdate(Group group);

    Group getById(Long id) throws NotFoundException;

    List<Group> getAll();

    Group addUser(Long groupId, Long userId) throws NotFoundException;
}
