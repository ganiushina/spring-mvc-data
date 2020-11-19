package com.geekbrains.spring.mvc.model;

import javax.persistence.*;

@Entity
@Table(name = "goods")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;


    @Column(name = "coast")
    private int coast;


    public Product(Long id, String name, int coast) {
        this.id = id;
        this.title = name;
        this.coast = coast;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }
}
