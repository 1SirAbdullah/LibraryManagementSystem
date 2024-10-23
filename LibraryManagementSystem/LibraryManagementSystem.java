import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Book class to store book details
class Book {
    String title;
    String author;
    int copies;
    int issuedCopies;

    Book(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.issuedCopies = 0;
    }

    // Method to check if the book is available for issuing
    boolean isAvailable() {
        return (copies - issuedCopies) > 0;
    }

    // Method to issue a book
    void issueBook() {
        if (isAvailable()) {
            issuedCopies++;
        }
    }

    // Method to return a book
    void returnBook() {
        if (issuedCopies > 0) {
            issuedCopies--;
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available Copies: " + (copies - issuedCopies);
    }
}

// Library class to manage book collection and user interactions
public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static HashMap<String, Book> issuedBooks = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Available Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Generate Report (Issued Books)");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    viewAvailableBooks();
                    break;
                case 3:
                    issueBook(scanner);
                    break;
                case 4:
                    returnBook(scanner);
                    break;
                case 5:
                    generateIssuedBooksReport();
                    break;
                case 6:
                    System.out.println("Exiting Library Management System...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to add a new book to the library
    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter number of copies: ");
        int copies = scanner.nextInt();

        Book book = new Book(title, author, copies);
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Method to view all available books
    private static void viewAvailableBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    // Method to issue a book
    private static void issueBook(Scanner scanner) {
        System.out.print("Enter book title to issue: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && book.isAvailable()) {
                book.issueBook();
                issuedBooks.put(title, book);
                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book not available for issuing.");
    }

    // Method to return a book
    private static void returnBook(Scanner scanner) {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();

        Book book = issuedBooks.get(title);
        if (book != null) {
            book.returnBook();
            issuedBooks.remove(title);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found in issued books.");
        }
    }

    // Method to generate a report of all issued books
    private static void generateIssuedBooksReport() {
        if (issuedBooks.isEmpty()) {
            System.out.println("No books issued.");
        } else {
            System.out.println("\nIssued Books:");
            for (String title : issuedBooks.keySet()) {
                System.out.println("Title: " + title);
            }
        }
    }
}