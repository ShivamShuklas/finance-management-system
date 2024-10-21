package com.codingchallenge.app;

import com.codingchallenge.dao.FinanceRepositoryImpl;
import com.codingchallenge.entity.Expense;
import com.codingchallenge.entity.User;
import com.codingchallenge.entity.Admin;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();

        System.out.println("*** Finance Management System ***");
        System.out.println("Enter 1 for : Admin");
        System.out.println("Enter 2 for : User");
        System.out.println("*********************************");
        int roleChoice = sc.nextInt();
        sc.nextLine();
        
        if (roleChoice == 1) {

            System.out.println("Admin Verification, Please Enter your Credentials !");
            System.out.println("Enter Admin Username:");
            String adminUsername = sc.nextLine();
            System.out.println("Enter Admin Password:");
            String adminPassword = sc.nextLine();

            if (financeRepo.validateAdmin(adminUsername, adminPassword)) {

                System.out.println("Admin login successful!");

                while (true) {
                    System.out.println("*** Admin Menu ***");
                    System.out.println("Enter 1 for : View All User Expenses");
                    System.out.println("Enter 2 for : Add a New Admin");
                    System.out.println("Enter 3 for : Exit the Program");
                    System.out.println("******************");

                    int adminChoice = sc.nextInt();
                    sc.nextLine();

                    switch (adminChoice) {
                        case 1:

                            List<Expense> allExpenses = financeRepo.getAllExpensesForAdmin();
                            if (allExpenses.isEmpty()) {
                                System.out.println("No expenses found.");
                            } else {
                                for (Expense exp : allExpenses) {
                                    System.out.println("User ID: " + exp.getUserId() + ", Expense ID: " + exp.getExpenseId() + ", Amount: " + exp.getAmount() + ", Category ID: " + exp.getCategoryId() + ", Date: " + exp.getDate() + ", Description: " + exp.getDescription());
                                }
                            }
                            break;

                        case 2:

                            System.out.println("Create Admin User ID:");
                            int adminId = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Create Admin username:");
                            adminUsername = sc.nextLine();
                            System.out.println("Create Admin password:");
                            adminPassword = sc.nextLine();
                            System.out.println("Enter Admin email:");
                            String adminEmail = sc.nextLine();

                            Admin admin = new Admin(adminId, adminUsername, adminPassword, adminEmail);
                            if (financeRepo.createAdmin(admin)) {
                                System.out.println("Admin added successfully!");
                                System.out.println("With AdminId : " + admin.getAdminId());

                            } else {
                                System.out.println("Failed to add admin.");
                            }
                            break;

                        case 3:
                            System.out.println("Exiting the program");
                            System.out.println("Thank you for using our finance management system !");
                            sc.close();
                            System.exit(0);

                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            }else {
                System.out.println("Invalid Admin credentials!");
            }
            } else if (roleChoice == 2) {
                while (true) {
                    System.out.println("*** User Menu ***");
                    System.out.println("Enter 1 for : Add User");
                    System.out.println("Enter 2 for : Add Expense");
                    System.out.println("Enter 3 for : Delete User");
                    System.out.println("Enter 4 for : Delete Expense");
                    System.out.println("Enter 5 for : Update Expense");
                    System.out.println("Enter 6 for : View All Expenses");
                    System.out.println("Enter 7 for : Exit");
                    System.out.println("******************");

                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1:

                            System.out.println("Create Your User ID:");
                            int userId = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Create your username:");
                            String username = sc.nextLine();
                            System.out.println("Create your password:");
                            String password = sc.nextLine();
                            System.out.println("Enter your email:");
                            String email = sc.nextLine();

                            User user = new User(userId, username, password, email);
                            if (financeRepo.createUser(user)) {
                                System.out.println("User added successfully!");
                                System.out.println("With userId : " + user.getUserId());
                            } else {
                                System.out.println("Failed to add user");
                            }
                            break;

                        case 2:

                            System.out.println("Enter your User ID:");
                            userId = sc.nextInt();
                            System.out.println("Create your Expense ID:");
                            int expenseId = sc.nextInt();
                            System.out.println("Enter amount:");
                            double amount = sc.nextDouble();
                            System.out.println("Enter category ID:");
                            int categoryId = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter the product's description:");
                            String description = sc.nextLine();
                            Date date = new Date();

                            Expense expense = new Expense(expenseId, userId, amount, categoryId, date, description);
                            if (financeRepo.createExpense(expense)) {
                                System.out.println("Expense added successfully!");
                                System.out.println("With ExpenseId : "+expense.getExpenseId());
                            } else {
                                System.out.println("Failed to add expense.");
                            }
                            break;

                        case 3:

                            System.out.println("Enter the User ID to be deleted:");
                            int deleteUserId = sc.nextInt();
                            if (financeRepo.deleteUser(deleteUserId)) {
                                System.out.println("User deleted successfully!");
                            } else {
                                System.out.println("Failed to delete user!");
                            }
                            break;

                        case 4:

                            System.out.println("Enter the Expense ID to be deleted:");
                            int deleteExpenseId = sc.nextInt();
                            if (financeRepo.deleteExpense(deleteExpenseId)) {
                                System.out.println("Expense deleted successfully!");
                            } else {
                                System.out.println("Failed to delete expense!");
                            }
                            break;

                        case 5:

                            System.out.println("Enter your User ID:");
                            int updateUserId = sc.nextInt();
                            System.out.println("Enter the specific expense ID:");
                            int updateExpenseId = sc.nextInt();
                            System.out.println("Enter the new amount:");
                            double updateAmount = sc.nextDouble();
                            System.out.println("Enter the new category ID:");
                            int updateCategoryId = sc.nextInt();
                            sc.nextLine(); // consume newline
                            System.out.println("Enter the new description:");
                            String updateDescription = sc.nextLine();
                            Date updateDate = new Date();

                            Expense updatedExpense = new Expense(updateExpenseId, updateUserId, updateAmount, updateCategoryId, updateDate, updateDescription);
                            if (financeRepo.updateExpense(updateUserId, updatedExpense)) {
                                System.out.println("Expense updated successfully!");
                            } else {
                                System.out.println("Failed to update expense");
                            }
                            break;

                        case 6:

                            System.out.println("Enter the User ID to view expenses:");
                            int viewUserId = sc.nextInt();
                            List<Expense> expenses = financeRepo.getAllExpenses(viewUserId);
                            if (expenses.isEmpty()) {
                                System.out.println("No expenses found for this user.");
                            } else {
                                for (Expense exp : expenses) {
                                    System.out.println("Expense ID: " + exp.getExpenseId() + ", Amount: " + exp.getAmount() + ", Category ID: " + exp.getCategoryId() + ", Date: " + exp.getDate() + ", Description: " + exp.getDescription());
                                }
                            }
                            break;

                        case 7:
                            System.out.println("Exiting the program");
                            System.out.println("Thank you for using our finance management system !");
                            sc.close();
                            System.exit(0);

                        default:
                            System.out.println("Invalid choice");
                    }
                }
            }
        }
}
