package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Metody są dostępne w JpaRepository, nie musisz ich implementować, ale warto dodać inne, jeśli potrzeba
}
