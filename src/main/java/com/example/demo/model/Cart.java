package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonManagedReference
    @OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<Item>();;

    public Cart() {}

    public void addItem(Item item) {
        item.setCart(this);
        items.add(item);
    }

    public void removeItem(Item item) {
        item.setCart(null);
        items.remove(item);
    }
}
