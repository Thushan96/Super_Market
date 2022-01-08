package model;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String orderDate;
    private String custId;
    private double cost;
    private String discount;
    private ArrayList<ItemDetails> items;

    public Order(String orderId, String orderDate, String custId, double cost, String discount, ArrayList<ItemDetails> items) {
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setCustId(custId);
        this.setCost(cost);
        this.setDiscount(discount);
        this.setItems(items);
    }

    public Order(String orderId, String orderDate, String custId, double cost) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.custId = custId;
        this.cost = cost;
    }

    public Order() {
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public ArrayList<ItemDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetails> items) {
        this.items = items;
    }
}
