package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    public Item() {}

    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
