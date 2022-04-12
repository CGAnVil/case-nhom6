package com.codegym.service.publisher;

import com.codegym.model.Publisher;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPublisherService extends IGeneralService<Publisher> {
    Iterable<Publisher> findProductByNameContaining(String name);

}
