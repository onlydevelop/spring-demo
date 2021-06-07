package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Item;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = new ArrayList<Cart>();
        cartRepository.findAll().forEach(carts::add);

        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") long id) {
        Optional<Cart> cartsData = cartRepository.findById(id);

        if (cartsData.isPresent()) {
            return new ResponseEntity<Cart>(cartsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        try {
            Cart cartData = new Cart();
            for (Item item: cart.getItems()) {
                cartData.addItem(item);
            }
            Cart _cart = cartRepository.save(cartData);
            return new ResponseEntity<>(_cart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateItem(@PathVariable("id") long id, @RequestBody Cart cart) {
        Optional<Cart> cartData = cartRepository.findById(id);

        if (cartData.isPresent()) {
            Cart _cart = cartData.get();
            for (Item _item: _cart.getItems()) {
                for(Item item: cart.getItems()) {
                    if (_item.getId() == item.getId()) {
                        cart.updateItem(_item, item);
                    }
                }
            }
            return new ResponseEntity<>(cartRepository.save(_cart), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
        try {
            cartRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllItems() {
        try {
            cartRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
