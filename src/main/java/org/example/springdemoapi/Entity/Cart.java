package org.example.springdemoapi.Entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String cartName;

    private String status;

    private String description;

    private int quantity;

    private double price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")

    private List<Product> product;


    public Cart() {

    }

    public Cart(double price, String cartName, String status, String description, int quantity) {
        this.price = price;
        this.cartName = cartName;
        this.status = status;
        this.description = description;
        this.quantity = quantity;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
