package com.java.domain;

/**
 * Created by liuf on 2016/8/24.
 */
public class CrawlTag {
    private int id;
    private int supplierId;
    private String pn;
    private String mfs;
    private String description;
    private String moq;
    private String inventory;
    private String amount;
    private String price;

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

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getMfs() {
        return mfs;
    }

    public void setMfs(String mfs) {
        this.mfs = mfs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoq() {
        return moq;
    }

    public void setMoq(String moq) {
        this.moq = moq;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
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
        return "CrawlTag{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", pn='" + pn + '\'' +
                ", mfs='" + mfs + '\'' +
                ", description='" + description + '\'' +
                ", moq='" + moq + '\'' +
                ", inventory='" + inventory + '\'' +
                ", amount='" + amount + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
