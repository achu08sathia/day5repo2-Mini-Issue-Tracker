package com.day5proj2;

import java.util.List;
import java.util.Scanner;

import com.day5proj2.dao.IssueDao;
import com.day5proj2.models.Issue;

public class App {
    public static void main(String[] args) {
        IssueDao issueDao = new IssueDao();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Mini Issue Tracker =====");
            System.out.println("1. Add Issue");
            System.out.println("2. View All Issues");
            System.out.println("3. Update Issue Status");
            System.out.println("4. Delete Issue");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter status (Open/In Progress/Closed): ");
                    String status = scanner.nextLine();
                    issueDao.addIssue(new Issue(0, title, description, status));
                    break;

                case 2:
                    List<Issue> issues = issueDao.getAllIssues();
                    if (issues.isEmpty()) {
                        System.out.println("No issues found!");
                    } else {
                        System.out.println("\n--- All Issues ---");
                        for (Issue issue : issues) {
                            System.out.println("ID: " + issue.getId());
                            System.out.println("Title: " + issue.getTitle());
                            System.out.println("Description: " + issue.getDescription());
                            System.out.println("Status: " + issue.getStatus());
                            System.out.println("----------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter issue ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new status: ");
                    String newStatus = scanner.nextLine();
                    issueDao.updateStatus(updateId, newStatus);
                    break;

                case 4:
                    System.out.print("Enter issue ID to delete: ");
                    int deleteId = scanner.nextInt();
                    issueDao.deleteIssue(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}