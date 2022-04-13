package com.codegym.repository;

import com.codegym.model.Genre;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository extends PagingAndSortingRepository<Genre, Long> {
   Iterable<Genre> findByNameContaining(String name);

}
