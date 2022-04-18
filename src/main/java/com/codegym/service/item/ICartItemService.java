package com.codegym.service.item;

import com.codegym.model.CartItem;
import com.codegym.model.ShoppingCart;
import com.codegym.model.User;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICartItemService extends IGeneralService<CartItem> {
    Iterable<CartItem> findAllByShoppingCart(ShoppingCart shoppingCart);
}

