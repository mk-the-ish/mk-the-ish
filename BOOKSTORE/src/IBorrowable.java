public interface IBorrowable {
     void borrowBook(Book book) throws LibraryException, UserException;

     void returnBook(Book book) throws LibraryException;
}
