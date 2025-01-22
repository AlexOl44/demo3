package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.service.CategoryService;  // Importowanie CategoryService
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryService categoryService;  // Usługa do pobierania kategorii

    @GetMapping
    public String showExpenseSummary(Model model) {
        List<Expense> expenses = expenseRepository.findAll();

        // Obliczanie sumy wszystkich wydatków
        double totalExpenses = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        // Przekazanie sumy i listy wydatków do modelu
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("expenses", expenses);
        return "expenses/list";  // Zwrócenie widoku listy wydatków
    }

    @GetMapping("/add")
    public String showAddExpenseForm(Model model) {
        List<Expense> expenses = expenseRepository.findAll();
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();

        // Przekazanie listy kategorii do formularza
        model.addAttribute("expense", new Expense());
        model.addAttribute("categories", categoryService.findAll());  // Lista dostępnych kategorii
        model.addAttribute("totalExpenses", totalExpenses);
        return "expenses/form";
    }

    @PostMapping("/add")
    public String addExpense(@Valid @ModelAttribute Expense expense,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Expense> expenses = expenseRepository.findAll();
            double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
            model.addAttribute("totalExpenses", totalExpenses);
            model.addAttribute("categories", categoryService.findAll());
            return "expenses/form";
        }

        expenseRepository.save(expense);  // Zapisanie wydatku
        return "redirect:/expenses";
    }

    @GetMapping("/edit/{id}")
    public String showEditExpenseForm(@PathVariable Long id, Model model) {
        Expense expense = expenseRepository.findById(id).orElseThrow();
        List<Expense> expenses = expenseRepository.findAll();
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();

        // Przekazanie listy kategorii do formularza
        model.addAttribute("expense", expense);
        model.addAttribute("categories", categoryService.findAll());  // Lista dostępnych kategorii
        model.addAttribute("totalExpenses", totalExpenses);
        return "expenses/form";  // Formularz edycji wydatku
    }

    @PostMapping("/edit/{id}")
    public String editExpense(@PathVariable Long id, @Valid @ModelAttribute Expense expense,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Expense> expenses = expenseRepository.findAll();
            double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
            model.addAttribute("totalExpenses", totalExpenses);
            model.addAttribute("categories", categoryService.findAll());
            return "expenses/form";  // Ponowne wyświetlenie formularza w przypadku błędów
        }
        expense.setId(id);  // Ustawienie ID, aby zaktualizować odpowiedni rekord
        expenseRepository.save(expense);  // Zapisanie zaktualizowanego wydatku
        return "redirect:/expenses";  // Przekierowanie na listę wydatków
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        return "redirect:/expenses";  // Przekierowanie na listę wydatków po usunięciu
    }
}
