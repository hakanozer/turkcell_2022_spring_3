package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(length = 100)
    @NotEmpty
    @NotNull
    private String name;

    @Column(unique = true, length = 200)
    @NotEmpty
    @NotNull
    @Email
    private String email;

}
