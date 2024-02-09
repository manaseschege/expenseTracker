package com.Chei.ExpenseTracker.Service;

        import com.Chei.ExpenseTracker.Entity.Users;
        import com.Chei.ExpenseTracker.Entity.UserModel;

public interface UserService {
    Users createUser(UserModel user);
    Users readUser();
    Users updateUser(UserModel user);
    void deleteUser();
    Users getLoggedInUser();
}
