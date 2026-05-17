package com.itemmanagement.backend.repository;

import com.itemmanagement.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// ProductRepository จะเป็น Interface ที่สืบทอดมาจาก JpaRepository
// ใช้สำหรับจัดการข้อมูลใน Database (เช่น บันทึก, ลบ, แก้ไข หรือค้นหาข้อมูลสินค้า)
// แต่ละ method เอามาจาก repository ของ spring boot
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA จะสร้าง CRUD methods ให้โดยอัตโนมัติ
    // เช่น save(), findById(), findAll(), deleteById()
}
