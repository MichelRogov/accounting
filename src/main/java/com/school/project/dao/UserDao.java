package com.school.project.dao;

import com.school.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    com.school.project.model.entities.User findByUsername(String username);
}
