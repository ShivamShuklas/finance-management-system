package com.codingchallenge.exceptiontest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.codingchallenge.dao.FinanceRepositoryImpl;
import com.codingchallenge.entity.Expense;

import java.util.Date;

public class InvalidExpenseTest {
    @Test
    public void testInvalidExpenseCreation() {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        Expense expense = new Expense(0, 999, 1500.0, 999, new Date(), "Invalid Test"); // Invalid user and category ID
        boolean isExpenseCreated = financeRepo.createExpense(expense);
        assertFalse(isExpenseCreated); // Should be false as the foreign key constraint should fail
    }
}
