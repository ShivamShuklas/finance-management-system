package com.codingchallenge.jdbctest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.codingchallenge.jdbc.DBConn;

import java.sql.Connection;

public class DBConnectionTest {
    @Test
    public void testConnection() {
        try (Connection conn = DBConn.getConnection()) {
            assertNotNull(conn);
        } catch (Exception e) {
            fail("Database connection failed: " + e.getMessage());
        }
    }
}
