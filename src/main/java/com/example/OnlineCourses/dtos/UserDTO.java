package com.example.OnlineCourses.dtos;

import com.example.OnlineCourses.domains.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private UserRole userRole;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String registratedDate;

    public UserDTO(Long id, String login, String name, String surname, String email, String phoneNumber) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
