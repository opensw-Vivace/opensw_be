package com.vivace.opensw.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String pw;

    @Column
    private Boolean status;







}
