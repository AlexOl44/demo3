package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Wyświetlenie listy wszystkich kategorii
    @GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list"; // Zwraca widok listy kategorii
    }

    // Wyświetla formularz dodawania nowej kategorii
    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/form"; // Zwraca widok formularza dodawania kategorii
    }

    // Obsługuje dodawanie nowej kategorii
    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.save(category); // Zapisuje nową kategorię w bazie danych
        return "redirect:/categories"; // Przekierowuje do listy kategorii
    }

    // Wyświetla formularz edycji kategorii o podanym ID
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        // Sprawdzamy, czy kategoria istnieje, używając Optional. Zgłasza wyjątek, jeśli nie istnieje
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));

        model.addAttribute("category", category);
        return "categories/edit-category";  // Zwraca formularz edycji
    }

    // Obsługuje edycję istniejącej kategorii
    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, @ModelAttribute Category category) {
        category.setId(id);  // Ustawiamy ID kategorii, aby zaktualizować istniejącą kategorię
        categoryService.save(category); // Zapisuje zmiany w bazie danych
        return "redirect:/categories";  // Przekierowanie do listy kategorii po zapisaniu zmian
    }

    // Usuwa kategorię o podanym ID
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id); // Usuwa kategorię z bazy danych
        return "redirect:/categories";  // Przekierowanie po usunięciu kategorii
    }
}
