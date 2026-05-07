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
//public class BookInventoryForm extends JPanel {
//
//    private JTextField txtTitle, txtAuthor, txtISBN, txtAvailable, txtSearch;
//    private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
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
//    public BookInventoryForm() {
//        setLayout(new BorderLayout(10, 10));
//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        setBackground(bgColor);
//
//        // ================== Top Panel: Input Fields ==================
//        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
//        inputPanel.setBackground(bgColor);
//        TitledBorder inputBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2), "Book Details");
//        inputBorder.setTitleFont(titleFont);
//        inputPanel.setBorder(inputBorder);
//
//        txtTitle = new JTextField();
//        txtAuthor = new JTextField();
//        txtISBN = new JTextField();
//        txtAvailable = new JTextField();
//
//        inputPanel.add(createStyledLabel("Title:"));
//        inputPanel.add(txtTitle);
//        inputPanel.add(createStyledLabel("Author:"));
//        inputPanel.add(txtAuthor);
//        inputPanel.add(createStyledLabel("ISBN:"));
//        inputPanel.add(txtISBN);
//        inputPanel.add(createStyledLabel("Available:"));
//        inputPanel.add(txtAvailable);
//
//        btnAdd = createModernButton("Add");
//        btnUpdate = createModernButton("Update");
//
//        inputPanel.add(btnAdd);
//        inputPanel.add(btnUpdate);
//
//        // ================== Center Panel: Table ==================
//        String[] columns = {"ID", "Title", "Author", "ISBN", "Available"};
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
//        // ================== Bottom Panel: Search ==================
//        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        searchPanel.setBackground(bgColor);
//        txtSearch = new JTextField(20);
//        btnSearch = createModernButton("Search by Author/Available");
//        btnDelete = createModernButton("Delete");
//
//        searchPanel.add(createStyledLabel("Search:"));
//        searchPanel.add(txtSearch);
//        searchPanel.add(btnSearch);
//        searchPanel.add(btnDelete);
//
//        // ================== Layout ==================
//        add(inputPanel, BorderLayout.NORTH);
//        add(tableScroll, BorderLayout.CENTER);
//        add(searchPanel, BorderLayout.SOUTH);
//
//        loadBooks();
//
//        // ========== Event Listeners ==========
//        btnAdd.addActionListener(e -> addBook());
//        btnUpdate.addActionListener(e -> updateBook());
//        btnDelete.addActionListener(e -> deleteBook());
//        btnSearch.addActionListener(e -> searchBooks());
//
//        table.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int row = table.getSelectedRow();
//                if (row >= 0) {
//                    txtTitle.setText(model.getValueAt(row, 1).toString());
//                    txtAuthor.setText(model.getValueAt(row, 2).toString());
//                    txtISBN.setText(model.getValueAt(row, 3).toString());
//                    txtAvailable.setText(model.getValueAt(row, 4).toString());
//                }
//            }
//        });
//    }
//
//    private JLabel createStyledLabel(String text) {
//        JLabel label = new JLabel(text);
//        label.setFont(mainFont);
//        return label;
//    }
//
//    private JButton createModernButton(String text) {
//        JButton button = new JButton(text) {
//            private boolean hovered = false;
//
//            {
//                setContentAreaFilled(false);
//                setFocusPainted(false);
//                setBorder(BorderFactory.createLineBorder(borderColor, 1));
//                setFont(mainFont);
//                setForeground(buttonTextColor);
//                setPreferredSize(new Dimension(180, 40));
//
//                addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                        hovered = true;
//                        repaint();
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//                        hovered = false;
//                        repaint();
//                    }
//                });
//            }
//
//            @Override
//            protected void paintComponent(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g.create();
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//                GradientPaint gp = new GradientPaint(0, 0,
//                        hovered ? buttonHoverColor : buttonColor,
//                        0, getHeight(),
//                        bgColor);
//
//                g2.setPaint(gp);
//                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
//
//                super.paintComponent(g);
//                g2.dispose();
//            }
//
//            @Override
//            protected void paintBorder(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g.create();
//                g2.setColor(borderColor);
//                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
//                g2.dispose();
//            }
//        };
//
//        return button;
//    }
//
//    private void loadBooks() {
//        model.setRowCount(0);
//        try (Connection conn = DBConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT id, title, author, isbn, available FROM books")) {
//
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getString("title"),
//                        rs.getString("author"),
//                        rs.getString("isbn"),
//                        rs.getString("available")
//                });
//            }
//
//        } catch (SQLException e) {
//            showError("Error loading books: " + e.getMessage());
//        }
//    }
//
//    private void addBook() {
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "INSERT INTO books (title, author, isbn, available) VALUES (?, ?, ?, ?)")) {
//
//            ps.setString(1, txtTitle.getText().trim());
//            ps.setString(2, txtAuthor.getText().trim());
//            ps.setString(3, txtISBN.getText().trim());
//            ps.setString(4, txtAvailable.getText().trim());
//
//            if (ps.executeUpdate() > 0) {
//                showMessage("Book added!");
//                loadBooks();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error adding book: " + e.getMessage());
//        }
//    }
//
//    private void updateBook() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            showError("Please select a book to update.");
//            return;
//        }
//
//        int id = (int) model.getValueAt(row, 0);
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "UPDATE books SET title=?, author=?, isbn=?, available=? WHERE id=?")) {
//
//            ps.setString(1, txtTitle.getText().trim());
//            ps.setString(2, txtAuthor.getText().trim());
//            ps.setString(3, txtISBN.getText().trim());
//            ps.setString(4, txtAvailable.getText().trim());
//            ps.setInt(5, id);
//
//            if (ps.executeUpdate() > 0) {
//                showMessage("Book updated!");
//                loadBooks();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error updating book: " + e.getMessage());
//        }
//    }
//
//    private void deleteBook() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            showError("Please select a book to delete.");
//            return;
//        }
//
//        int id = (int) model.getValueAt(row, 0);
//
//        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Delete Book", JOptionPane.YES_NO_OPTION);
//        if (confirm != JOptionPane.YES_OPTION) return;
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM books WHERE id=?")) {
//
//            ps.setInt(1, id);
//            if (ps.executeUpdate() > 0) {
//                showMessage("Book deleted.");
//                loadBooks();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error deleting book: " + e.getMessage());
//        }
//    }
//
//    private void searchBooks() {
//        String keyword = txtSearch.getText().trim();
//        model.setRowCount(0);
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "SELECT id, title, author, isbn, available FROM books WHERE author LIKE ? OR available LIKE ?")) {
//
//            ps.setString(1, "%" + keyword + "%");
//            ps.setString(2, "%" + keyword + "%");
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getString("title"),
//                        rs.getString("author"),
//                        rs.getString("isbn"),
//                        rs.getString("available")
//                });
//            }
//
//        } catch (SQLException e) {
//            showError("Search error: " + e.getMessage());
//        }
//    }
//
//    private void clearFields() {
//        txtTitle.setText("");
//        txtAuthor.setText("");
//        txtISBN.setText("");
//        txtAvailable.setText("");
//        txtSearch.setText("");
//    }
//
//    private void showMessage(String message) {
//        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    private void showError(String error) {
//        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Book Inventory");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(900, 600);
//            frame.setLocationRelativeTo(null);
//            frame.add(new BookInventoryForm());
//            frame.setVisible(true);
//        });
//    }
//}
//
//
//
//
//


// Same package and imports...
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
//public class BookInventoryForm extends JPanel {
//
//    private JTextField txtTitle, txtAuthor, txtISBN, txtAvailable, txtSearch;
//    private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
//    private JTable table;
//    private DefaultTableModel model;
//    private JLabel lblTotalBooks;
//
//    // Color Palette
//    private final Color bgColor = new Color(245, 245, 240);
//    private final Color borderColor = new Color(200, 200, 180);
//    private final Color headerColor = new Color(90, 120, 90);
//    private final Color buttonColor = new Color(180, 200, 180);
//    private final Color buttonHoverColor = new Color(160, 180, 160);
//    private final Color buttonTextColor = new Color(40, 60, 40);
//    private final Font mainFont = new Font("Serif", Font.PLAIN, 16);
//    private final Font titleFont = new Font("Serif", Font.BOLD, 18);
//
//    public BookInventoryForm() {
//        setLayout(new BorderLayout(10, 10));
//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        setBackground(bgColor);
//
//        // ===== Top Panel =====
//        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
//        inputPanel.setBackground(bgColor);
//        TitledBorder inputBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2), "Book Details");
//        inputBorder.setTitleFont(titleFont);
//        inputPanel.setBorder(inputBorder);
//
//        txtTitle = new JTextField();
//        txtAuthor = new JTextField();
//        txtISBN = new JTextField();
//        txtAvailable = new JTextField();
//
//        inputPanel.add(createStyledLabel("Title:"));
//        inputPanel.add(txtTitle);
//        inputPanel.add(createStyledLabel("Author:"));
//        inputPanel.add(txtAuthor);
//        inputPanel.add(createStyledLabel("ISBN:"));
//        inputPanel.add(txtISBN);
//        inputPanel.add(createStyledLabel("Available:"));
//        inputPanel.add(txtAvailable);
//
//        btnAdd = createModernButton("Add");
//        btnUpdate = createModernButton("Update");
//
//        inputPanel.add(btnAdd);
//        inputPanel.add(btnUpdate);
//
//        // ===== Center Panel (Table) =====
//        String[] columns = {"ID", "Title", "Author", "ISBN", "Available"};
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
//        // ===== Bottom Panel =====
//        JPanel bottomPanel = new JPanel(new BorderLayout());
//        bottomPanel.setBackground(bgColor);
//
//        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        searchPanel.setBackground(bgColor);
//        txtSearch = new JTextField(20);
//        btnSearch = createModernButton("Search by Author/Available");
//        btnDelete = createModernButton("Delete");
//
//        searchPanel.add(createStyledLabel("Search:"));
//        searchPanel.add(txtSearch);
//        searchPanel.add(btnSearch);
//        searchPanel.add(btnDelete);
//
//        lblTotalBooks = new JLabel("Total Books: 0");
//        lblTotalBooks.setFont(titleFont);
//        lblTotalBooks.setHorizontalAlignment(SwingConstants.RIGHT);
//
//        bottomPanel.add(searchPanel, BorderLayout.WEST);
//        bottomPanel.add(lblTotalBooks, BorderLayout.EAST);
//
//        // ===== Layout =====
//        add(inputPanel, BorderLayout.NORTH);
//        add(tableScroll, BorderLayout.CENTER);
//        add(bottomPanel, BorderLayout.SOUTH);
//
//        loadBooks();
//
//        // ===== Listeners =====
//        btnAdd.addActionListener(e -> addBook());
//        btnUpdate.addActionListener(e -> updateBook());
//        btnDelete.addActionListener(e -> deleteBook());
//        btnSearch.addActionListener(e -> searchBooks());
//
//        table.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int row = table.getSelectedRow();
//                if (row >= 0) {
//                    txtTitle.setText(model.getValueAt(row, 1).toString());
//                    txtAuthor.setText(model.getValueAt(row, 2).toString());
//                    txtISBN.setText(model.getValueAt(row, 3).toString());
//                    txtAvailable.setText(model.getValueAt(row, 4).toString());
//                }
//            }
//        });
//    }
//
//    private JLabel createStyledLabel(String text) {
//        JLabel label = new JLabel(text);
//        label.setFont(mainFont);
//        return label;
//    }
//
//    private JButton createModernButton(String text) {
//        JButton button = new JButton(text) {
//            private boolean hovered = false;
//
//            {
//                setContentAreaFilled(false);
//                setFocusPainted(false);
//                setBorder(BorderFactory.createLineBorder(borderColor, 1));
//                setFont(mainFont);
//                setForeground(buttonTextColor);
//                setPreferredSize(new Dimension(180, 40));
//
//                addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                        hovered = true;
//                        repaint();
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//                        hovered = false;
//                        repaint();
//                    }
//                });
//            }
//
//            @Override
//            protected void paintComponent(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g.create();
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//                GradientPaint gp = new GradientPaint(0, 0,
//                        hovered ? buttonHoverColor : buttonColor,
//                        0, getHeight(),
//                        bgColor);
//
//                g2.setPaint(gp);
//                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
//                super.paintComponent(g);
//                g2.dispose();
//            }
//
//            @Override
//            protected void paintBorder(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g.create();
//                g2.setColor(borderColor);
//                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
//                g2.dispose();
//            }
//        };
//
//        return button;
//    }
//
//    void loadBooks() {
//        model.setRowCount(0);
//        try (Connection conn = DBConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT id, title, author, isbn, available FROM books")) {
//
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getString("title"),
//                        rs.getString("author"),
//                        rs.getString("isbn"),
//                        rs.getString("available")
//                });
//            }
//
//            updateTotalBooks();
//
//        } catch (SQLException e) {
//            showError("Error loading books: " + e.getMessage());
//        }
//    }
//
//    private void updateTotalBooks() {
//        int total = 0;
//        for (int i = 0; i < model.getRowCount(); i++) {
//            try {
//                total += Integer.parseInt(model.getValueAt(i, 4).toString());
//            } catch (NumberFormatException ignored) {}
//        }
//        lblTotalBooks.setText("Total Books: " + total);
//    }
//
//    private void addBook() {
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "INSERT INTO books (title, author, isbn, available) VALUES (?, ?, ?, ?)")) {
//
//            ps.setString(1, txtTitle.getText().trim());
//            ps.setString(2, txtAuthor.getText().trim());
//            ps.setString(3, txtISBN.getText().trim());
//            ps.setString(4, txtAvailable.getText().trim());
//
//            if (ps.executeUpdate() > 0) {
//                showMessage("Book added!");
//                loadBooks();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error adding book: " + e.getMessage());
//        }
//    }
//
//    private void updateBook() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            showError("Please select a book to update.");
//            return;
//        }
//
//        int id = (int) model.getValueAt(row, 0);
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "UPDATE books SET title=?, author=?, isbn=?, available=? WHERE id=?")) {
//
//            ps.setString(1, txtTitle.getText().trim());
//            ps.setString(2, txtAuthor.getText().trim());
//            ps.setString(3, txtISBN.getText().trim());
//            ps.setString(4, txtAvailable.getText().trim());
//            ps.setInt(5, id);
//
//            if (ps.executeUpdate() > 0) {
//                showMessage("Book updated!");
//                loadBooks();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error updating book: " + e.getMessage());
//        }
//    }
//
//    private void deleteBook() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            showError("Please select a book to delete.");
//            return;
//        }
//
//        int id = (int) model.getValueAt(row, 0);
//
//        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Delete Book", JOptionPane.YES_NO_OPTION);
//        if (confirm != JOptionPane.YES_OPTION) return;
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM books WHERE id=?")) {
//
//            ps.setInt(1, id);
//            if (ps.executeUpdate() > 0) {
//                showMessage("Book deleted.");
//                loadBooks();
//                clearFields();
//            }
//
//        } catch (SQLException e) {
//            showError("Error deleting book: " + e.getMessage());
//        }
//    }
//
//    private void searchBooks() {
//        String keyword = txtSearch.getText().trim();
//        model.setRowCount(0);
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(
//                     "SELECT id, title, author, isbn, available FROM books WHERE author LIKE ? OR available LIKE ?")) {
//
//            ps.setString(1, "%" + keyword + "%");
//            ps.setString(2, "%" + keyword + "%");
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getString("title"),
//                        rs.getString("author"),
//                        rs.getString("isbn"),
//                        rs.getString("available")
//                });
//            }
//
//            updateTotalBooks();
//
//        } catch (SQLException e) {
//            showError("Search error: " + e.getMessage());
//        }
//    }
//
//    private void clearFields() {
//        txtTitle.setText("");
//        txtAuthor.setText("");
//        txtISBN.setText("");
//        txtAvailable.setText("");
//        txtSearch.setText("");
//    }
//
//    private void showMessage(String message) {
//        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    private void showError(String error) {
//        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Book Inventory");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(900, 600);
//            frame.setLocationRelativeTo(null);
//            frame.add(new BookInventoryForm());
//            frame.setVisible(true);
//        });
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

public class BookInventoryForm extends JPanel {

    private JTextField txtTitle, txtAuthor, txtISBN, txtAvailable, txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTotalBooks;

    // Color Palette
    private final Color bgColor = new Color(245, 245, 240);
    private final Color borderColor = new Color(200, 200, 180);
    private final Color headerColor = new Color(90, 120, 90);
    private final Color buttonColor = new Color(180, 200, 180);
    private final Color buttonHoverColor = new Color(160, 180, 160);
    private final Color buttonTextColor = new Color(40, 60, 40);
    private final Font mainFont = new Font("Serif", Font.PLAIN, 16);
    private final Font titleFont = new Font("Serif", Font.BOLD, 18);

    public BookInventoryForm() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(bgColor);

        // ===== Top Panel =====
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(bgColor);
        TitledBorder inputBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2), "Book Details");
        inputBorder.setTitleFont(titleFont);
        inputPanel.setBorder(inputBorder);

        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtISBN = new JTextField();
        txtAvailable = new JTextField();

        inputPanel.add(createStyledLabel("Title:"));
        inputPanel.add(txtTitle);
        inputPanel.add(createStyledLabel("Author:"));
        inputPanel.add(txtAuthor);
        inputPanel.add(createStyledLabel("ISBN:"));
        inputPanel.add(txtISBN);
        inputPanel.add(createStyledLabel("Available:"));
        inputPanel.add(txtAvailable);

        btnAdd = createModernButton("Add");
        btnUpdate = createModernButton("Update");

        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);

        // ===== Center Panel (Table) =====
        String[] columns = {"ID", "Title", "Author", "ISBN", "Available"};
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

        // ===== Bottom Panel =====
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(bgColor);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(bgColor);
        txtSearch = new JTextField(20);
        btnSearch = createModernButton("Search by Author/Available");
        btnDelete = createModernButton("Delete");

        searchPanel.add(createStyledLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnDelete);

        lblTotalBooks = new JLabel("Total Books: 0");
        lblTotalBooks.setFont(titleFont);
        lblTotalBooks.setHorizontalAlignment(SwingConstants.RIGHT);

        bottomPanel.add(searchPanel, BorderLayout.WEST);
        bottomPanel.add(lblTotalBooks, BorderLayout.EAST);

        // ===== Layout =====
        add(inputPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadBooks();

        // ===== Listeners =====
        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnSearch.addActionListener(e -> searchBooks());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtTitle.setText(model.getValueAt(row, 1).toString());
                    txtAuthor.setText(model.getValueAt(row, 2).toString());
                    txtISBN.setText(model.getValueAt(row, 3).toString());
                    txtAvailable.setText(model.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(mainFont);
        return label;
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text) {
            private boolean hovered = false;

            {
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorder(BorderFactory.createLineBorder(borderColor, 1));
                setFont(mainFont);
                setForeground(buttonTextColor);
                setPreferredSize(new Dimension(180, 40));

                addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        hovered = true;
                        repaint();
                    }

                    public void mouseExited(MouseEvent e) {
                        hovered = false;
                        repaint();
                    }
                });
            }

            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(0, 0,
                        hovered ? buttonHoverColor : buttonColor,
                        0, getHeight(),
                        bgColor);

                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }

            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(borderColor);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
            }
        };

        return button;
    }

    void loadBooks() {
        model.setRowCount(0);
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, title, author, isbn, available FROM books")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("available")
                });
            }

            updateTotalBooks();

        } catch (SQLException e) {
            showError("Error loading books: " + e.getMessage());
        }
    }

    private void updateTotalBooks() {
        int total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                total += Integer.parseInt(model.getValueAt(i, 4).toString());
            } catch (NumberFormatException ignored) {
            }
        }
        lblTotalBooks.setText("Total Books: " + total);
    }

    private void addBook() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO books (title, author, isbn, available) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, txtTitle.getText().trim());
            ps.setString(2, txtAuthor.getText().trim());
            ps.setString(3, txtISBN.getText().trim());
            ps.setString(4, txtAvailable.getText().trim());

            if (ps.executeUpdate() > 0) {
                showMessage("Book added!");
                loadBooks();
                clearFields();
            }

        } catch (SQLException e) {
            showError("Error adding book: " + e.getMessage());
        }
    }

    private void updateBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            showError("Please select a book to update.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE books SET title=?, author=?, isbn=?, available=? WHERE id=?")) {

            ps.setString(1, txtTitle.getText().trim());
            ps.setString(2, txtAuthor.getText().trim());
            ps.setString(3, txtISBN.getText().trim());
            ps.setString(4, txtAvailable.getText().trim());
            ps.setInt(5, id);

            if (ps.executeUpdate() > 0) {
                showMessage("Book updated!");
                loadBooks();
                clearFields();
            }

        } catch (SQLException e) {
            showError("Error updating book: " + e.getMessage());
        }
    }

    private void deleteBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            showError("Please select a book to delete.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Delete Book", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM books WHERE id=?")) {

            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                showMessage("Book deleted.");
                loadBooks();
                clearFields();
            }

        } catch (SQLException e) {
            showError("Error deleting book: " + e.getMessage());
        }
    }

    private void searchBooks() {
        String keyword = txtSearch.getText().trim();
        model.setRowCount(0);
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT id, title, author, isbn, available FROM books WHERE author LIKE ? OR available LIKE ?")) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("available")
                });
            }

            updateTotalBooks();

        } catch (SQLException e) {
            showError("Search error: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtTitle.setText("");
        txtAuthor.setText("");
        txtISBN.setText("");
        txtAvailable.setText("");
        txtSearch.setText("");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Book Inventory");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 600);
                frame.setLocationRelativeTo(null);
                frame.add(new BookInventoryForm());
                frame.setVisible(true);
            }
        });
    }
}
