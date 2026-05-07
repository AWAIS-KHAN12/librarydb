/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package gui;
//
//import db.DBConnection;
//
//import javax.swing.*;
//import javax.swing.border.TitledBorder;
//import javax.swing.table.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class LendingForm extends JPanel {
//
//    private JTextField txtBookID, txtMemberID, txtIssueDate, txtReturnDate, txtSearch;
//    private JButton btnIssue, btnReturn, btnSearch;
//    private JTable table;
//    private DefaultTableModel model;
//
//    // Color Palette: Old Money Theme
//    private final Color bgColor = new Color(245, 245, 240);
//    private final Color borderColor = new Color(200, 200, 180);
//    private final Color headerColor = new Color(90, 120, 90);
//    private final Color buttonColor = new Color(180, 200, 180);
//    private final Color buttonHoverColor = new Color(160, 180, 160);
//    private final Color buttonTextColor = new Color(40, 60, 40);
//    private final Font mainFont = new Font("Serif", Font.PLAIN, 16);
//    private final Font titleFont = new Font("Serif", Font.BOLD, 18);
//
//    public LendingForm() {
//        setLayout(new BorderLayout(10, 10));
//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        setBackground(bgColor);
//
//        // ============== Top Panel: Input Fields ==============
//        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
//        inputPanel.setBackground(bgColor);
//        TitledBorder inputBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2), "Lending Details");
//        inputBorder.setTitleFont(titleFont);
//        inputPanel.setBorder(inputBorder);
//
//        txtBookID = new JTextField();
//        txtMemberID = new JTextField();
//        txtIssueDate = new JTextField();
//        txtReturnDate = new JTextField();
//
//        inputPanel.add(createStyledLabel("Book ID:"));
//        inputPanel.add(txtBookID);
//        inputPanel.add(createStyledLabel("Member ID:"));
//        inputPanel.add(txtMemberID);
//        inputPanel.add(createStyledLabel("Issue Date (YYYY-MM-DD):"));
//        inputPanel.add(txtIssueDate);
//        inputPanel.add(createStyledLabel("Return Date (YYYY-MM-DD):"));
//        inputPanel.add(txtReturnDate);
//
//        btnIssue = createStyledButton("Issue Book");
//        btnReturn = createStyledButton("Return Book");
//
//        inputPanel.add(btnIssue);
//        inputPanel.add(btnReturn);
//
//        // ============== Center Panel: Table ==============
//        String[] columns = {"ID", "Book ID", "Member ID", "Issue Date", "Return Date"};
//        model = new DefaultTableModel(columns, 0);
//        table = new JTable(model) {
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        table.setFont(mainFont);
//        table.setRowHeight(22);
//
//        JTableHeader tableHeader = table.getTableHeader();
//        tableHeader.setOpaque(true);
//        tableHeader.setBackground(headerColor);
//        tableHeader.setForeground(Color.BLACK);
//        tableHeader.setFont(titleFont);
//        tableHeader.setReorderingAllowed(false);
//
//        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//                                                           boolean hasFocus, int row, int column) {
//                JLabel label = new JLabel(" " + value.toString() + " ");
//                label.setFont(titleFont);
//                label.setOpaque(true);
//                label.setBackground(headerColor);
//                label.setForeground(Color.BLACK);
//                label.setHorizontalAlignment(SwingConstants.CENTER);
//                return label;
//            }
//        });
//
//        JScrollPane tableScroll = new JScrollPane(table);
//
//        // ============== Bottom Panel: Search ==============
//        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        searchPanel.setBackground(bgColor);
//        txtSearch = new JTextField(20);
//        btnSearch = createStyledButton("Search by Member ID");
//
//        searchPanel.add(createStyledLabel("Search:"));
//        searchPanel.add(txtSearch);
//        searchPanel.add(btnSearch);
//
//        // ============== Layout ==============
//        add(inputPanel, BorderLayout.NORTH);
//        add(tableScroll, BorderLayout.CENTER);
//        add(searchPanel, BorderLayout.SOUTH);
//
//        loadLendingRecords();
//
//        // ============== Event Listeners ==============
//        btnIssue.addActionListener(e -> issueBook());
//        btnReturn.addActionListener(e -> returnBook());
//        btnSearch.addActionListener(e -> searchRecords());
//
//        table.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int row = table.getSelectedRow();
//                txtBookID.setText(model.getValueAt(row, 1).toString());
//                txtMemberID.setText(model.getValueAt(row, 2).toString());
//                txtIssueDate.setText(model.getValueAt(row, 3).toString());
//                txtReturnDate.setText(model.getValueAt(row, 4).toString());
//            }
//        });
//    }
//
//    // ========== Helper Methods for Styling ==========
//    private JButton createStyledButton(String text) {
//        JButton button = new JButton(text) {
//            private boolean hover = false;
//
//            @Override
//            protected void paintComponent(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g.create();
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//                if (hover) {
//                    g2.setColor(buttonHoverColor);
//                } else {
//                    g2.setColor(buttonColor);
//                }
//                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
//
//                g2.setColor(buttonTextColor);
//                FontMetrics fm = g2.getFontMetrics();
//                int stringWidth = fm.stringWidth(getText());
//                int stringHeight = fm.getAscent();
//
//                g2.setFont(getFont());
//                g2.drawString(getText(), (getWidth() - stringWidth) / 2, (getHeight() + stringHeight) / 2 - 3);
//
//                g2.dispose();
//            }
//
//            @Override
//            public void updateUI() {
//                super.updateUI();
//                setContentAreaFilled(false);
//                setFocusPainted(false);
//                setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
//                setFont(mainFont);
//                setForeground(buttonTextColor);
//
//                addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                        hover = true;
//                        repaint();
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//                        hover = false;
//                        repaint();
//                    }
//                });
//            }
//        };
//        return button;
//    }
//
//    private JLabel createStyledLabel(String text) {
//        JLabel label = new JLabel(text);
//        label.setFont(mainFont);
//        label.setForeground(new Color(50, 50, 50));
//        return label;
//    }
//
//    // ========== Core Logic ==========
//    private void loadLendingRecords() {
//        model.setRowCount(0);
//        try (Connection conn = DBConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM lendings")) {
//
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getInt("book_id"),
//                        rs.getInt("member_id"),
//                        rs.getDate("issue_date"),
//                        rs.getDate("return_date")
//                });
//            }
//
//        } catch (SQLException e) {
//            showError("Error loading lending records: " + e.getMessage());
//        }
//    }
//
////    private void issueBook() {
////        try (Connection conn = DBConnection.getConnection();
////             PreparedStatement ps = conn.prepareStatement(
////                     "INSERT INTO lendings (book_id, member_id, issue_date, return_date) VALUES (?, ?, ?, ?)")) {
////            ps.setInt(1, Integer.parseInt(txtBookID.getText()));
////            ps.setInt(2, Integer.parseInt(txtMemberID.getText()));
////            ps.setDate(3, Date.valueOf(txtIssueDate.getText()));
////            ps.setDate(4, Date.valueOf(txtReturnDate.getText()));
////            
////            
////
////            if (ps.executeUpdate() > 0) {
////                
////                showMessage("Book issued successfully.");
////                loadLendingRecords();
////                clearFields();
////            }
////        } catch (SQLException | IllegalArgumentException e) {
////            showError("Error issuing book: " + e.getMessage());
////        }
////   }
////    
//
//
//
//
//private void issueBook() {
//    try (Connection conn = DBConnection.getConnection();
//         PreparedStatement ps = conn.prepareStatement(
//                 "INSERT INTO lendings (book_id, member_id, issue_date, return_date) VALUES (?, ?, ?, ?)")) {
//
//        int bookId = Integer.parseInt(txtBookID.getText().trim());
//        int memberId = Integer.parseInt(txtMemberID.getText().trim());
//        Date issueDate = Date.valueOf(txtIssueDate.getText().trim());
//        Date returnDate = Date.valueOf(txtReturnDate.getText().trim());
//
//        ps.setInt(1, bookId);
//        ps.setInt(2, memberId);
//        ps.setDate(3, issueDate);
//        ps.setDate(4, returnDate);
//
//        if (ps.executeUpdate() > 0) {
//            decrementBookAvailability(bookId);
//
//          
//
//            showMessage("Book issued successfully.");
//            loadLendingRecords();
//            clearFields();
//        }
//    } catch (SQLException | IllegalArgumentException e) {
//        showError("Error issuing book: " + e.getMessage());
//    }
//}
//
//
//    
//    
//    
//    private void returnBook() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            showError("Please select a record to return.");
//            return;
//        }
//        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
//
//        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to return this book?", "Confirm Return", JOptionPane.YES_NO_OPTION);
//        if (confirm != JOptionPane.YES_OPTION) return;
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM lendings WHERE id = ?")) {
//            ps.setInt(1, id);
//
//            if (ps.executeUpdate() > 0) {
//                showMessage("Book returned successfully.");
//                loadLendingRecords();
//                clearFields();
//            }
//        } catch (SQLException e) {
//            showError("Error returning book: " + e.getMessage());
//        }
//    }
//
//    private void searchRecords() {
//        String searchTerm = txtSearch.getText().trim();
//        model.setRowCount(0);
//        if (searchTerm.isEmpty()) {
//            loadLendingRecords();
//            return;
//        }
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT * FROM lendings WHERE member_id = ?")) {
//            ps.setInt(1, Integer.parseInt(searchTerm));
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getInt("book_id"),
//                        rs.getInt("member_id"),
//                        rs.getDate("issue_date"),
//                        rs.getDate("return_date")
//                });
//            }
//
//        } catch (SQLException | NumberFormatException e) {
//            showError("Error searching records: " + e.getMessage());
//        }
//    }
//
//    private void clearFields() {
//        txtBookID.setText("");
//        txtMemberID.setText("");
//        txtIssueDate.setText("");
//        txtReturnDate.setText("");
//    }
//
//    private void showMessage(String msg) {
//        JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    private void showError(String msg) {
//        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
//    }
//private void decrementBookAvailability(int bookId) {
//    try (Connection conn = DBConnection.getConnection();
//         PreparedStatement ps = conn.prepareStatement(
//                 "UPDATE books SET available = available - 1 WHERE id = ? AND available > 0")) {
//
//        ps.setInt(1, bookId);
//        ps.executeUpdate();
//
//    } catch (SQLException e) {
//        showError("Error updating book availability: " + e.getMessage());
//    }
//}
//
//}






package gui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LendingForm extends JPanel {

    private JTextField txtBookID, txtMemberID, txtIssueDate, txtReturnDate, txtSearch;
    private JButton btnIssue, btnReturn, btnSearch;
    private JTable table;
    private DefaultTableModel model;

    // Color Palette: Old Money Theme
    private final Color bgColor = new Color(245, 245, 240);
    private final Color borderColor = new Color(200, 200, 180);
    private final Color headerColor = new Color(90, 120, 90);
    private final Color buttonColor = new Color(180, 200, 180);
    private final Color buttonHoverColor = new Color(160, 180, 160);
    private final Color buttonTextColor = new Color(40, 60, 40);
    private final Font mainFont = new Font("Serif", Font.PLAIN, 16);
    private final Font titleFont = new Font("Serif", Font.BOLD, 18);

    public LendingForm() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(bgColor);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(bgColor);
        TitledBorder inputBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2), "Lending Details");
        inputBorder.setTitleFont(titleFont);
        inputPanel.setBorder(inputBorder);

        txtBookID = new JTextField();
        txtMemberID = new JTextField();
        txtIssueDate = new JTextField();
        txtReturnDate = new JTextField();

        inputPanel.add(createStyledLabel("Book ID:"));
        inputPanel.add(txtBookID);
        inputPanel.add(createStyledLabel("Member ID:"));
        inputPanel.add(txtMemberID);
        inputPanel.add(createStyledLabel("Issue Date (YYYY-MM-DD):"));
        inputPanel.add(txtIssueDate);
        inputPanel.add(createStyledLabel("Return Date (YYYY-MM-DD):"));
        inputPanel.add(txtReturnDate);

        btnIssue = createStyledButton("Issue Book");
        btnReturn = createStyledButton("Return Book");

        inputPanel.add(btnIssue);
        inputPanel.add(btnReturn);

        String[] columns = {"ID", "Book ID", "Member ID", "Issue Date", "Return Date", "Fine"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFont(mainFont);
        table.setRowHeight(22);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setOpaque(true);
        tableHeader.setBackground(headerColor);
        tableHeader.setForeground(Color.BLACK);
        tableHeader.setFont(titleFont);
        tableHeader.setReorderingAllowed(false);

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(" " + value.toString() + " ");
                label.setFont(titleFont);
                label.setOpaque(true);
                label.setBackground(headerColor);
                label.setForeground(Color.BLACK);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });

        JScrollPane tableScroll = new JScrollPane(table);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(bgColor);
        txtSearch = new JTextField(20);
        btnSearch = createStyledButton("Search by Member ID");

        searchPanel.add(createStyledLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        add(inputPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);

        loadLendingRecords();

        btnIssue.addActionListener(e -> issueBook());
        btnReturn.addActionListener(e -> returnBook());
        btnSearch.addActionListener(e -> searchRecords());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtBookID.setText(model.getValueAt(row, 1).toString());
                    txtMemberID.setText(model.getValueAt(row, 2).toString());
                    txtIssueDate.setText(model.getValueAt(row, 3).toString());
                    txtReturnDate.setText(model.getValueAt(row, 4) != null ? model.getValueAt(row, 4).toString() : "");
                }
            }
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            private boolean hover = false;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (hover) {
                    g2.setColor(buttonHoverColor);
                } else {
                    g2.setColor(buttonColor);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                g2.setColor(buttonTextColor);
                FontMetrics fm = g2.getFontMetrics();
                int stringWidth = fm.stringWidth(getText());
                int stringHeight = fm.getAscent();

                g2.setFont(getFont());
                g2.drawString(getText(), (getWidth() - stringWidth) / 2, (getHeight() + stringHeight) / 2 - 3);

                g2.dispose();
            }

            @Override
            public void updateUI() {
                super.updateUI();
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
                setFont(mainFont);
                setForeground(buttonTextColor);

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hover = false;
                        repaint();
                    }
                });
            }
        };
        return button;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(mainFont);
        label.setForeground(new Color(50, 50, 50));
        return label;
    }

    private void loadLendingRecords() {
        model.setRowCount(0);
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM lendings")) {

            while (rs.next()) {
                Date returnDate = rs.getDate("return_date");
                long fine = 0;
                if (returnDate != null && returnDate.toLocalDate().isBefore(LocalDate.now())) {
                    long daysLate = ChronoUnit.DAYS.between(returnDate.toLocalDate(), LocalDate.now());
                    fine = daysLate * 10;
                }

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date"),
                        returnDate,
                        "Rs. " + fine
                });
            }

        } catch (SQLException e) {
            showError("Error loading lending records: " + e.getMessage());
        }
    }

    private void issueBook() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO lendings (book_id, member_id, issue_date, return_date) VALUES (?, ?, ?, ?)")) {

            int bookId = Integer.parseInt(txtBookID.getText().trim());
            int memberId = Integer.parseInt(txtMemberID.getText().trim());
            Date issueDate = Date.valueOf(txtIssueDate.getText().trim());
            Date returnDate = Date.valueOf(txtReturnDate.getText().trim());

            // Check availability before issuing
            if (!isBookAvailable(bookId)) {
                showError("Book is not available for lending.");
                return;
            }

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.setDate(3, issueDate);
            ps.setDate(4, returnDate);

            if (ps.executeUpdate() > 0) {
                decrementBookAvailability(bookId);
                showMessage("Book issued successfully.");
                loadLendingRecords();
                clearFields();
            }
        } catch (SQLException | IllegalArgumentException e) {
            showError("Error issuing book: " + e.getMessage());
        }
    }

    private void returnBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            showError("Please select a record to return.");
            return;
        }
        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
        int bookId = Integer.parseInt(model.getValueAt(row, 1).toString());

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to return this book?", "Confirm Return", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM lendings WHERE id = ?")) {
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                incrementBookAvailability(bookId);
                showMessage("Book returned successfully.");
                loadLendingRecords();
                clearFields();
            }
        } catch (SQLException e) {
            showError("Error returning book: " + e.getMessage());
        }
    }

    private boolean isBookAvailable(int bookId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT available FROM books WHERE id = ?")) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("available") > 0;
            }
        } catch (SQLException e) {
            showError("Error checking book availability: " + e.getMessage());
        }
        return false;
    }

    private void searchRecords() {
        String searchTerm = txtSearch.getText().trim();
        model.setRowCount(0);
        if (searchTerm.isEmpty()) {
            loadLendingRecords();
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM lendings WHERE member_id = ?")) {
            ps.setInt(1, Integer.parseInt(searchTerm));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date returnDate = rs.getDate("return_date");
                long fine = 0;
                if (returnDate != null && returnDate.toLocalDate().isBefore(LocalDate.now())) {
                    long daysLate = ChronoUnit.DAYS.between(returnDate.toLocalDate(), LocalDate.now());
                    fine = daysLate * 10;
                }

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date"),
                        returnDate,
                        "Rs. " + fine
                });
            }

        } catch (SQLException | NumberFormatException e) {
            showError("Error searching records: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtBookID.setText("");
        txtMemberID.setText("");
        txtIssueDate.setText("");
        txtReturnDate.setText("");
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void decrementBookAvailability(int bookId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psCheck = conn.prepareStatement("SELECT available FROM books WHERE id = ?");
             PreparedStatement psUpdate = conn.prepareStatement("UPDATE books SET available = available - 1 WHERE id = ? AND available > 0")) {
            psCheck.setInt(1, bookId);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                int available = rs.getInt("available");
                if (available > 0) {
                    psUpdate.setInt(1, bookId);
                    int updated = psUpdate.executeUpdate();
                    if (updated == 0) {
                        showError("Book stock could not be decremented, possibly out of stock.");
                    }
                } else {
                    showError("Book is out of stock.");
                }
            }
        } catch (SQLException e) {
            showError("Error updating book availability: " + e.getMessage());
        }
    }

    private void incrementBookAvailability(int bookId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE books SET available = available + 1 WHERE id = ?")) {
            ps.setInt(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            showError("Error updating book availability: " + e.getMessage());
        }
    }
}

