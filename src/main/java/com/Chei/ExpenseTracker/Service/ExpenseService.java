package com.Chei.ExpenseTracker.Service;

import com.Chei.ExpenseTracker.Entity.Expense;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable page);
Expense getExpenseById(Long id);
    Expense postExpenses(Expense expense);
    void deleteExpenseById(Long id);
Expense updateExpenseById(Long id,Expense expense);
List<Expense> getExpenseByCategory(String category,Pageable page);
List<Expense>getExpenseByName(String name,Pageable page);
List<Expense> getExpenseByDate(Date startDate, Date endDate,Pageable page);
}
