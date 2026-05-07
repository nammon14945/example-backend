package com.itemmanagement.backend.model;

public class User {

    // กำหนดรูปแบบของ Object
    private Long id;
    private String name;
    private String role;

    // ประกาศ constructor User ให้รับค่า id, name, role เข้ามา
    public User(Long id, String name, String role) {

        // ex. this.id คือตัวแปรที่รับเข้ามาจาก constructor แล้วแทนค่าให้ this.id = private Long id ของ class User
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
