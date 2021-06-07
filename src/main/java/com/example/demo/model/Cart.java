package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="cart", cascade = CascadeType.ALL)
    private Set<Item> items;

    public Cart() {}

    public Cart(Set<Item> items) {
        this.items = items;
    }
}
