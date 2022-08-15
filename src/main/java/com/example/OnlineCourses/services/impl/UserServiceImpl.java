package com.example.OnlineCourses.services.impl;

import com.example.OnlineCourses.domains.ConfirmationToken;
import com.example.OnlineCourses.domains.UDetails;
import com.example.OnlineCourses.domains.User;
import com.example.OnlineCourses.dtos.RegistrationRequest;
import com.example.OnlineCourses.exceptions.RestAPIException;
import com.example.OnlineCourses.repositories.UDetailsRepository;
import com.example.OnlineCourses.repositories.UserRepository;
import com.example.OnlineCourses.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";

    private final UserRepository userRepository;
    private final UDetailsRepository uDetailsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RestAPIException("user with id " + id + " not found"));
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RestAPIException("user with id " + id + " not found");
        }
        confirmationTokenService.deleteById(id);
        userRepository.deleteById(id);
    }
    
    @Override
    public void update(UDetails uDetails) {
        UDetails item = uDetailsRepository.findById(uDetails.getId())
                .orElseThrow(() -> new RestAPIException("user with id " + uDetails.getId() + " not found"));
        if (uDetails.getName() != null){
            item.setName(uDetails.getName());
        }
        if (uDetails.getSurname() != null){
            item.setSurname(uDetails.getSurname());
        }
        if (uDetails.getEmail() != null){
            if (uDetailsRepository.findUDetailsByEmail(uDetails.getEmail()).isPresent()) {
                throw new RestAPIException("email already taken");
            } else {
                item.setEmail(uDetails.getEmail());
            }
        }
        if (uDetails.getPhoneNumber() != null) {
            item.setPhoneNumber(uDetails.getPhoneNumber());
        }
        uDetailsRepository.saveAndFlush(item);
    }

    @Override
    public String signUp(RegistrationRequest request) {
        if (userRepository.findByLogin(request.getLogin()).isPresent()){
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new RestAPIException("login already taken");
        }

        if (uDetailsRepository.findUDetailsByEmail(request.getEmail()).isPresent()){
            throw new RestAPIException("Email is already taken");
        }

        UDetails uDetails = new UDetails(request.getName(), request.getSurname(), request.getEmail());
        User user = new User(
                request.getLogin(),
                request.getUserRole(),
                bCryptPasswordEncoder.encode(request.getPassword()),
                uDetails);
        uDetails.setUser(user);

        userRepository.saveAndFlush(user);
        uDetailsRepository.saveAndFlush(uDetails);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: SEND EMAIL

        return token;
    }

    public int enableUser(String login) {
        return userRepository.enableUser(login);
    }
}
