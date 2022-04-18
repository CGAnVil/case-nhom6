package com.codegym.repository;

import com.codegym.model.Book;
import com.codegym.model.CartItem;
import com.codegym.model.ShoppingCart;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICartItemRepository extends JpaRepository<CartItem,Long> {
    Iterable<CartItem> findAllByShoppingCart(ShoppingCart shoppingCart);
}
