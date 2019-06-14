package com.school.project.service.impl;

import com.school.project.dao.RoleDao;
import com.school.project.dao.UserDao;
import com.school.project.dto.UserDto;
import com.school.project.exception.UserNotFoundException;
import com.school.project.model.Role;
import com.school.project.model.entities.User;
import com.school.project.model.types.UserAccountType;
import com.school.project.repository.UserAccountRepository;
import com.school.project.repository.UserRepository;
import com.school.project.service.UserAccountService;
import com.school.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    public User findByUsername(String username){

        return userDao.findByUsername(username);
    }


    @Override
    public User create(User user) {
        userRepository.save(user);
        userAccountService.createUserAccount(user, UserAccountType.STUDENT);
        return user;
    }

    @Override
    public void update(UserDto user, Long id) {
        User userById = getUserById(id);
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        userById.setBirthDate(user.getBirthDate());
        userById.setEmail(user.getEmail());
        userById.setPhoneNumber(user.getPhoneNumber());
        userRepository.saveAndFlush(userById);
    }

    @Override
    public void delete(Long id) {
        userAccountRepository.deleteUserAccountByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) throw new UserNotFoundException("User with id :" + id + "is not found");
        return byId.get();
    }
}
