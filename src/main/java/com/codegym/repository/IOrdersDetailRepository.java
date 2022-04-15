package com.codegym.repository;

import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersDetailRepository extends JpaRepository<OrderDetail,Long> {
    @Query("select sum(o.amount) from Book b left join OrderDetail o on b.id = o.book.id where b.id = ?1 group by b.id")
    Integer sumProductAmount(Long id);

    Iterable<OrderDetail> findAllByOrder(Order order);
}
