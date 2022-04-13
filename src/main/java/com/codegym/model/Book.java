package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotNull
    private double price;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Author author;

    @Column(nullable = false)
    private String releaseYear;

    @Column(nullable = false)
    private String republish;

    @ManyToOne
    private Publisher publisher;

    private String image;





}
