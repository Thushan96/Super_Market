package model;

import java.util.Objects;

public class OrderDetail {
    private String OrderId;
    private String ItemCode;
    private int OrderQty;
    private int Price;
    private Double Discount;

    public OrderDetail(String orderId, String itemCode, int orderQty, int price, Double discount) {
        setOrderId(orderId);
        setItemCode(itemCode);
        setOrderQty(orderQty);
        setPrice(price);
        setDiscount(discount);
    }

    public OrderDetail(String itemCode, int orderQty, int price, Double discount) {
        ItemCode = itemCode;
        OrderQty = orderQty;
        Price = price;
        Discount = discount;
    }

    public OrderDetail() {
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getOrderQty() {
        return OrderQty;
    }

    public void setOrderQty(int orderQty) {
        OrderQty = orderQty;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "OrderId='" + OrderId + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", OrderQty=" + OrderQty +
                ", Price='" + Price + '\'' +
                ", Discount=" + Discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;
        OrderDetail detail = (OrderDetail) o;
        return getOrderQty() == detail.getOrderQty() &&
                Objects.equals(getOrderId(), detail.getOrderId()) &&
                Objects.equals(getItemCode(), detail.getItemCode()) &&
                Objects.equals(getPrice(), detail.getPrice()) &&
                Objects.equals(getDiscount(), detail.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getItemCode(), getOrderQty(), getPrice(), getDiscount());
    }
}
