package com.codegym.repository;

import com.codegym.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends PagingAndSortingRepository<Author,Long> {
    Page<Author> findByNameContaining (String name , Pageable pageable);

}