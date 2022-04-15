package com.codegym.service.order;


import com.codegym.model.Book;
import com.codegym.model.IBookImage;
import com.codegym.model.Order;
import com.codegym.model.User;
import com.codegym.service.IGeneralService;

public interface IOrdersService extends IGeneralService<Order> {
    Iterable<Order> findAllByUserAndStatus(User user, Integer status);

    Iterable<Order> findAllByStatus(Integer status);

    Iterable<Book> findAllBookUserBought(User user);

    Long sumTotalPriceInput(Integer month, Integer year);

    Iterable<IBookImage> getAllBookByOrder(Long orderId);

    Iterable<Order> findAllByUserAndStatusIsNot(User user, Integer status);



}
