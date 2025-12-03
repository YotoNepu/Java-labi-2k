package Laba4;

class Book {

    private String title;
    private String author;
    private int returnDay;
    private int returnMonth;

    public Book() {
        this.title = "Неизвестно";
        this.author = "Неизвестно";
        this.returnDay = 0;
        this.returnMonth = 0;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(Book other) {
        this.title = other.title;
        this.author = other.author;
        this.returnDay = other.returnDay;
        this.returnMonth = other.returnMonth;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getReturnDay() {
        return returnDay;
    }

    public int getReturnMonth() {
        return returnMonth;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReturnDate(int day, int month) {
        this.returnDay = day;
        this.returnMonth = month;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" (" + author + ")";
    }
}