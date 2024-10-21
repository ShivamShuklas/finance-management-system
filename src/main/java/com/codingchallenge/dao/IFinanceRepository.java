package com.codingchallenge.dao;

import com.codingchallenge.entity.User;
import com.codingchallenge.entity.Expense;
import com.codingchallenge.entity.Admin;

import java.util.List;

public interface IFinanceRepository {
    boolean createUser(User user);
    boolean createExpense(Expense expense);
    boolean deleteUser(int userId);
    boolean deleteExpense(int expenseId);
    List<Expense> getAllExpenses(int userId);
    boolean updateExpense(int userId, Expense expense);

    boolean createAdmin(Admin admin);
    Admin getAdmin(String username, String password);
    List<Expense> getAllExpensesForAdmin();
    boolean validateAdmin(String adminUsername, String adminPassword);
}

