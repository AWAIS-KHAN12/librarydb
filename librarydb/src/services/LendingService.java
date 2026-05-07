/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.time.LocalDate;
import java.util.List;
import models.Lending;

/**
 *
 * @author Hashir
 */

import db.DBConnection;
import models.Lending;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LendingService {

    public boolean issueBook(Lending lending) {
        String insertSql = "INSERT INTO lending (book_id, member_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
        String updateBookSql = "UPDATE books SET available = false WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Transaction start

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                 PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql)) {

                insertStmt.setInt(1, (int) lending.getBookId());
                insertStmt.setInt(2, (int) lending.getMemberId());
                insertStmt.setDate(3, (Date) lending.getIssueDate());
                insertStmt.setDate(4, (Date) lending.getReturnDate());
                int rowsInserted = insertStmt.executeUpdate();

                updateBookStmt.setInt(1, (int) lending.getBookId());
                int rowsUpdated = updateBookStmt.executeUpdate();

                conn.commit(); // Transaction commit

                return rowsInserted > 0 && rowsUpdated > 0;
            } catch (SQLException e) {
                conn.rollback(); // Rollback on error
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Reset autocommit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnBook(int lendingId, Date returnDate) {
        String updateLendingSql = "UPDATE lending SET return_date = ? WHERE id = ?";
        String updateBookSql = "UPDATE books SET available = true WHERE id = (SELECT book_id FROM lending WHERE id = ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement updateLendingStmt = conn.prepareStatement(updateLendingSql);
                 PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql)) {

                updateLendingStmt.setDate(1, returnDate);
                updateLendingStmt.setInt(2, lendingId);
                int rowsUpdatedLending = updateLendingStmt.executeUpdate();

                updateBookStmt.setInt(1, lendingId);
                int rowsUpdatedBook = updateBookStmt.executeUpdate();

                conn.commit();

                return rowsUpdatedLending > 0 && rowsUpdatedBook > 0;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Lending> getAllLendings() {
        String sql = "SELECT * FROM lendings";
        List<Lending> lendings = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Lending lending = new Lending(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("return_date")
                );
                lendings.add(lending);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lendings;
    }

    public boolean returnBook(int lendingId, LocalDate returnDate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



