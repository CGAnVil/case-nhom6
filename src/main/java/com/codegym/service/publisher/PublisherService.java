package com.codegym.service.publisher;

import com.codegym.model.Publisher;
import com.codegym.repository.IPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherService implements IPublisherService{
    @Autowired
    private IPublisherRepository publisherRepository;
    @Override
    public Iterable<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        publisherRepository.deletePublisher(id);
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Iterable<Publisher> findProductByNameContaining(String name) {
        return publisherRepository.findByNameContaining(name);
    }

}
