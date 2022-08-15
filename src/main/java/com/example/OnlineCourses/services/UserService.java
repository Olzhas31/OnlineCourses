package com.example.OnlineCourses.services;

import com.example.OnlineCourses.domains.UDetails;
import com.example.OnlineCourses.domains.User;
import com.example.OnlineCourses.dtos.RegistrationRequest;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUserById(Long id);

    void update(UDetails user);

    String signUp(RegistrationRequest request);

    int enableUser(String login);
}
