package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;
    @ManyToOne
    private User user;
}
