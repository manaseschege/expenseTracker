package com.Chei.ExpenseTracker.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {
    @NotBlank(message="Name should not be empty")
    private String name;
    @NotBlank(message = "Email should not be empty")
    @Email(message="Enter a valid email address")
    private String email;
    @NotNull(message="password should not be empty")
    @Size(min=5,message = "password should should be at least 5 characters")
    private String password;
    private Long age=0L;

}
