package com.learnspring.springrestapi.service;

import com.learnspring.springrestapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);

    List<User> getAllUsers();

    void creatUser(User user);

    void deleteUserById(Long id);

    void updateUser(User user);
}
