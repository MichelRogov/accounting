package com.school.project.controller;

import com.school.project.dto.GroupDto;
import com.school.project.model.entities.Group;
import com.school.project.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class GroupController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private GroupService groupService;

    @PostMapping("/group")
    public Group createGroup(@RequestBody GroupDto groupDto){
        return groupService.create(convertGroupDtoToGroup(groupDto));
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<Group> getGroupByID(@PathVariable long id){
        return ResponseEntity.ok()
                .body(groupService.getGroupById(id));
    }

    @GetMapping("/groups")
    public List<GroupDto> getAllGroups() {
        return groupService.getAllGroups().stream()
                .map(s -> convertGroupToGroupDto(s))
                .collect(Collectors.toList());
    }

    private Group convertGroupDtoToGroup(GroupDto groupDto){
        return modelMapper.map(groupDto, Group.class);
    }

    private GroupDto convertGroupToGroupDto(Group group){
        return modelMapper.map(group, GroupDto.class);
    }
}

