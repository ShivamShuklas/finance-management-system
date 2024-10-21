package com.codingchallenge.daotest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.codingchallenge.dao.FinanceRepositoryImpl;

public class ExpenseDeletionTest {
    @Test
    public void testDeleteExpense() {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        boolean isExpenseDeleted = financeRepo.deleteExpense(8); // assuming expense with ID 1 exists
        assertTrue(isExpenseDeleted);
    }
}
