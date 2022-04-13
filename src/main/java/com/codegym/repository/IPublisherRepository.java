package com.codegym.repository;

import com.codegym.model.Publisher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublisherRepository extends PagingAndSortingRepository<Publisher,Long> {
    Iterable<Publisher> findByNameContaining(String name);

}
