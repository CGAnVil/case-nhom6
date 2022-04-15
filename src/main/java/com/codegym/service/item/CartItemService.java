package com.codegym.service.item;

import com.codegym.model.Book;
import com.codegym.model.CartItem;
import com.codegym.model.ShoppingCart;
import com.codegym.model.User;
import com.codegym.repository.IBookRepository;
import com.codegym.repository.ICartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService implements ICartItemService{
    @Autowired
    private ICartItemRepository cartItemRepository;

    @Override
    public Iterable<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Iterable<CartItem> findAllByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findAllByShoppingCart(shoppingCart);
    }
}
