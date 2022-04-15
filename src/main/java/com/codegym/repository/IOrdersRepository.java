package com.codegym.repository;

import com.codegym.model.Book;
import com.codegym.model.IBookImage;
import com.codegym.model.Order;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepository extends JpaRepository<Order,Long> {
    Iterable<Order> findAllByUserAndStatus(User user, Integer status);

    @Query("select o from Order as o where o.user = ?1 and  o.status <> ?2")
    Iterable<Order> findAllByUserAndStatusIsNot(User user, Integer status);

    Iterable<Order> findAllByStatus(Integer status);

    @Query("SELECT DISTINCT od.book FROM Order o left join OrderDetail od on o.id = od.order.id" +
            " WHERE o.user = ?1 and o.status = 2")
    Iterable<Book> findAllBookUserBought(User user);

    @Query(value = "select sum(amount * price) " +
            "from orders " +
            "left join orders_detail od on orders.id = od.orders_id " +
            "left join book p on od.book_id = p.id " +
            "where month(create_date) = ?1 and year(create_date) = ?2", nativeQuery = true)
    Long sumTotalPriceInput(Integer month, Integer year);

    @Query(value = "select p.id, p.name, i.url, p.price " +
            "from orders " +
            "         left join orders_detail od on orders.id = od.orders_id " +
            "         left join book p on od.book_id = p.id " +
            "         left join image i on p.id = i.book_id " +
            "where orders_id = ?1", nativeQuery = true)
    Iterable<IBookImage> getAllBookByOrder(Long orderId);

}
