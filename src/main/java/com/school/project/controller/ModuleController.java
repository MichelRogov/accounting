package com.school.project.controller;

import com.school.project.dto.ModuleDto;
import com.school.project.model.entities.Module;
import com.school.project.service.ModuleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/modules")
    public ResponseEntity<ModuleDto> createModule(@RequestBody ModuleDto moduleDto) {
        return ResponseEntity.ok().body(convertModuleToModuleDto(moduleService
                .createModule(convertModuleDtoToModule(moduleDto))));
    }

    @PutMapping("/modules")
    public void updateModule(@RequestBody ModuleDto moduleDto, @RequestParam("id") @PathVariable Long id) {
        moduleService.updateModule(convertModuleDtoToModule(moduleDto), id);
    }

    @GetMapping("/modules")
    public ResponseEntity<ModuleDto> getModuleById(@RequestParam("id") @PathVariable long id) {
        return ResponseEntity.ok().body(convertModuleToModuleDto(moduleService.getModuleById(id)));
    }

    @GetMapping("/modules/")
    public ResponseEntity<ModuleDto> getModuleByName(@RequestParam("name") @PathVariable String name) {
        return ResponseEntity.ok().body(convertModuleToModuleDto(moduleService.getModuleByName(name)));
    }

    @GetMapping("/modules/subject/{id}")
    public ResponseEntity<List<ModuleDto>> getModuleBySubjectsId(@PathVariable long id) {
        return ResponseEntity.ok().body(moduleService.getModuleBySubject(id)
                .stream().map(s -> convertModuleToModuleDto(s)).collect(Collectors.toList()));
    }


    private ModuleDto convertModuleToModuleDto(Module module) {
        return modelMapper.map(module, ModuleDto.class);
    }

    private Module convertModuleDtoToModule(ModuleDto moduleDto) {
        return modelMapper.map(moduleDto, Module.class);
    }
}
