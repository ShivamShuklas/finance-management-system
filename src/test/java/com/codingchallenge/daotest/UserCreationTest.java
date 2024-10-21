package com.codingchallenge.daotest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.codingchallenge.dao.FinanceRepositoryImpl;
import com.codingchallenge.entity.User;

public class UserCreationTest {
    @Test
    public void testCreateUser() {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        User user = new User(0, "testUser", "password", "testuser@example.com");
        boolean isUserCreated = financeRepo.createUser(user);
        assertTrue(isUserCreated);
    }
}
