/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package utils;
//
///**
// *
// * @author Hashir
// */
//
//
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//
//import models.Book;
//import models.Member;
//import models.Lending;
//import services.BookService;
//import services.MemberService;
//import services.LendingService;
//
//import java.io.FileOutputStream;
//import java.util.List;
//
//public class ReportGenerator {
//
//    public static void generatePDFReport() {
//        Document document = new Document();
//
//        try {
//            PdfWriter.getInstance(document, new FileOutputStream("LibraryReport.pdf"));
//            document.open();
//
//            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
//            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
//            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12);
//
//            document.add(new Paragraph("Library Management System Report", titleFont));
//            document.add(Chunk.NEWLINE);
//
//            // ==== BOOK SECTION ====
//            document.add(new Paragraph("Books", sectionFont));
//            PdfPTable bookTable = new PdfPTable(5);
//            bookTable.addCell("ID");
//            bookTable.addCell("Title");
//            bookTable.addCell("Author");
//            bookTable.addCell("ISBN");
//            bookTable.addCell("Available");
//
//            List<Book> books = new BookService().getAllBooks();
//            for (Book book : books) {
//                bookTable.addCell(String.valueOf(book.getId()));
//                bookTable.addCell(book.getTitle());
//                bookTable.addCell(book.getAuthor());
//                bookTable.addCell(book.getIsbn());
//                bookTable.addCell(String.valueOf(book.getAvailable()));
//            }
//            document.add(bookTable);
//            document.add(Chunk.NEWLINE);
//
//            // ==== MEMBER SECTION ====
//            document.add(new Paragraph("Members", sectionFont));
//            PdfPTable memberTable = new PdfPTable(3);
//            memberTable.addCell("ID");
//            memberTable.addCell("Name");
//            memberTable.addCell("Email");
//
//            List<Member> members = new MemberService().getAllMembers();
//            for (Member m : members) {
//                memberTable.addCell(String.valueOf(m.getId()));
//                memberTable.addCell(m.getName());
//                memberTable.addCell(m.getEmail());
//            }
//            document.add(memberTable);
//            document.add(Chunk.NEWLINE);
//
//            // ==== LENDING SECTION ====
//            document.add(new Paragraph("Lending Records", sectionFont));
//            PdfPTable lendTable = new PdfPTable(5);
//            lendTable.addCell("ID");
//            lendTable.addCell("Book ID");
//            lendTable.addCell("Member ID");
//            lendTable.addCell("Issue Date");
//            lendTable.addCell("Return Date");
//
//            List<Lending> lendings = new LendingService().getAllLendings();
//            for (Lending l : lendings) {
//                lendTable.addCell(String.valueOf(l.getId()));
//                lendTable.addCell(String.valueOf(l.getBookId()));
//                lendTable.addCell(String.valueOf(l.getMemberId()));
//                lendTable.addCell((PdfPCell) l.getIssueDate());
//                lendTable.addCell((PdfPCell) l.getReturnDate());
//            }
//            document.add(lendTable);
//
//            document.close();
//            System.out.println("PDF Report Created Successfully!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//


package utils;

/**
 *
 * @author Hashir
 */

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import models.Book;
import models.Member;
import models.Lending;
import services.BookService;
import services.MemberService;
import services.LendingService;

import java.io.FileOutputStream;
import java.util.List;

public class ReportGenerator {

    public static void generatePDFReport() {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("LibraryReport.pdf"));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12);

            document.add(new Paragraph("Library Management System Report", titleFont));
            document.add(Chunk.NEWLINE);

            // ==== BOOK SECTION ====
            document.add(new Paragraph("Books", sectionFont));
            PdfPTable bookTable = new PdfPTable(5);
            bookTable.setWidthPercentage(100);
            bookTable.addCell("ID");
            bookTable.addCell("Title");
            bookTable.addCell("Author");
            bookTable.addCell("ISBN");
            bookTable.addCell("Available");

            List<Book> books = new BookService().getAllBooks();
            for (Book book : books) {
                bookTable.addCell(String.valueOf(book.getId()));
                bookTable.addCell(book.getTitle());
                bookTable.addCell(book.getAuthor());
                bookTable.addCell(book.getIsbn());
                bookTable.addCell(book.isAvailable() ? "Yes" : "No"); // Fixed
            }
            document.add(bookTable);
            document.add(Chunk.NEWLINE);

            // ==== MEMBER SECTION ====
            document.add(new Paragraph("Members", sectionFont));
            PdfPTable memberTable = new PdfPTable(3);
            memberTable.setWidthPercentage(100);
            memberTable.addCell("ID");
            memberTable.addCell("Name");
            memberTable.addCell("Email");

            List<Member> members = new MemberService().getAllMembers();
            for (Member m : members) {
                memberTable.addCell(String.valueOf(m.getId()));
                memberTable.addCell(m.getName());
                memberTable.addCell(m.getEmail());
            }
            document.add(memberTable);
            document.add(Chunk.NEWLINE);

            // ==== LENDING SECTION ====
            document.add(new Paragraph("Lending Records", sectionFont));
            PdfPTable lendTable = new PdfPTable(5);
            lendTable.setWidthPercentage(100);
            lendTable.addCell("ID");
            lendTable.addCell("Book ID");
            lendTable.addCell("Member ID");
            lendTable.addCell("Issue Date");
            lendTable.addCell("Return Date");

            List<Lending> lendings = new LendingService().getAllLendings();
            for (Lending l : lendings) {
                lendTable.addCell(String.valueOf(l.getId()));
                lendTable.addCell(String.valueOf(l.getBookId()));
                lendTable.addCell(String.valueOf(l.getMemberId()));
                lendTable.addCell(l.getIssueDate() != null ? l.getIssueDate().toString() : "N/A"); // Fixed
                lendTable.addCell(l.getReturnDate() != null ? l.getReturnDate().toString() : "N/A"); // Fixed
            }
            document.add(lendTable);

            document.close();
            System.out.println("✅ PDF Report Created Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
