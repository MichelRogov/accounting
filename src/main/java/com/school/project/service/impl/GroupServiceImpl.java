package com.school.project.service.impl;

import com.school.project.model.entities.Group;
import com.school.project.model.entities.User;
import com.school.project.repository.GroupRepository;
import com.school.project.repository.UserRepository;
import com.school.project.service.GroupService;
import javassist.NotFoundException;
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
    public Group createUpdate(Group group) {

        return groupRepository.save(group);
    }

    @Override
    public Group getById(Long id) throws NotFoundException {

        Optional<Group> byId = groupRepository.findById(id);
        validationFinderById(byId, "Group", id);
        return byId.get();
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group addUser(Long groupId, Long userId) throws NotFoundException {

        Optional<User> userById = userRepository.findById(userId);
        validationFinderById(userById, "User", userId);

        Group group = getById(groupId);
        List<User> users = group.getUserList();
        users.add(userById.get());

        return groupRepository.save(group);
    }


    public void validationFinderById(Optional optional, String name, Long id) throws NotFoundException {
        if (!optional.isPresent())
            throw new NotFoundException(name + "with id : " + id + " is not found");
    }

}
