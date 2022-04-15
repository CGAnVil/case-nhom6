package com.codegym.controller;


import com.codegym.model.CartItem;
import com.codegym.service.item.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart-items")
public class CartItemController {
    @Autowired
    private ICartItemService cartItemService;

    @GetMapping
    public ResponseEntity<Iterable<CartItem>> getAllItem() {
        return new ResponseEntity<>(cartItemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable Long id) {
        Optional<CartItem> cartItemOptional = cartItemService.findById(id);
        return cartItemOptional.map(cartItem -> new ResponseEntity<>(cartItem, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        return new ResponseEntity<>(cartItemService.save(cartItem), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        Optional<CartItem> cartItemOptional = cartItemService.findById(id);
        return cartItemOptional.map(cartItem1 -> {
            cartItem.setId(cartItem1.getId());
            cartItemService.save(cartItem);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartItem> deleteCartItem(@PathVariable Long id) {
        Optional<CartItem> cartItemOptional = cartItemService.findById(id);
        return cartItemOptional.map(cartItem -> {
            cartItemService.removeById(id);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
