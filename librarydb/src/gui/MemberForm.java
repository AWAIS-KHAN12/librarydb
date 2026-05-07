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
//public class MemberForm extends JPanel {
//
//    private JTextField txtName, txtEmail, txtPhone, txtSearch;
//    private JButton btnAdd, btnUpdate, btnDelete, btnViewHistory, btnSearch;
//    private JTable table;
//    private DefaultTableModel model;
//
//    // Old Money Theme
//    private final Color bgColor = new Color(245, 245, 240);
//    private final Color borderColor = new Color(200, 200, 180);
//    private final Color headerColor = new Color(90, 120, 90);
//    private final Color buttonColor = new Color(180, 200, 180);
//    private final Color buttonTextColor = new Color(40, 60, 40);
//    private final Font mainFont = new Font("Serif", Font.PLAIN, 16);
//    private final Font titleFont = new Font("Serif", Font.BOLD, 18);
//
//    public MemberForm() {
//        setLayout(new BorderLayout(10, 10));
//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        setBackground(bgColor);
//
//        // ========== Input Panel ==========
//        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
//        inputPanel.setBackground(bgColor);
//        TitledBorder inputBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2), "Member Details");
//        inputBorder.setTitleFont(titleFont);
//        inputPanel.setBorder(inputBorder);
//
//        txtName = new JTextField();
//        txtEmail = new JTextField();
//        txtPhone = new JTextField();
//
//        inputPanel.add(createStyledLabel("Name:"));
//        inputPanel.add(txtName);
//        inputPanel.add(createStyledLabel("Email:"));
//        inputPanel.add(txtEmail);
//        inputPanel.add(createStyledLabel("Phone:"));
//        inputPanel.add(txtPhone);
//
//        btnAdd = createStyledButton("Add");
//        btnUpdate = createStyledButton("Update");
//
//        inputPanel.add(btnAdd);
//        inputPanel.add(btnUpdate);
//
//        // ========== Table Panel ==========
//        String[] columns = {"ID", "Name", "Email", "Phone"};
//        model = new DefaultTableModel(columns, 0) {
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        table = new JTable(model);
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
//        // ========== Bottom Panel ==========
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        bottomPanel.setBackground(bgColor);
//
//        txtSearch = new JTextField(20);
//        btnSearch = createStyledButton("Search by Name/Email");
//        btnDelete = createStyledButton("Delete");
//        btnViewHistory = createStyledButton("View History");
//
//        bottomPanel.add(createStyledLabel("Search:"));
//        bottomPanel.add(txtSearch);
//        bottomPanel.add(btnSearch);
//        bottomPanel.add(btnDelete);
//        bottomPanel.add(btnViewHistory);
//
//        // ========== Add Panels ==========
//        add(inputPanel, BorderLayout.NORTH);
//        add(tableScroll, BorderLayout.CENTER);
//        add(bottomPanel, BorderLayout.SOUTH);
//
//        loadMembers();
//
//        // ========== Event Listeners ==========
//        btnAdd.addActionListener(e -> addMember());
//        btnUpdate.addActionListener(e -> updateMember());
//        btnDelete.addActionListener(e -> deleteMember());
//        btnSearch.addActionListener(e -> searchMembers());
//        btnViewHistory.addActionListener(e -> viewHistory());
//
//        table.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int row = table.getSelectedRow();
//                if (row != -1) {
//                    txtName.setText(model.getValueAt(row, 1).toString());
//                    txtEmail.setText(model.getValueAt(row, 2).toString());
//                    txtPhone.setText(model.getValueAt(row, 3).toString());
//                }
//            }
//        });
//    }
//
//    private JLabel createStyledLabel(String text) {
//        JLabel label = new JLabel(text);
//        label.setFont(mainFont);
//        label.setForeground(new Color(50, 50, 50));
//        return label;
//    }
//
//    // Improved styled button with rounded edges and hover effect
//    private JButton createStyledButton(String text) {
//        JButton button = new JButton(text) {
//            @Override
//            protected void paintComponent(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g.create();
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                g2.setColor(buttonColor);
//                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
//                super.paintComponent(g2);
//                g2.dispose();
//            }
//        };
//
//        button.setForeground(buttonTextColor);
//        button.setFont(mainFont);
//        button.setFocusPainted(false);
//        button.setContentAreaFilled(false);
//        button.setOpaque(false);
//        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
//        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//
//        button.addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent e) {
//                button.setForeground(Color.BLACK);
//            }
//
//            public void mouseExited(MouseEvent e) {
//                button.setForeground(buttonTextColor);
//            }
//        });
//
//        return button;
//    }
//
//    private void loadMembers() {
//        model.setRowCount(0);
//        try (Connection conn = DBConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM members")) {
//
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("email"),
//                        rs.getString("phone")
//                });
//            }
//
//        } catch (SQLException e) {
//            showError("Error loading members: " + e.getMessage());
//        }
//    }
//
//    private void addMember() {
//        if (!validateInputs()) return;
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)")) {
//
//            ps.setString(1, txtName.getText().trim());
//            ps.setString(2, txtEmail.getText().trim());
//            ps.setString(3, txtPhone.getText().trim());
//
//            if (ps.executeUpdate() > 0) {
//                showMessage("Member added!");
//                loadMembers();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error adding member: " + e.getMessage());
//        }
//    }
//
//    private void updateMember() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            showError("Please select a member to update.");
//            return;
//        }
//        if (!validateInputs()) return;
//
//        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "UPDATE members SET name=?, email=?, phone=? WHERE id=?")) {
//
//            ps.setString(1, txtName.getText().trim());
//            ps.setString(2, txtEmail.getText().trim());
//            ps.setString(3, txtPhone.getText().trim());
//            ps.setInt(4, id);
//
//            if (ps.executeUpdate() > 0) {
//                showMessage("Member updated!");
//                loadMembers();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error updating member: " + e.getMessage());
//        }
//    }
//
//    private void deleteMember() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            showError("Please select a member to delete.");
//            return;
//        }
//        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
//
//        int confirm = JOptionPane.showConfirmDialog(this,
//                "Are you sure you want to delete this member?",
//                "Delete Member",
//                JOptionPane.YES_NO_OPTION);
//        if (confirm != JOptionPane.YES_OPTION) return;
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM members WHERE id=?")) {
//
//            ps.setInt(1, id);
//            if (ps.executeUpdate() > 0) {
//                showMessage("Member deleted.");
//                loadMembers();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error deleting member: " + e.getMessage());
//        }
//    }
//
//    private void searchMembers() {
//        String keyword = txtSearch.getText().trim();
//        model.setRowCount(0);
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "SELECT * FROM members WHERE name LIKE ? OR email LIKE ?")) {
//
//            ps.setString(1, "%" + keyword + "%");
//            ps.setString(2, "%" + keyword + "%");
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    model.addRow(new Object[]{
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("email"),
//                            rs.getString("phone")
//                    });
//                }
//            }
//
//        } catch (SQLException e) {
//            showError("Error searching members: " + e.getMessage());
//        }
//    }
//
//    private void viewHistory() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            showError("Please select a member to view history.");
//            return;
//        }
//        int memberId = Integer.parseInt(model.getValueAt(row, 0).toString());
//        JOptionPane.showMessageDialog(this, "Viewing history for member ID: " + memberId);
//    }
//
//    private boolean validateInputs() {
//        if (txtName.getText().trim().isEmpty()) {
//            showError("Name is required.");
//            txtName.requestFocus();
//            return false;
//        }
//        if (txtEmail.getText().trim().isEmpty()) {
//            showError("Email is required.");
//            txtEmail.requestFocus();
//            return false;
//        }
//        if (txtPhone.getText().trim().isEmpty()) {
//            showError("Phone is required.");
//            txtPhone.requestFocus();
//            return false;
//        }
//        return true;
//    }
//
//    private void clearFields() {
//        txtName.setText("");
//        txtEmail.setText("");
//        txtPhone.setText("");
//        txtSearch.setText("");
//        table.clearSelection();
//    }
//
//    private void showMessage(String message) {
//        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    private void showError(String error) {
//        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
//    }
//}
//
//

package gui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MemberForm extends JPanel {

    private JTextField txtName, txtEmail, txtPhone, txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnViewHistory, btnSearch;
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTotalMembers;

    // Old Money Theme
    private final Color bgColor = new Color(245, 245, 240);
    private final Color borderColor = new Color(200, 200, 180);
    private final Color headerColor = new Color(90, 120, 90);
    private final Color buttonColor = new Color(180, 200, 180);
    private final Color buttonTextColor = new Color(40, 60, 40);
    private final Font mainFont = new Font("Serif", Font.PLAIN, 16);
    private final Font titleFont = new Font("Serif", Font.BOLD, 18);

    public MemberForm() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(bgColor);

        // ========== Input Panel ==========
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(bgColor);
        TitledBorder inputBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2), "Member Details");
        inputBorder.setTitleFont(titleFont);
        inputPanel.setBorder(inputBorder);

        txtName = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();

        inputPanel.add(createStyledLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(createStyledLabel("Email:"));
        inputPanel.add(txtEmail);
        inputPanel.add(createStyledLabel("Phone:"));
        inputPanel.add(txtPhone);

        btnAdd = createStyledButton("Add");
        btnUpdate = createStyledButton("Update");

        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);

        // ========== Table Panel ==========
        String[] columns = {"ID", "Name", "Email", "Phone"};  // Removed "Books Lent"
        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
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

        // ========== Bottom Panel ==========
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(bgColor);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(bgColor);

        txtSearch = new JTextField(20);
        btnSearch = createStyledButton("Search by Name/Email");
        btnDelete = createStyledButton("Delete");
        btnViewHistory = createStyledButton("View History");

        leftPanel.add(createStyledLabel("Search:"));
        leftPanel.add(txtSearch);
        leftPanel.add(btnSearch);
        leftPanel.add(btnDelete);
        leftPanel.add(btnViewHistory);

        lblTotalMembers = createStyledLabel("Total Members: 0");
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(bgColor);
        rightPanel.add(lblTotalMembers);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(rightPanel, BorderLayout.EAST);

        // ========== Add Panels ==========
        add(inputPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadMembers();

        // ========== Event Listeners ==========
        btnAdd.addActionListener(e -> addMember());
        btnUpdate.addActionListener(e -> updateMember());
        btnDelete.addActionListener(e -> deleteMember());
        btnSearch.addActionListener(e -> searchMembers());
        btnViewHistory.addActionListener(e -> viewHistory());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtName.setText(model.getValueAt(row, 1).toString());
                    txtEmail.setText(model.getValueAt(row, 2).toString());
                    txtPhone.setText(model.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(mainFont);
        label.setForeground(new Color(50, 50, 50));
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(buttonColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g2);
                g2.dispose();
            }
        };

        button.setForeground(buttonTextColor);
        button.setFont(mainFont);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent e) {
                button.setForeground(buttonTextColor);
            }
        });

        return button;
    }

    private void loadMembers() {
        model.setRowCount(0);
        int total = 0;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM members")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                });
                total++;
            }

            lblTotalMembers.setText("Total Members: " + total);

        } catch (SQLException e) {
            showError("Error loading members: " + e.getMessage());
        }
    }

    private void searchMembers() {
        String keyword = txtSearch.getText().trim();
        model.setRowCount(0);
        int total = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM members WHERE name LIKE ? OR email LIKE ?")) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone")
                    });
                    total++;
                }
            }

            lblTotalMembers.setText("Total Members: " + total);

        } catch (SQLException e) {
            showError("Error searching members: " + e.getMessage());
        }
    }

    private void addMember() {
        if (!validateInputs()) return;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)")) {

            ps.setString(1, txtName.getText().trim());
            ps.setString(2, txtEmail.getText().trim());
            ps.setString(3, txtPhone.getText().trim());

            if (ps.executeUpdate() > 0) {
                showMessage("Member added!");
                loadMembers();
                clearFields();
            }

        } catch (SQLException e) {
            showError("Error adding member: " + e.getMessage());
        }
    }

    private void updateMember() {
        int row = table.getSelectedRow();
        if (row == -1) {
            showError("Please select a member to update.");
            return;
        }
        if (!validateInputs()) return;

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE members SET name=?, email=?, phone=? WHERE id=?")) {

            ps.setString(1, txtName.getText().trim());
            ps.setString(2, txtEmail.getText().trim());
            ps.setString(3, txtPhone.getText().trim());
            ps.setInt(4, id);

            if (ps.executeUpdate() > 0) {
                showMessage("Member updated!");
                loadMembers();
                clearFields();
            }

        } catch (SQLException e) {
            showError("Error updating member: " + e.getMessage());
        }
    }

    private void deleteMember() {
        int row = table.getSelectedRow();
        if (row == -1) {
            showError("Please select a member to delete.");
            return;
        }
        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this member?",
                "Delete Member",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM members WHERE id=?")) {

            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                showMessage("Member deleted.");
                loadMembers();
                clearFields();
            }

        } catch (SQLException e) {
            showError("Error deleting member: " + e.getMessage());
        }
    }

    private void viewHistory() {
        int row = table.getSelectedRow();
        if (row == -1) {
            showError("Please select a member to view history.");
            return;
        }
        int memberId = Integer.parseInt(model.getValueAt(row, 0).toString());
        JOptionPane.showMessageDialog(this, "Viewing history for member ID: " + memberId);
    }

    private boolean validateInputs() {
        if (txtName.getText().trim().isEmpty()) {
            showError("Name is required.");
            txtName.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            showError("Email is required.");
            txtEmail.requestFocus();
            return false;
        }
        if (txtPhone.getText().trim().isEmpty()) {
            showError("Phone is required.");
            txtPhone.requestFocus();
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtSearch.setText("");
        table.clearSelection();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}


