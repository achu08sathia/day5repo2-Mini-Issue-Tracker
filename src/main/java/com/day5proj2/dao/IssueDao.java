package com.day5proj2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.day5proj2.models.Issue;
import com.day5proj2.util.DBUtil;

public class IssueDao {

  
    public void addIssue(Issue issue) {
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO issues(title, description, status) VALUES(?,?,?)"
            );
            ps.setString(1, issue.getTitle());
            ps.setString(2, issue.getDescription());
            ps.setString(3, issue.getStatus());
            ps.executeUpdate();
            System.out.println("Issue added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM issues");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Issue issue = new Issue(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("status")
                );
                issues.add(issue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issues;
    }

    public void updateStatus(int id, String status) {
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE issues SET status=? WHERE id=?"
            );
            ps.setString(1, status);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Status updated successfully!");
            else
                System.out.println("Issue not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    public void deleteIssue(int id) {
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM issues WHERE id=?"
            );
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Issue deleted successfully!");
            else
                System.out.println("Issue not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}