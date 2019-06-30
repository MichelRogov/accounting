package com.school.project.service.impl;

import com.school.project.exception.GroupNotFoundException;
import com.school.project.model.entities.Group;
import com.school.project.repository.GroupRepository;
import com.school.project.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getGroupById(Long id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (!byId.isPresent()) {
            throw new GroupNotFoundException("Group with id " + id + " was not found");
        }
        return byId.get();
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

}
