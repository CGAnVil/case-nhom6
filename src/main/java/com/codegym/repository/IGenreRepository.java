package com.codegym.repository;

import com.codegym.model.Genre;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IGenreRepository extends PagingAndSortingRepository<Genre, Long> {
   Iterable<Genre> findByNameContaining(String name);

   @Modifying
   @Query(value = "call delete_genre(?1)",nativeQuery = true)
   void deleteGenre(Long id);

}
