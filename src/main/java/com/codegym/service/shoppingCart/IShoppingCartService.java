package com.codegym.service.shoppingCart;


import com.codegym.model.ShoppingCart;
import com.codegym.model.User;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IShoppingCartService extends IGeneralService<ShoppingCart> {
    Optional<ShoppingCart> findByUser(User user);
}
