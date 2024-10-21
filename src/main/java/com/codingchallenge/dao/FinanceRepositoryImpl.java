package com.codingchallenge.dao;

import com.codingchallenge.entity.Admin;
import com.codingchallenge.entity.User;
import com.codingchallenge.entity.Expense;
import com.codingchallenge.exception.ExpenseNotFoundException;
import com.codingchallenge.exception.UserNotFoundException;
import com.codingchallenge.jdbc.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceRepositoryImpl implements IFinanceRepository {

    @Override
    public boolean createUser(User user) {
        String query = "INSERT INTO users (user_id, username, password, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createExpense(Expense expense) {
        String query = "INSERT INTO expenses (expense_id, user_id, amount, category_id, date, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, expense.getExpenseId());
            ps.setInt(2, expense.getUserId());
            ps.setDouble(3, expense.getAmount());
            ps.setInt(4, expense.getCategoryId());
            ps.setDate(5, new java.sql.Date(expense.getDate().getTime()));
            ps.setString(6, expense.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) throw new UserNotFoundException("User with id " + userId + " not found.");
            return rowsAffected > 0;
        } catch (SQLException | UserNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteExpense(int expenseId) {
        String query = "DELETE FROM expenses WHERE expense_id = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, expenseId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) throw new ExpenseNotFoundException("Expense with id " + expenseId + " not found.");
            return rowsAffected > 0;
        } catch (SQLException | ExpenseNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Expense> getAllExpenses(int userId) {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses WHERE user_id = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("expense_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("amount"),
                        rs.getInt("category_id"),
                        rs.getDate("date"),
                        rs.getString("description")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    @Override
    public boolean updateExpense(int userId, Expense expense) {
        String query = "UPDATE expenses SET amount = ?, category_id = ?, date = ?, description = ? WHERE user_id = ? AND expense_id = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, expense.getAmount());
            ps.setInt(2, expense.getCategoryId());
            ps.setDate(3, new java.sql.Date(expense.getDate().getTime()));
            ps.setString(4, expense.getDescription());
            ps.setInt(5, userId);
            ps.setInt(6, expense.getExpenseId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Admin implementations
    @Override
    public boolean createAdmin(Admin admin) {
        String query = "INSERT INTO admin (admin_id, username, password, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, admin.getAdminId());
            ps.setString(2, admin.getUsername());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Admin getAdmin(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getInt("admin_id"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Expense> getAllExpensesForAdmin() {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("expense_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("amount"),
                        rs.getInt("category_id"),
                        rs.getDate("date"),
                        rs.getString("description")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    @Override
    public boolean validateAdmin(String adminUsername, String adminPassword) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, adminUsername);
            ps.setString(2, adminPassword);

            ResultSet rs = ps.executeQuery();
            return rs.next();  // If result set is not empty, admin credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
