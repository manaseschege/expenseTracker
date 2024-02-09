package com.Chei.ExpenseTracker.Service;

import com.Chei.ExpenseTracker.Entity.Expense;
import com.Chei.ExpenseTracker.Repository.ExpenseRepository;
import com.Chei.ExpenseTracker.exeptions.ResourceNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepo;
    private UserService userService;
    @Override
    public Page<Expense> getAllExpenses(Pageable page) {

        return expenseRepo.findByUserId(userService.getLoggedInUser().getId(), page);
    }



    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
    if(expense.isPresent()){
        return expense.get();
    }
    throw  new ResourceNotFoundExeption("Expense is not found for the id "+ id);
    }

    @Override
    public Expense postExpenses(Expense expense) {
expense.setUser(userService.getLoggedInUser());
        return expenseRepo.save(expense);
    }

    @Override
    public void deleteExpenseById(Long id) {
    Expense expense = getExpenseById(id);
   expenseRepo.delete(expense);

    }

    @Override
    public Expense updateExpenseById(Long id, Expense expense) {
        Optional<Expense> expense1=expenseRepo.findById(id);
        if(expense1.isPresent()) {
            expense1.get().setName(expense.getName()!=null?expense.getName():expense1.get().getName());
            expense1.get().setAmount(expense.getAmount()!=null?expense.getAmount():expense1.get().getAmount());
            expense1.get().setCategory(expense.getCategory()!=null?expense.getCategory():expense1.get().getCategory());
            expense1.get().setDate(expense.getDate()!=null?expense.getDate():expense1.get().getDate());
            expense1.get().setDescription(expense.getDescription()!=null?expense.getDescription():expense1.get().getDescription());
            return expenseRepo.save(expense1.get());
        }
        return null;
    }

    @Override
    public List<Expense> getExpenseByCategory(String category, Pageable page) {
        return expenseRepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category, page).toList();
    }

    @Override
    public List<Expense> getExpenseByName(String name, Pageable page) {
        return expenseRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),name, page).toList();
    }

    @Override
    public List<Expense> getExpenseByDate(Date startDate, Date endDate, Pageable page) {
        if(startDate==null){
            startDate=new Date(0);
        }
        if(endDate==null){
            endDate= new Date(System.currentTimeMillis());
        }
        Page<Expense> pages=expenseRepo.getExpenseByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate, endDate, page);
        return pages.toList();

    }

//    @Override
//    public List<Expense> getExpenseByDate(Date startDate, Date endDate) {
//        if(startDate==null){
//            startDate=new Date(0);
//        }
//        if(endDate==null){
//            endDate= new Date(System.currentTimeMillis());
//        }
//       Page<Expense> pages=expenseRepo.findByNameContaining(startDate, endDate,Pageable page).toList()
//    }
//Expense existingExpense = getExpenseById(id);
//existingExpense.setName(expense.getName()!=null ? expense.getName() :existingExpense.getName());
//existingExpense.setAmount(expense.getAmount()!=null ? expense.getAmount():existingExpense.getAmount());
//existingExpense.setCategory(expense.getCategory()!=null ? expense.getCategory(): existingExpense.getCategory());
//existingExpense.setDate(expense.getDate()!=null? expense.getDate(): existingExpense.getDate());
//existingExpense.setDescription(expense.getDescription()!=null? expense.getDescription(): existingExpense.getDescription());
//    return expenseRepo.save(existingExpense);
    }

