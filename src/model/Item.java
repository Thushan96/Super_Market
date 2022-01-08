package model;

import java.util.Objects;

public class Item {
    private String ItemCode;
    private String Description;
    private String PackSize;
    private String UnitPrice;
    private String QtyOnHand;
    private String Discount;

    public Item(String itemCode, String description, String packSize, String unitPrice, String qtyOnHand, String discount) {
        ItemCode = itemCode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
        Discount = discount;
    }

    public Item() {
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPackSize() {
        return PackSize;
    }

    public void setPackSize(String packSize) {
        PackSize = packSize;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", PackSize='" + PackSize + '\'' +
                ", UnitPrice='" + UnitPrice + '\'' +
                ", QtyOnHand='" + QtyOnHand + '\'' +
                ", Discount='" + Discount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getItemCode(), item.getItemCode()) &&
                Objects.equals(getDescription(), item.getDescription()) &&
                Objects.equals(getPackSize(), item.getPackSize()) &&
                Objects.equals(getUnitPrice(), item.getUnitPrice()) &&
                Objects.equals(getQtyOnHand(), item.getQtyOnHand()) &&
                Objects.equals(getDiscount(), item.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemCode(), getDescription(), getPackSize(), getUnitPrice(), getQtyOnHand(), getDiscount());
    }
}
