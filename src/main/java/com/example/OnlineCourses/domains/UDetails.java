package com.example.OnlineCourses.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table
@Data
public class UDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String registratedDate;

    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public UDetails(){}

    public UDetails(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.registratedDate = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss").format(new Date());
    }
}
