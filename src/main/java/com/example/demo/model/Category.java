package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    private String name;

    private String description;

    // Pobiera ID kategorii
    public Long getId() {
        return id;
    }

    // Ustawia ID kategorii
    public void setId(Long id) {
        this.id = id;
    }

    // Pobiera nazwę kategorii
    public String getName() {
        return name;
    }

    // Ustawia nazwę kategorii
    public void setName(String name) {
        this.name = name;
    }

    // Pobiera opis kategorii
    public String getDescription() {
        return description;
    }

    // Ustawia opis kategorii
    public void setDescription(String description) {
        this.description = description;
    }

}