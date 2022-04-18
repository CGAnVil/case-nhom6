package com.codegym.service.shoppingCart;

import com.codegym.model.ShoppingCart;
import com.codegym.model.User;
import com.codegym.repository.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService{
    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Override
    public Iterable<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void removeById(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public Optional<ShoppingCart> findByUser(User user) {
        return shoppingCartRepository.findByUser(user);
    }
}