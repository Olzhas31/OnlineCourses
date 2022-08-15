package com.example.OnlineCourses.dtos;

import com.example.OnlineCourses.domains.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private String login;
    private String password;
    private UserRole userRole;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String registratedDate;

}
