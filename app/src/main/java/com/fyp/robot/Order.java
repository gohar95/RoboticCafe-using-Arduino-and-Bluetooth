package com.fyp.robot;

public class Order {
    String name;
    String quantity;
    String tableNumber;
    String productName;


    public Order() {
        // Default constructor required for Firebase
    }

    public Order(String name, String quantity, String tableNumber, String productName) {
        this.name = name;
        this.quantity = quantity;
        this.tableNumber = tableNumber;
        this.productName = productName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }


}
