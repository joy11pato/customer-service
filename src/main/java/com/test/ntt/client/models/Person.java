package com.test.ntt.client.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String gender;

    @Column(nullable = false, unique = true)
    private String idCard;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    public Person(){};

    public Person (String name,
                   String gender,
                   String idCard,
                   String address,
                   String phone){
        this.name = name;
        this.gender = gender;
        this.idCard = idCard;
        this.address = address;
        this.phone = phone;

    }

}
