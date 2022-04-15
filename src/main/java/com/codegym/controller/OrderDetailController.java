package com.codegym.controller;

import com.codegym.model.OrderDetail;
import com.codegym.service.orderDetail.IOrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order-details")
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private IOrdersDetailService ordersDetailService;

    @GetMapping
    public ResponseEntity<Iterable<OrderDetail>> getAllOrdersDetail() {
        return new ResponseEntity<>(ordersDetailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable Long id) {
        Optional<OrderDetail> ordersDetailOptional = ordersDetailService.findById(id);
        return ordersDetailOptional.map(orderDetail -> new ResponseEntity<>(orderDetail, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderDetail> createOrdersDetail(@RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(ordersDetailService.save(orderDetail), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> editOrdersDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> ordersDetailOptional = ordersDetailService.findById(id);
        if (!ordersDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetail.setId(id);
        return new ResponseEntity<>(ordersDetailService.save(orderDetail), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetail> deleteOrdersDetail(@PathVariable Long id) {
        Optional<OrderDetail> ordersDetailOptional = ordersDetailService.findById(id);
        if (!ordersDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersDetailService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/sum")
    public ResponseEntity<Integer> sumAmount(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersDetailService.sumProductAmount(id), HttpStatus.OK);
    }
}
