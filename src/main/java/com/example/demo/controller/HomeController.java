package com.example.demo.controller;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Wyświetla stronę główną z podsumowaniem wydatków
    @GetMapping("/")
    public String showHomePage(Model model) {
        double totalExpenses = expenseRepository.findAll().stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        model.addAttribute("totalExpenses", totalExpenses);
        return "home"; // Zwrot do szablonu home.html
    }
}
