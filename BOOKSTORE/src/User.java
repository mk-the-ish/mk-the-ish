import java.util.ArrayList;

public abstract class User {
    protected String name;
    protected String id;
    protected ArrayList<Book> borrowedBooks;

    public User(String name, String id){
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book){
        borrowedBooks.add(book);
    }
    public void returnBook(Book book){
        borrowedBooks.remove(book);
    }

    public boolean checkAvailability(){
        return false;
    }
}
