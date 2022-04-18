package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Book book;

    private int quantity;

    @ManyToOne
    private ShoppingCart shoppingCart;
}
