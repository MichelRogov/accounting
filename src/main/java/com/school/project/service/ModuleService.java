package com.school.project.service;

import com.school.project.model.entities.Module;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleService {

    Module createModule(Module module);

    void updateModule(Module module, Long id);

    Module getModuleById(Long id);

    Module getModuleByName(String name);

    List<Module> getModuleBySubject(Long id);
}
