package com.example.OnlineCourses.services;

import com.example.OnlineCourses.dtos.RegistrationRequest;

public interface RegistrationService {
    String confirmToken(String token);

    String register(RegistrationRequest request);
}
