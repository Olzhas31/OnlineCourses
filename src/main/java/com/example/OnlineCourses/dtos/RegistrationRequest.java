package com.example.OnlineCourses.dtos;

import com.example.OnlineCourses.domains.UserRole;
import lombok.Data;

@Data
public class RegistrationRequest {
    private final String login;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final UserRole userRole;
}
