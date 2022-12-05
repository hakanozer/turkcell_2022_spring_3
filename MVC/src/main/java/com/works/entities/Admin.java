package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Length(min = 0, max = 100)
    @Column(length = 100)
    private String name;

    @NotEmpty
    @NotNull
    @Email
    @Column(unique = true, length = 200)
    private String email;

    @NotEmpty
    @NotNull
    @Column(length = 500)
    private String password;

}
