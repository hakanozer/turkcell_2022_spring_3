package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nid")
    private Long nid;

    @NotNull
    private Long aid;
    @Column(length = 200)
    private String title;
    @Column(length = 500)
    private String detail;
    @Column(length = 7)
    private String color;

}
