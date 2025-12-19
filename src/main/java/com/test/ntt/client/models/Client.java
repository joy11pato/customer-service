package com.test.ntt.client.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends Person {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isActive;

    public Client() {
    }

    public Client(String name, String address, String phone, String idCard,
                  String gender, String password){
        super(name, gender, idCard, address, phone);
        this.isActive = true;
        this.password = password;
        this.setName(name);
        this.setGender(gender);
        this.setAddress(address);
        this.setIdCard(idCard);
        this.setPhone(phone);
    }
}
