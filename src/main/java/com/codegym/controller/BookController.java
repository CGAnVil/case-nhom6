package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.BookForm;
import com.codegym.service.book.IBookService;
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
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;


    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping
    public ResponseEntity<Page<Book>> findAll(@RequestParam(name = "q") Optional<String> q, @PageableDefault(value = 5) Pageable pageable) {
        Page<Book> books = bookService.findAll(pageable);
        if (q.isPresent()) {
            books = bookService.findProductByNameContaining(q.get(), pageable);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Optional<Book> productOptional = bookService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Book> save(@ModelAttribute BookForm bookForm) {
        MultipartFile image = bookForm.getImage();
        if (image.getSize() != 0) {
            String fileName = bookForm.getImage().getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + fileName;
            try {
                FileCopyUtils.copy(bookForm.getImage().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Book book = new Book(bookForm.getId(), bookForm.getName(), bookForm.getPrice(), bookForm.getGenre(), bookForm.getAuthor(), bookForm.getReleaseYear(), bookForm.getRepublish(), bookForm.getPublisher(), fileName);
            return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @ModelAttribute BookForm bookForm) {
        Optional<Book> oldBook = bookService.findById(id);
        if (!oldBook.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book book = oldBook.get();
        MultipartFile multipartFile = bookForm.getImage();
        if (multipartFile.getSize() != 0) {
            String fileName = bookForm.getImage().getOriginalFilename();
            book.setImage(fileName);
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            book.setName(bookForm.getName());
            book.setPrice(bookForm.getPrice());
            book.setGenre(bookForm.getGenre());
            book.setAuthor(bookForm.getAuthor());
            book.setReleaseYear(bookForm.getReleaseYear());
            book.setRepublish(bookForm.getRepublish());
            book.setPublisher(bookForm.getPublisher());
            bookService.save(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteProduct(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.removeById(id);
        return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
    }

}
