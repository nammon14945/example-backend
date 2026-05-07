package com.itemmanagement.backend.model;

public class Product {

    // กำหนดรูปแบบของ Object
    private Long id;
    private String name;
    private int price;
    private boolean insStock;

    // ประกาศ constructor Product ให้รับค่า id, name, price, inStock เข้ามา
    public Product(Long id, String name, int price, boolean insStock) {

        // ex. this.id คือตัวแปรที่รับเข้ามาจาก constructor แล้วแทนค่าให้ this.id = private Long id ของ class Product
        this.id = id;
        this.name = name;
        this.price = price;
        this.insStock = insStock;
    }

    // เปลี่ยนสิทธิ์จาก private ให้เป็น public เพื่อให้ controller สามารถนำค่าตัวแปรใน object ไปแสดงที่หน้าบ้านได้
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean getInStock() {
        return insStock;
    }
}
