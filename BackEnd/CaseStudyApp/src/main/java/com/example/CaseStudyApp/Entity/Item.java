package com.example.CaseStudyApp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Items")
public class Item {

    @Id
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_price")
    private Double itemPrice;

    public Item() {
    }

    public Item(String itemName, Double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
