public class LibraryManagementApp {
    public static void main(String[] args) throws LibraryException, UserException {
        Library library = new Library();


        User student = new Student("Alice", "S123");
        User teacher = new Teacher("Bob", "T456");

        Book book1 = new Book("Introduction to Java", "John Doe", "123456", true);
        Book book2 = new Book("OOP Concepts", "Jane Smith", "789101", true);

        library.addBook(book1);
        library.addBook(book2);

        student.borrowBook(book1);
        teacher.borrowBook(book2);
        // Demonstrate returning books and handling exceptions.
        student.returnBook(book1);
        student.returnBook(book1); // Simulate trying to return the same book again.

        library.displayAvailableBooks();
    }
}

