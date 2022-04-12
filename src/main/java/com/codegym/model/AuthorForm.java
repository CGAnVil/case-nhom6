package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorForm {

    private Long id;

    private String name;

    private String dateBirth;

    private String dateDeath;

    private int quantityBook;

    private String nationality;

    private String wiki;

    private MultipartFile Image;

}

