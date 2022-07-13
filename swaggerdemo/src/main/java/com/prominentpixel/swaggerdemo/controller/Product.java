package com.prominentpixel.swaggerdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Product {

    private List<String> products;

    @PostConstruct
    public void load() {
        this.products = new ArrayList<>();
        this.products.add("Pencil");
        this.products.add("Pen");
        this.products.add("Eraser");
    }

    @GetMapping("/products")
    public List<String> getProducts() {
        return this.products;
    }

    @PostMapping("/products")
    public void save() {
        this.products.add("Book");
        this.products.add("Keyboard");
    }
}
