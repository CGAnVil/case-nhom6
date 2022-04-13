package com.codegym.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {

    private Long id;

    private String name;

    private double price;

    private Genre genre;

    private Author author;

    private String releaseYear;

    private String republish;

    private Publisher publisher;

    private MultipartFile image;
}
