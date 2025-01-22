package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Pobiera wszystkie kategorie
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Zapisuje nową kategorię lub edytuje istniejącą
    public void save(Category category) {
        categoryRepository.save(category);
    }

    // Usuwa kategorię na podstawie ID
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    // Pobiera kategorię na podstawie ID
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
}
