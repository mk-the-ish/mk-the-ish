public class Book {

    private String title;
    private String author;
    private String ISBN;
    private boolean availabilityStatus;

    public Book(String title, String author,  String ISBN, boolean availabilityStatus){
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.availabilityStatus = availabilityStatus;

    }



    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                '}';
    }



}
