package com.java.domain;

/**
 * Created by liuf on 2016/8/25.
 */
public class OfficePriceTag {
    private int id;
    private int supplierId;
    private String officePrice;
    private String amount;
    private String price;
    //是否带有if判断
    private String ifCondition;

    public String getIfCondition() {
        return ifCondition;
    }

    public void setIfCondition(String ifCondition) {
        this.ifCondition = ifCondition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getOfficePrice() {
        return officePrice;
    }

    public void setOfficePrice(String officePrice) {
        this.officePrice = officePrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OfficePriceTag{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", officePrice='" + officePrice + '\'' +
                ", amount='" + amount + '\'' +
                ", price='" + price + '\'' +
                ", ifCondition='" + ifCondition + '\'' +
                '}';
    }
}
