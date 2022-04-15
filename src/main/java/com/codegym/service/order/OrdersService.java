package com.codegym.service.order;
import com.codegym.model.Book;
import com.codegym.model.IBookImage;
import com.codegym.model.Order;
import com.codegym.model.User;
import com.codegym.repository.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersService implements IOrdersService {

    @Autowired
    private IOrdersRepository ordersRepository;
    @Override
    public Iterable<Order> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public Order save(Order order) {
        return ordersRepository.save(order);
    }

    @Override
    public Iterable<Order> findAllByUserAndStatus(User user, Integer status) {
        return ordersRepository.findAllByUserAndStatus(user, status);
    }

    @Override
    public Iterable<Order> findAllByStatus(Integer status) {
        return ordersRepository.findAllByStatus(status);
    }

    @Override
    public Iterable<Book> findAllBookUserBought(User user) {
        return ordersRepository.findAllBookUserBought(user);
    }

    @Override
    public Long sumTotalPriceInput(Integer month, Integer year) {
        return ordersRepository.sumTotalPriceInput(month, year);
    }

    @Override
    public Iterable<IBookImage> getAllBookByOrder(Long orderId) {
        return ordersRepository.getAllBookByOrder(orderId);
    }

    @Override
    public Iterable<Order> findAllByUserAndStatusIsNot(User user, Integer status) {
        return ordersRepository.findAllByUserAndStatusIsNot(user, status);
    }
}

