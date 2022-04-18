package com.codegym.service.orderDetail;

import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.repository.IOrdersDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrdersDetailService implements IOrdersDetailService{
    @Autowired
    private IOrdersDetailRepository ordersDetailRepository;

    @Override
    public Iterable<OrderDetail> findAll() {
        return ordersDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return ordersDetailRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        ordersDetailRepository.deleteById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return ordersDetailRepository.save(orderDetail);
    }

    @Override
    public Integer sumProductAmount(Long id) {
        return ordersDetailRepository.sumProductAmount(id);
    }

    @Override
    public Iterable<OrderDetail> findAllByOrders(Order order) {
        return ordersDetailRepository.findAllByOrder(order);
    }

    @Override
    public Integer showOrderdetailbyOrder(Long id) {
        return ordersDetailRepository.showOrderdetailbyOrder(id);
    }
}
