package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)",nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotEmpty
    private String dateBirth;

    @Column(nullable = false)
    @NotEmpty
    private String dateDeath;

    @Column(nullable = false)
    private int quantityBook;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String wiki;

    private String Image;

}
