package com.codingchallenge.daotest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.codingchallenge.dao.FinanceRepositoryImpl;
import com.codingchallenge.entity.Expense;

import java.util.Date;

public class ExpenseCreationTest {
    @Test
    public void testCreateExpense() {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        Expense expense = new Expense(0, 15, 1500, 11, new Date(), "Grocery");
        boolean isExpenseCreated = financeRepo.createExpense(expense);
        assertTrue(isExpenseCreated);
    }
}
