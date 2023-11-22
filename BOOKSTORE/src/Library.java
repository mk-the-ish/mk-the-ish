import java.util.ArrayList;

public class Library {
    private static ArrayList<Book> books ;

    public Library(){
        //library initialization
        books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public static boolean checkAvailability(Book book){
        //availability logic to be changed
        if (books.contains(book)){
            return true;
        }
        else{
            return false;
        }
    }

    public void displayAvailableBooks(){
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void removeBook(Book book){
        books.remove(book);
    }




}
;