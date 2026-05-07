/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JCheckBox chkShowPassword;

    // Old Money Aesthetic Colors
    private static final Color BACKGROUND_COLOR = new Color(243, 241, 230);
    private static final Color BUTTON_COLOR = new Color(42, 72, 65);
    private static final Color BUTTON_HOVER_COLOR = new Color(34, 60, 54);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Color LABEL_COLOR = new Color(54, 47, 45);
    private static final Color INPUT_BORDER_COLOR = new Color(185, 180, 165);

    private static final Font TITLE_FONT = new Font("Georgia", Font.BOLD, 22);
    private static final Font LABEL_FONT = new Font("Georgia", Font.PLAIN, 15);
    private static final Font BUTTON_FONT = new Font("Georgia", Font.BOLD, 16);

    public LoginForm() {
        setTitle("Library Management System - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(false); // Optional: remove if using custom decorations

        initComponents();

        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 15));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        add(mainPanel);

        // Top Panel (Image + Title)
        JPanel topPanel = new JPanel(new BorderLayout(0, 10));
        topPanel.setBackground(BACKGROUND_COLOR);

        try {
            URL imageUrl = new URL("https://cdn-icons-png.flaticon.com/512/29/29302.png");
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel lblIcon = new JLabel(new ImageIcon(scaledImage));
            lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
            topPanel.add(lblIcon, BorderLayout.NORTH);
        } catch (Exception ex) {
            JLabel lblFallbackIcon = new JLabel("📚", SwingConstants.CENTER);
            lblFallbackIcon.setFont(new Font("Serif", Font.PLAIN, 48));
            topPanel.add(lblFallbackIcon, BorderLayout.NORTH);
        }

        JLabel lblTitle = new JLabel("Login to Library Management", SwingConstants.CENTER);
        lblTitle.setFont(TITLE_FONT);
        lblTitle.setForeground(LABEL_COLOR);
        topPanel.add(lblTitle, BorderLayout.SOUTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(LABEL_FONT);
        lblUsername.setForeground(LABEL_COLOR);
        formPanel.add(lblUsername, gbc);

        // Username Field
        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        txtUsername.setFont(LABEL_FONT);
        txtUsername.setBorder(new LineBorder(INPUT_BORDER_COLOR, 1, true));
        txtUsername.setMargin(new Insets(5, 8, 5, 8));
        formPanel.add(txtUsername, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(LABEL_FONT);
        lblPassword.setForeground(LABEL_COLOR);
        formPanel.add(lblPassword, gbc);

        // Password Field
        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(LABEL_FONT);
        txtPassword.setBorder(new LineBorder(INPUT_BORDER_COLOR, 1, true));
        txtPassword.setEchoChar('•');
        txtPassword.setMargin(new Insets(5, 8, 5, 8));
        formPanel.add(txtPassword, gbc);

        // Show Password Checkbox
        gbc.gridx = 1;
        gbc.gridy = 2;
        chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setBackground(BACKGROUND_COLOR);
        chkShowPassword.setFont(new Font("Georgia", Font.PLAIN, 12));
        formPanel.add(chkShowPassword, gbc);

        chkShowPassword.addActionListener(e -> {
            txtPassword.setEchoChar(chkShowPassword.isSelected() ? (char) 0 : '•');
        });

        // Button Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(BACKGROUND_COLOR);
        btnPanel.setBorder(new EmptyBorder(25, 0, 0, 0));
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        btnLogin = new JButton("Login");
        btnLogin.setFont(BUTTON_FONT);
        btnLogin.setBackground(BUTTON_COLOR);
        btnLogin.setForeground(BUTTON_TEXT_COLOR);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setMnemonic(KeyEvent.VK_ENTER);
        btnLogin.setBorder(new LineBorder(BUTTON_COLOR.darker(), 1, true));
        btnLogin.setPreferredSize(new Dimension(120, 40));

        // Hover effect
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(BUTTON_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(BUTTON_COLOR);
            }
        });

        getRootPane().setDefaultButton(btnLogin);
        btnLogin.addActionListener(this::handleLogin);
        btnPanel.add(btnLogin);
    }

    private void handleLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if ("admin".equals(username) && "1234".equals(password)) {
            JOptionPane.showMessageDialog(this,
                    "Login Successful! Welcome.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            new MainDashboard().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
