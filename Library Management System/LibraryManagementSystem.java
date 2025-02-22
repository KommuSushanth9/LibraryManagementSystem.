import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title=title;
        this.author=author;
        this.isAvailable=true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() throws Exception {
        if (!isAvailable) {
            throw new Exception("Book is already borrowed.");
        }
        isAvailable=false;
    }

    public void returnBook() {
        isAvailable=true;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + isAvailable;
    }
}


class Library {
    private ArrayList<Book> books;

    public Library() {
        books=new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Library library=new Library();

        
        System.out.print("Enter number of books to add: ");
        int numBooks=scanner.nextInt();
        scanner.nextLine();
        
        for(int i=0;i<numBooks;i++) {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            System.out.print("Enter book author: ");
            String author = scanner.nextLine();
            library.addBook(new Book(title, author));
        }

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Display Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    Book borrowBook = library.findBook(borrowTitle);
                    if (borrowBook != null) {
                        try {
                            borrowBook.borrowBook();
                            System.out.println("You borrowed: " + borrowBook.getTitle());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    Book returnBook = library.findBook(returnTitle);
                    if (returnBook != null) {
                        returnBook.returnBook();
                        System.out.println("You returned: " + returnBook.getTitle());
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
