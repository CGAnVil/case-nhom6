package com.codegym.service.AuthorService;

import com.codegym.model.Author;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAuthorService extends IGeneralService<Author> {
      Page <Author> findByNameContaining (String name , Pageable pageable);

      Page <Author> findAll(Pageable pageable);
}
