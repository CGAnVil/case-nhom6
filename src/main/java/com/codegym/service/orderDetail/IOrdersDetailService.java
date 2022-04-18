package com.codegym.service.orderDetail;

import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.service.IGeneralService;

public interface IOrdersDetailService extends IGeneralService<OrderDetail> {
    Integer sumProductAmount(Long id);

    Iterable<OrderDetail> findAllByOrders(Order order);

    Iterable<OrderDetail> showOrderDetailByOrder(Long id);
}
