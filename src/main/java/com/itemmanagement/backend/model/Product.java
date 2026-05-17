package com.itemmanagement.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// เพิ่ม Entity เพื่อให้ Hibernate อ่านเจอแล้วไปสร้างเป็น table บน database
@Entity
public class Product {

    // กำหนดรูปแบบของ Object
    // หลังจาก constructor ทำงานเสร็จ object จะมีค่าทันที
    @Id     // Field หรือตัวแปรนี้ (ในที่นี้คือ id) จะเป็น Primary Key ของตาราง
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // บอกให้ฐานข้อมูล (PostgreSQL) เป็นคน "ออกเลข ID ให้โดยอัตโนมัติ" เมื่อมีการบันทึกข้อมูลใหม่ลงไป
    private Long id;
    private String name;
    private double price;
    private boolean inStock;

    public Product() {}

    // ประกาศ constructor Product ให้รับค่า id, name, price, inStock เข้ามา
    // โปรแกรมเริ่มทำงานที่นี่ก่อน
    public Product(Long id, String name, int price, boolean inStock) {

        // ex. this.id คือตัวแปรที่รับเข้ามาจาก constructor แล้วแทนค่าให้ this.id = private Long id ของ class Product
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    // เปลี่ยนสิทธิ์จาก private ให้เป็น public เพื่อให้ controller สามารถนำค่าตัวแปรใน object ไปแสดงที่หน้าบ้านได้
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
