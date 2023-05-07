package com.example.CaseStudyApp.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "order_total")
    private Double orderTotal;
    @OneToOne(targetEntity = File.class, cascade = CascadeType.ALL)
    private File file;
    @ManyToMany(targetEntity = Item.class, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Item> itemList;

    public Order() {
    }

    public Order(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Order(Integer orderId, Double orderTotal, File file, List<Item> itemList) {
        this.orderId = orderId;
        this.orderTotal = orderTotal;
        this.file = file;
        this.itemList = itemList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}