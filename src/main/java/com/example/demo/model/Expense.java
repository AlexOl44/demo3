package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    @ManyToOne
    @NotNull(message = "Category is required")
    private Category category;

    private String description;

    @NotNull(message = "Date is required")
    private LocalDate date;

    // Gettery i Settery

    // Pobiera ID wydatku
    public Long getId() {
        return id;
    }

    // Ustawia ID wydatku
    public void setId(Long id) {
        this.id = id;
    }

    // Pobiera kwote wydatków
    public Double getAmount() {
        return amount;
    }

    // Ustawia kwotę wydatków
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    // Pobiera kategorię wydatku
    public Category getCategory() {
        return category;
    }

    // Ustawia kategorię wydatku
    public void setCategory(Category category) {
        this.category = category;
    }

    // Pobiera opis wydatku
    public String getDescription() {
        return description;
    }

    // Ustawia opis wydatku
    public void setDescription(String description) {
        this.description = description;
    }

    // Pobiera date wydatku
    public LocalDate getDate() {
        return date;
    }

    // Ustawia date wydatku
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
