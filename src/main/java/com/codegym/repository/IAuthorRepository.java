package com.codegym.repository;

import com.codegym.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends PagingAndSortingRepository<Author,Long> {
    Page<Author> findByNameContaining (String name , Pageable pageable);

    @Modifying
    @Query(value = "call delete_author(?1)",nativeQuery = true)
    void deleteAuthor(Long id);

}
