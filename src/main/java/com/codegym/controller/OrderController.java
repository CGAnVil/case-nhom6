package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.order.IOrdersService;
import com.codegym.service.order.OrdersService;
import com.codegym.service.orderDetail.IOrdersDetailService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IOrdersDetailService ordersDetailService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Order>> getAllOrders() {
//        @RequestParam Integer status
//        if (status == null) {
            return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(ordersService.findAllByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Optional<Order> ordersOptional = ordersService.findById(id);
        return ordersOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Order> createOrders(@RequestBody Order orders) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        orders.setCreateDate(date);
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> editOrders(@PathVariable Long id, @RequestBody Order orders) {
        Optional<Order> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orders.setId(id);
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrders(@PathVariable Long id) {
        Optional<Order> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Iterable<Order>> getAllOrderByUser(@PathVariable Long id, @RequestParam Integer status) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(ordersService.findAllByUserAndStatus(user, status),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{id}/status-not-like")
    public ResponseEntity<Iterable<Order>> getAllOrderByUserAndStatusNotLike(@PathVariable Long id, @RequestParam Integer status) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(ordersService.findAllByUserAndStatusIsNot(user, status),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/order-details")
    public ResponseEntity<Iterable<OrderDetail>> findAllOrderDetailByOrder(@PathVariable Long id) {
        Optional<Order> ordersOptional = ordersService.findById(id);
        return ordersOptional.map(order -> new ResponseEntity<>(ordersDetailService.findAllByOrders(order), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{id}/books")
    public ResponseEntity<Iterable<Book>> getAllProductUserBought(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(ordersService.findAllBookUserBought(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/total-price")
    public ResponseEntity<Long> sumTotalPriceByMonthAndYear(@RequestParam(name = "month") Integer month, @RequestParam(name = "year") Integer year) {
        return new ResponseEntity<>(ordersService.sumTotalPriceInput(month, year), HttpStatus.OK);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<Iterable<IBookImage>> getAllBookByOrder(@PathVariable Long id) {
        Optional<Order> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersService.getAllBookByOrder(id), HttpStatus.OK);
    }
}
