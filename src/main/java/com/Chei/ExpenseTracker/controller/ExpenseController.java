package com.Chei.ExpenseTracker.controller;

import com.Chei.ExpenseTracker.Entity.Expense;
import com.Chei.ExpenseTracker.Service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();
    }
    @ResponseStatus(value= HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenses(@Valid @RequestBody Expense expense){
        return expenseService.postExpenses(expense);
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseBYId(@PathVariable Long id){
return expenseService.getExpenseById(id);
    }
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
@DeleteMapping("/expense/{id}")
    public void deleteExpenseById(@PathVariable Long id){
         expenseService.deleteExpenseById(id);
};
@PutMapping("/expenses/{id}")
public Expense getExpenseById(@PathVariable Long id,@RequestBody Expense expense){
        return expenseService.updateExpenseById(id,expense);
}
@GetMapping("/expenses/bycategory")
    public List<Expense> getExpenseByCategory(String category,Pageable page){
       return expenseService.getExpenseByCategory(category,page);
}
@GetMapping("/expenses/byName")
    public List<Expense> getExpenseByName(String name,Pageable page){
        return expenseService.getExpenseByName(name,page);
}
@GetMapping("/expenses/date")
    public List<Expense> getExpenseByDate(@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate, Pageable page) {
    return expenseService.getExpenseByDate(startDate, endDate, page);
}
}
