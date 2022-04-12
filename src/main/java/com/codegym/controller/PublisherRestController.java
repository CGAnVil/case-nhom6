package com.codegym.controller;


import com.codegym.model.Publisher;
import com.codegym.service.publisher.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/publishes")
public class PublisherRestController {
    @Autowired
    private IPublisherService publisherService;

    @GetMapping
    public ResponseEntity<Iterable<Publisher>> findAll(@RequestParam(name = "q")Optional<String> q){
        Iterable<Publisher> publishers = publisherService.findAll();
        if (q.isPresent()){
            publishers = publisherService.findProductByNameContaining(q.get());
        }
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> findById(@PathVariable Long id){
        Optional<Publisher> publisherOptional = publisherService.findById(id);
        if (!publisherOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publisherOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher){
        return new ResponseEntity<>(publisherService.save(publisher),HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher){
        Optional<Publisher> publisherOptional = publisherService.findById(id);
        if (!publisherOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        publisher.setId(id);
        return new ResponseEntity<>(publisherService.save(publisher),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable Long id){
        Optional<Publisher> publisherOptional = publisherService.findById(id);
        if (!publisherOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        publisherService.removeById(id);
        return new ResponseEntity<>(publisherOptional.get(),HttpStatus.OK);
    }
}
