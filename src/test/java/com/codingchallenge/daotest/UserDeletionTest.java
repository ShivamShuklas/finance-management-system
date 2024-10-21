package com.codingchallenge.daotest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.codingchallenge.dao.FinanceRepositoryImpl;

public class UserDeletionTest {
    @Test
    public void testDeleteUser() {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        boolean isUserDeleted = financeRepo.deleteUser(1); // assuming user with ID 1 exists
        assertTrue(isUserDeleted);
    }
}

