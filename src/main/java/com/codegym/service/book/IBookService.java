package com.codegym.service.book;

import com.codegym.model.Book;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService extends IGeneralService<Book> {
    Page<Book> findAll(Pageable pageable);

    Page<Book> findProductByNameContaining(String name, Pageable pageable);
}
