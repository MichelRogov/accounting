package com.school.project.service.impl;

import com.school.project.exception.GroupNotFoundException;
import com.school.project.exception.UserNotFoundException;
import com.school.project.model.entities.Group;
import com.school.project.model.entities.User;
import com.school.project.repository.GroupRepository;
import com.school.project.repository.UserRepository;
import com.school.project.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Group createUpdate(Group group){

        return groupRepository.save(group);
    }

    @Override
    public Group getById(Long id) {

        Optional<Group> byId = groupRepository.findById(id);
        if (!byId.isPresent()) {
            throw new GroupNotFoundException("Group with id " + id + " was not found");
        }
        return byId.get();
    }

    @Override
    public List<Group> getAll() {

        return groupRepository.findAll();
    }

    @Override
    public Group addUser(Long groupId, Long userId) {

        Optional<User> userById = userRepository.findById(userId);
        if (!userById.isPresent()) {
            throw new UserNotFoundException("User with id " + userId + " was not found");
        }
        Group group = getById(groupId);
        List <User> users = group.getUserList();
        users.add(userById.get());

        return createUpdate(group);
    }

}
