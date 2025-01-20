package com.literalura.console;

import com.literalura.model.Book;
import com.literalura.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleUI implements CommandLineRunner {
    private final BookService bookService;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> searchBook();
                    case 2 -> listAllBooks();
                    case 3 -> listAllAuthors();
                    case 4 -> searchBooksByLanguage();
                    case 5 -> running = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void displayMenu() {
        System.out.println("\n=== LiterAlura Book Catalog ===");
        System.out.println("1. Search Book by Title");
        System.out.println("2. List All Books");
        System.out.println("3. List All Authors");
        System.out.println("4. Search Books by Language");
        System.out.println("5. Exit");
        System.out.print("Choose an option (1-5): ");
    }

    private void searchBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        try {
            Book book = bookService.searchAndSaveBook(title);
            if (book != null) {
                System.out.println("\nBook found and saved:");
                displayBook(book);
            } else {
                System.out.println("Book not found.");
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listAllBooks() {
        System.out.println("\nRegistered Books:");
        bookService.getAllBooks().forEach(this::displayBook);
    }

    private void listAllAuthors() {
        System.out.println("\nRegistered Authors:");
        bookService.getAllAuthors().forEach(System.out::println);
    }

    private void searchBooksByLanguage() {
        System.out.print("Enter language code (EN, ES, FR, PT): ");
        String language = scanner.nextLine().toUpperCase();
        try {
            System.out.println("\nBooks in " + language + ":");
            bookService.getBooksByLanguage(language).forEach(this::displayBook);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayBook(Book book) {
        System.out.println("-".repeat(50));
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Language: " + book.getLanguage().toUpperCase());
        System.out.println("Downloads: " + book.getDownloads());
    }
}