package com.codingchallenge.daotest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.codingchallenge.dao.FinanceRepositoryImpl;
import com.codingchallenge.entity.Expense;

import java.util.Date;

public class ExpenseUpdateTest {
    @Test
    public void testUpdateExpense() {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        Expense expense = new Expense(1, 1, 2500.0, 1, new Date(), "Updated Grocery");
        boolean isUpdated = financeRepo.updateExpense(1, expense); // assuming user with ID 1 exists
        assertTrue(isUpdated);
    }
}
