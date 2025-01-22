package com.example.demo.controller;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping
    public String showAllExpenses(Model model) {
        model.addAttribute("expenses", expenseRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "add-expense";
    }

    @PostMapping("/add")
    public String addExpense(@Valid @ModelAttribute Expense expense, BindingResult result) {
        if (result.hasErrors()) {
            return "add-expense";
        }
        expenseRepository.save(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/edit/{id}")
    public String showEditExpenseForm(@PathVariable Long id, Model model) {
        model.addAttribute("expense", expenseRepository.findById(id).orElseThrow());
        return "edit-expense";
    }

    @PostMapping("/edit/{id}")
    public String editExpense(@PathVariable Long id, @Valid @ModelAttribute Expense expense, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-expense";
        }
        expense.setId(id);
        expenseRepository.save(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        return "redirect:/expenses";
    }
}
