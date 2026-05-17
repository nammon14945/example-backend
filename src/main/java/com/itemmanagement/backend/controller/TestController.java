package com.itemmanagement.backend.controller;
import com.itemmanagement.backend.model.Product;
import com.itemmanagement.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itemmanagement.backend.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// @RestController
// บอก Spring ว่า class นี้ใช้รับ HTTP request และส่ง response กลับไปโดยตรง

// @GetMapping("/api/hello")
// บอกว่า method นี้จะทำงานเมื่อมี request แบบ GET มาที่ path /api/hello

@RestController
public class TestController {

    @Autowired
    private ProductRepository productRepository;

    // example ส่ง text string ไปหน้าบ้าน
    @GetMapping("/api/get/hello")
    public String hello() {
        return "Hello Spring Boot";
    }

    // test ส่ง text string ไปหน้าบ้าน
    @GetMapping("/api/get/status")
    public String status() {
        return "Application is running";
    }

    // รับ string name ทาง endpoint โดยใช้ pathVariable แล้วเอา string name ส่งไปหน้าเว็บ
    @GetMapping("/api/get/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello " + name;
    }

    // รับค่า string value จากตัวแปรเช่น keyword วิธีการใช้เช่น http://localhost:8080/api/search?keyword=spring
    // จากนั้นจะส่ง text string ไปหน้าบ้าน
    @GetMapping("/api/get/search")
    public String search(@RequestParam String keyword) {
        return "Searching for: " + keyword;
    }

    // ส่ง object json ไปหน้าบ้าน
    @GetMapping("/api/get/map-of-user")
    public Map<String, Object> getMapUser() {
        return Map.of(
                "id", 1,
                "name", "Nammon",
                "role", "Student"
        );
    }

    // ส่ง object json ไปหน้าบ้าน
    @GetMapping("/api/get/map-of-product")
    public Map<String, Object> getMapProduct() {
        return Map.of(
                "id", 1,
                "name", "Keyboard",
                "price", 100,
                "inStock", true
        );
    }

    // สร้าง object ใน Product(Object Class) ขึ้นมาใหม่ แล้วไปอ่าน Product ว่ามี object อะไรบ้าง แล้วเอา object รายการหนึ่งส่งไปยังหน้าบ้าน
    @GetMapping("/api/get/product")
    public Product getProduct() {

        return new Product(101L, "Keyboard", 1500, true);
    }

    // สร้าง object ใน User(Object Class) ขึ้นมาใหม่ แล้วไปอ่าน User ว่ามี object อะไรบ้าง แล้วเอา object รายการหนึ่งส่งไปยังหน้าบ้าน
    @GetMapping("/api/get/user")
    public User getUser() {
        return new User(128L, "1", "tt");
    }

    // สร้าง object ใน Product(Object Class) ขึ้นมาใหม่ แล้วไปอ่าน Product ว่ามี object อะไรบ้าง แล้วเอา object ทั้งหมดส่งไปยังหน้าบ้าน
    @GetMapping("/api/get/products")
    public List<Product> getProducts() {
        return List.of(
                new Product(101L, "Keyboard", 1500, true),
                new Product(102L, "Mouse", 800, true),
                new Product(103L, "Computer", 3000,  false)
        );
    }

    // สร้าง object ใน Product(Object Class) ขึ้นมาใหม่ แล้วไปอ่าน Product ว่ามีรายการไหนที่มี id ตรงกับ /{id} บ้าง
    // แล้วเอา object นั้นส่งไปยังหน้าบ้าน
    @GetMapping("/api/get/product/{id}")

    // ชื่อตัวแปรต้องตรงกับ /{id} หรือจะใช้ชื่อตัวแปรไม่ตรง (@PathVariable("id") Long ids) โดยให้ PathVariable เป็นตัวช่วย binding ให้ได้
    public Product getProductById(@PathVariable Long id) {

        // แทนค่าตัวแปร products คือรายการ objects ที่กำลังจะเพิ่มเข้าไปใน class Products
        List<Product> products = List.of(

                // ทำการเพิ่มรายการเข้าไปยัง class Product
                new Product(101L, "Keyboard", 1500, true),
                new Product(102L, "Mouse", 800, true),
                new Product(103L, "Computer", 3000,  false)
        );

        // ส่ง object ใน Product ที่มี Path id ตรงกับ Product id ไปยังหน้าบ้าน
        // .stream() คือการเอา List products มาเปิดเป็น Stream เพื่อเตรียมประมวลผลข้อมูลทีละตัว
        // Stream เหมือนการนำข้อมูลทั้งหมดใน products มาวางบนสาพาน แล้วจับขึ้นมาดูทีละตัวเพื่อหาว่าข้อมูลของรายการนั้นตรงกันกับเงื่อนไขไหม
        return products.stream()

                // กรองเอาเฉพาะ product ที่ product.getId() ตรงกับ ่รับมาจาก path id
                .filter(product -> product.getId().equals(id))

                // เอาตัวแรกที่กรองเจอ
                .findFirst()

                // ถ้าไม่เจอสินค้าเลย ให้คืนค่าเป็น null
                .orElse(null);
    }

    // สร้าง object ใน Product(Object Class) ขึ้นมาใหม่ แล้วไปอ่าน Product ว่ามีรายการไหนที่มี id ตรงกับ /{id} บ้าง
    // แล้วเอา object นั้นส่งไปหน้าบ้าน
    @GetMapping("/api/get/res_product/{id}")

    // หมายถึง method นี้จะ return ข้อมูลไปเป็น HTTP Response ที่มี body เป็น Product
    // ResponseEntity ดีตรงที่เราควบคุม HTTP status ได้
    public ResponseEntity<Product> getProductByIdSecond(@PathVariable Long id) {
        List<Product> products = List.of(
                new Product(101L, "Keyboard", 1500, true),
                new Product(102L, "Mouse", 700, true),
                new Product(103L, "Monitor", 4500, false)
        );

        // .steam() จับข้อมูลวางเรียง แล้วหยิบบขึ้นทีละรายการเพื่อเช็คเงื่อนไข หากถูกก็เลือกมา
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()

                // ถ้าเจอข้อมูลมันจะส่ง object ที่ตรงเงื่อนไขด้านบน แล้วห่อด้วย ReponseEntity อีกรอบ ที่กล่าวคือ
                // Status: 200 OK
                //    Body:
                //    {
                //      "id": 101,
                //      "name": "Keyboard",
                //      "price": 1500,
                //      "inStock": true
                //    }
                // แปลตามตัว product(เป็นถ้ามีข้อมูล) ->(ให้ทำการ) ResponseEntity.ok(product(นำ product มาห่อด้วย ResponseEntity)
                .map(test -> ResponseEntity.ok(test))
                // กรณีไม่จอ ให้ส่ง ResponseEntity แบบ notFound
                .orElse(ResponseEntity.notFound().build());
    }

    // เมื่อมีการเรียกใช้ endpoint นี้ จะรับ HTTP Response Product โดยข้อมูลที่จะรับมาจะเป็น Body แบบ class Product
    @PostMapping("/api/add/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        //จากนั้นเมื่อรับมาเสร็จจะ return ออกไปหน้าบ้านเป็นรูปแบบนี้
        // Status: 201 OK
        //    Body:
        //    {
        //      "id": 101,
        //      "name": "Keyboard",
        //      "price": 1500,
        //      "inStock": true
        //    }
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // ประกาศให้ product เป็นได้เฉพาะ ArrayList เท่านั้น(แต่ใน ArrayList นี้จะยังสามารถเพิ่ม, เปลี่ยนแปลงค่าได้)
    final List<Product> products = new ArrayList<>();

    // เพิ่มรายการ @RequestBody product เข้าไปยัง List<Product> products
    @PostMapping("/api/add/product_to_list")
    public ResponseEntity<Product> createProductList(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // แสดง List<Product> products ทั้งหมด
    @GetMapping("/api/get/products_from_list")
    public  List<Product> getProductList() {
        return products;
    }

    // บันทึก @RequestBody Product product ลงไปยัง Product savedProduct โดยใช้ save method ของ spring boot Repository
    @PostMapping("/api/add/product_by_repo_method")
    public ResponseEntity<Product> createProductByRepoMethod(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // เรียกดูรายการ Product ทั้งหมด
    @GetMapping("/api/get/all_products_by_repo_method")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/api/get/product_by_repo_method/{id}")
    public ResponseEntity<Product> getProductBuId(@PathVariable Long id) {

        // medthod findById() ของ Spring Data JPA จะคืนค่าเป็น Optional เพื่อป้องกัน NullPointerException ซึ่งบังคับให้เรา “จัดการกรณีไม่มีข้อมูล”
        Optional<Product> product = productRepository.findById(id);

        // ถ้าเจอข้อมูล ให้ทำการ return ResponseEntity.ok(product) แบบนี้
        //    Http Status: 200 OK
        //    Body:
        //    {
        //      "id": 101,
        //      "name": "Keyboard",
        //      "price": 1500,
        //      "inStock": true
        //    }

        // ถ้าไม่เจอ ResponseEntity.notFound().build()
        // Http Status: 404 NOT FOUND
        // Body:
        // {
        // }
        //
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/api/edit/product_by_repo_method/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existing = productRepository.findById(id);

        // isPresent() ตรวจสอบ object ข้างหน้าว่ามีข้อมูลหรือไม่
        // ถ้า existing ไม่มีข้อมูล ให้ส่ง status notFound คืนไป
        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        existing.get().setName(product.getName());
        existing.get().setPrice(product.getPrice());
        existing.get().setInStock(product.getInStock());

        Product updatedProduct = productRepository.save(existing.get());
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/api/delete/product_by_repo_method/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}