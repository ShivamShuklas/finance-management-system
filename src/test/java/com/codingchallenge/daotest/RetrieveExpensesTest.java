package com.codingchallenge.daotest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.codingchallenge.dao.FinanceRepositoryImpl;
import com.codingchallenge.entity.Expense;

import java.util.List;

public class RetrieveExpensesTest {
    @Test
    public void testGetAllExpenses() {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        List<Expense> expenses = financeRepo.getAllExpenses(1); // assuming user with ID 1 exists
        assertFalse(expenses.isEmpty()); // Should not be empty if user has expenses
    }
}
