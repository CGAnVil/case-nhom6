package com.codegym.service.genre;

import com.codegym.model.Genre;
import com.codegym.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GenreService implements IGenreService {
    @Autowired
    private IGenreRepository genreRepository;

    @Override
    public Iterable<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);

    }

    @Override
    public void removeById(Long id) {
        genreRepository.deleteById(id);
    }
    @Override
    public Iterable<Genre> findGenreByNameContaining(String name) {
        return genreRepository.findByNameContaining(name);
    }
}

