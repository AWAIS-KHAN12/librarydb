/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package gui;
//
//import javax.swing.*;
//import javax.swing.plaf.basic.BasicTabbedPaneUI;
//import java.awt.*;
//
//public class MainDashboard extends JFrame {
//
//    private JTabbedPane tabbedPane;
//    private BookInventoryForm bookInventoryForm;
//    private MemberForm memberForm;
//    private LendingForm lendingForm;
//
//    // Color palette
//    private final Color backgroundColor = new Color(245, 245, 240);   // Ivory
//    private final Color primaryGreen = new Color(34, 57, 49);         // Forest green
//    private final Color accentGold = new Color(192, 158, 84);         // Muted gold
//    private final Color shadowColor = new Color(220, 220, 210);       // Soft shadow
//    private final Font titleFont = new Font("Serif", Font.BOLD, 28);
//    private final Font tabFont = new Font("Georgia", Font.BOLD, 16);
//
//    public MainDashboard() {
//        setTitle("📚 Library Management System - Dashboard");
//        setSize(1200, 750);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        initUI();
//    }
//
//    private void initUI() {
//        getContentPane().setBackground(backgroundColor);
//        setLayout(new BorderLayout());
//
//        // Banner Panel (top)
//        JPanel bannerPanel = new JPanel(new BorderLayout());
//        bannerPanel.setBackground(backgroundColor);
//        bannerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//
//        // Logo icon on the left
//        JLabel logoLabel = new JLabel();
//        logoLabel.setIcon(new ImageIcon("resources/logo.png")); 
//        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
//        bannerPanel.add(logoLabel, BorderLayout.WEST);
//
//        // Title in center
//        JLabel titleLabel = new JLabel("📚 Library Management System", SwingConstants.CENTER);
//        titleLabel.setFont(titleFont);
//        titleLabel.setForeground(primaryGreen);
//        bannerPanel.add(titleLabel, BorderLayout.CENTER);
//
//        // Banner image on right
//        JLabel bannerImageLabel = new JLabel();
//        bannerImageLabel.setIcon(new ImageIcon("resources/banner.jpg")); // Replace with actual path
//        bannerImageLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
//        bannerPanel.add(bannerImageLabel, BorderLayout.EAST);
//
//        add(bannerPanel, BorderLayout.NORTH);
//
//        // Tabbed Pane
//        tabbedPane = new JTabbedPane();
//        tabbedPane.setFont(tabFont);
//        tabbedPane.setForeground(Color.WHITE);
//        tabbedPane.setBackground(primaryGreen);
//        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        // Forms
//        bookInventoryForm = new BookInventoryForm();
//        memberForm = new MemberForm();
//        lendingForm = new LendingForm();
//
//        tabbedPane.addTab("📖 Books", null, bookInventoryForm, "Manage Book Inventory");
//        tabbedPane.addTab("👤 Members", null, memberForm, "Manage Members");
//        tabbedPane.addTab("📬 Lending", null, lendingForm, "Issue / Return Books");
//        
//        
//
//        // Tab styling
//        tabbedPane.setUI(new BasicTabbedPaneUI() {
//            protected void installDefaults() {
//                super.installDefaults();
//                tabAreaInsets = new Insets(10, 10, 10, 10);
//                contentBorderInsets = new Insets(10, 10, 10, 10);
//            }
//
//            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
//                g.setColor(shadowColor);
//                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
//            }
//
//            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
//                                              int x, int y, int w, int h, boolean isSelected) {
//                Graphics2D g2 = (Graphics2D) g;
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                g2.setColor(isSelected ? accentGold : primaryGreen);
//                g2.fillRoundRect(x + 2, y + 2, w - 4, h - 4, 20, 20);
//            }
//
//            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
//                                          int x, int y, int w, int h, boolean isSelected) {
//                g.setColor(backgroundColor);
//                g.drawRoundRect(x + 2, y + 2, w - 4, h - 4, 20, 20);
//            }
//
//            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
//                return 45;
//            }
//
//            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
//                return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 30;
//            }
//
//            protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects,
//                                               int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
//                // No focus indicator
//            }
//        });
//
//        add(tabbedPane, BorderLayout.CENTER);
//    }
//
//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception ignored) {
//        }
//
//        SwingUtilities.invokeLater(() -> {
//            MainDashboard dashboard = new MainDashboard();
//            dashboard.setVisible(true);
//        });
//    }
//} 


package gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import utils.ReportGenerator;

public class MainDashboard extends JFrame {

    private JTabbedPane tabbedPane;
    private BookInventoryForm bookInventoryForm;
    private MemberForm memberForm;
    private LendingForm lendingForm;
    private JButton btnReport;

    // Color palette
    private final Color backgroundColor = new Color(245, 245, 240);   // Ivory
    private final Color primaryGreen = new Color(34, 57, 49);         // Forest green
    private final Color accentGold = new Color(192, 158, 84);         // Muted gold
    private final Color shadowColor = new Color(220, 220, 210);       // Soft shadow
    private final Font titleFont = new Font("Serif", Font.BOLD, 28);
    private final Font tabFont = new Font("Georgia", Font.BOLD, 16);

    public MainDashboard() {
        setTitle("📚 Library Management System - Dashboard");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        getContentPane().setBackground(backgroundColor);
        setLayout(new BorderLayout());

        // Banner Panel (top)
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.setBackground(backgroundColor);
        bannerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Logo icon on the left
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon("resources/logo.png")); 
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        bannerPanel.add(logoLabel, BorderLayout.WEST);

        // Title in center
        JLabel titleLabel = new JLabel("📚 Library Management System", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryGreen);
        bannerPanel.add(titleLabel, BorderLayout.CENTER);

        // Banner image on right
        JLabel bannerImageLabel = new JLabel();
        bannerImageLabel.setIcon(new ImageIcon("resources/banner.jpg")); // Replace with actual path
        bannerImageLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        bannerPanel.add(bannerImageLabel, BorderLayout.EAST);

        add(bannerPanel, BorderLayout.NORTH);

        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(tabFont);
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setBackground(primaryGreen);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Forms
        bookInventoryForm = new BookInventoryForm();
        memberForm = new MemberForm();
        lendingForm = new LendingForm();

        tabbedPane.addTab("📖 Books", null, bookInventoryForm, "Manage Book Inventory");
        tabbedPane.addTab("👤 Members", null, memberForm, "Manage Members");
        tabbedPane.addTab("📬 Lending", null, lendingForm, "Issue / Return Books");

        // Tab styling
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            protected void installDefaults() {
                super.installDefaults();
                tabAreaInsets = new Insets(10, 10, 10, 10);
                contentBorderInsets = new Insets(10, 10, 10, 10);
            }

            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                g.setColor(shadowColor);
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }

            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                                              int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isSelected ? accentGold : primaryGreen);
                g2.fillRoundRect(x + 2, y + 2, w - 4, h - 4, 20, 20);
            }

            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                                          int x, int y, int w, int h, boolean isSelected) {
                g.setColor(backgroundColor);
                g.drawRoundRect(x + 2, y + 2, w - 4, h - 4, 20, 20);
            }

            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return 45;
            }

            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
                return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 30;
            }

            protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects,
                                               int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
                // No focus indicator
            }
        });

        add(tabbedPane, BorderLayout.CENTER);

        // ===== Report Button Panel at Bottom =====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(backgroundColor);

        btnReport = new JButton("📄 Generate Report");
        btnReport.setFont(tabFont);
        btnReport.setBackground(accentGold);
        btnReport.setForeground(Color.WHITE);
        btnReport.setFocusPainted(false);

        btnReport.addActionListener(e -> ReportGenerator.generatePDFReport());

        bottomPanel.add(btnReport);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            MainDashboard dashboard = new MainDashboard();
            dashboard.setVisible(true);
        });
    }
}
