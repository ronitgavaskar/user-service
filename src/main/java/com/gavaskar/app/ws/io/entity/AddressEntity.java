package com.gavaskar.app.ws.io.entity;

import com.gavaskar.app.ws.shared.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="addresses")
public class AddressEntity implements Serializable {
    @Id // Primary Key; autoincremented with every entry
    @GeneratedValue
    private long id;
    @Column(length = 30, nullable = false)
    private String addressId;
    @Column(length = 15, nullable = false)
    private String city;
    @Column(length = 15, nullable = false)
    private String country;
    @Column(length = 100, nullable = false)
    private String streetName;
    @Column(length = 7, nullable = false)
    private String postalCode;
    @Column(length = 10, nullable = false)
    private String type;
    //relation between UserDetails and Addresses is OneToMany because one user can have many addresses hence ManyToOne
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity userDetails;

}
