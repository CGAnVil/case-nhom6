package com.codegym.controller;


import com.codegym.model.Author;
import com.codegym.model.AuthorForm;
import com.codegym.service.AuthorService.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping
    public ResponseEntity<Page<Author>> findAll(@RequestParam(name = "q") Optional<String> q, @PageableDefault(value = 5) Pageable pageable) {
        Page<Author> authors;
        if (!q.isPresent()) {
            authors = authorService.findAll(pageable);
        } else {
            authors = authorService.findByNameContaining(q.get(), pageable);
        }
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        Optional<Author> productOptional = authorService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Author> save(@ModelAttribute AuthorForm authorForm) {
        MultipartFile image = authorForm.getImage();
        if (image.getSize() != 0) {
            String fileName = authorForm.getImage().getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + fileName;
            try {
                FileCopyUtils.copy(authorForm.getImage().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Author author = new Author(authorForm.getId(),authorForm.getName(),authorForm.getDateBirth(),authorForm.getDateDeath(),authorForm.getQuantityBook(),authorForm.getNationality(),authorForm.getWiki(),fileName);
            return new ResponseEntity<>(authorService.save(author), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @ModelAttribute AuthorForm authorForm) {
        Optional<Author> oldAuthor = authorService.findById(id);
        if (!oldAuthor.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author author = oldAuthor.get();
        MultipartFile multipartFile = authorForm.getImage();
        if (multipartFile.getSize() != 0){
            String fileName = authorForm.getImage().getOriginalFilename();
            author.setImage(fileName);
            try {
                FileCopyUtils.copy(multipartFile.getBytes(),new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            author.setName(authorForm.getName());
            author.setDateBirth(authorForm.getDateBirth());
            author.setDateDeath(authorForm.getDateDeath());
            author.setQuantityBook(authorForm.getQuantityBook());
            author.setNationality(authorForm.getNationality());
            author.setWiki(authorForm.getWiki());
            authorService.save(author);
            return new ResponseEntity<>(author,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Author> DeleteAuthor(@PathVariable Long id) {
        Optional<Author> productOptional = authorService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.removeById(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

}