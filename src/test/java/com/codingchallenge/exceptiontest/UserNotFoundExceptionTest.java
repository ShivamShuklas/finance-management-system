package com.codingchallenge.exceptiontest;

import org.junit.Test;
import com.codingchallenge.dao.FinanceRepositoryImpl;
import com.codingchallenge.exception.UserNotFoundException;

public class UserNotFoundExceptionTest {
    @Test
    public void testUserNotFound() throws UserNotFoundException {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        financeRepo.deleteUser(999); // assuming user with ID 999 doesn't exist
    }
}
