package com.codegym.repository;

import com.codegym.model.Publisher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IPublisherRepository extends PagingAndSortingRepository<Publisher,Long> {
    Iterable<Publisher> findByNameContaining(String name);

    @Modifying
    @Query(value = "call delete_publisher(?1)",nativeQuery = true)
    void deletePublisher(Long id);
}
