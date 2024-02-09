package com.Chei.ExpenseTracker.Repository;

import com.Chei.ExpenseTracker.Entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {



    Optional<Expense>findByUserIdAndId(Long userId, Long id);
    Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable pageable);
     Page<Expense> findByUserIdAndNameContaining(Long userId,String name,Pageable page);
Page<Expense> getExpenseByUserIdAndDateBetween(Long userId,Date startDate, Date endDate,Pageable page);
Page<Expense> findByUserId(Long userId,Pageable page);
}
