package com.Chei.ExpenseTracker.exeptions;
public class ResourceNotFoundExeption extends RuntimeException{
    public ResourceNotFoundExeption(String message){
        super(message);
    }

}