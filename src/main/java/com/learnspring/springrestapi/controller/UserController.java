package com.learnspring.springrestapi.controller;

import com.learnspring.springrestapi.entity.User;
import com.learnspring.springrestapi.repository.UserRepository;
import com.learnspring.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Bo sung security, tim hieu jwt
// Tim hieu best practice cho rest api
// Tim hieu Unit Test (JUnit 5, Mockito), viet duoc unit test cho Repository, Service, Controller
// Chem gio dc ve microservice
@RestController
public class UserController {

    // chua co Controller Advice
    // chua define response status (tim hieu them ve HTTP Status)
    @Autowired
    private UserService userService;

    // Tim hieu de su dung Paging
    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // Khong thao tac truc tiep voi Entity, phai tao DTO
    // Su dung Map struct de mapping object (Entity <=> DTO)
    // Chua dinh nghia custom exception
    // Tim hieu va su dung cac Annotation dung cho validate du lieu
    // Response cua post khong bao gio la void
    @PostMapping("/users")
    public void addNewUserPOST(@RequestBody User user) {
        userService.creatUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    // TIm hieu ve Transaction
    // Tim hieu ACID trong sql
    // Tim hieu ve locking(xu ly tinh huong 2 request den cung 1 luc)
    @PutMapping("/users/{id}")
    public void updateUserByIdGET(@PathVariable Long id, @RequestBody User user){
        // business logic khong viet o controller
        // tim khong thay phai co throw exception
        User userGotUpdated= userService.findUserById(id).get();
        userGotUpdated.setLastName(user.getLastName());
        userGotUpdated.setFirstName(user.getFirstName());
        userGotUpdated.setUserName(user.getUserName());
        userGotUpdated.setPassword(userGotUpdated.getPassword());
        userService.updateUser(userGotUpdated);
    }


}
