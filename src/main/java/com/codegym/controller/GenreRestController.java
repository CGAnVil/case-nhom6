package com.codegym.controller;

import com.codegym.model.Genre;
import com.codegym.service.genre.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/genres")
public class GenreRestController {
    @Autowired
    private IGenreService genreService;

    @GetMapping
    public ResponseEntity<Iterable<Genre>> findAll(@RequestParam(name = "h") Optional<String> h) {
        Iterable<Genre> genres = genreService.findAll();
        if (h.isPresent()) {
            genres = genreService.findGenreByNameContaining(h.get());
        }
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> findById(@PathVariable Long id) {
        Optional<Genre> genreOptional = genreService.findById(id);
        if (!genreOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genreOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> save(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.save(genre), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre newGenre) {
        Optional<Genre> genreOptional = genreService.findById(id);
        if (!genreOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newGenre.setId(id);
        return new ResponseEntity<>(genreService.save(newGenre), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> deleteGenre(@PathVariable Long id) {
        Optional<Genre> genreOptional = genreService.findById(id);
        if (!genreOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genreService.removeById(id);
        return new ResponseEntity<>(genreOptional.get(), HttpStatus.OK);
    }
}

