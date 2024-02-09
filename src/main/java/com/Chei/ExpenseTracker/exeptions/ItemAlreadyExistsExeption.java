package com.Chei.ExpenseTracker.exeptions;

public class ItemAlreadyExistsExeption extends RuntimeException{
    public ItemAlreadyExistsExeption(String message){
        super(message);
    }
}
