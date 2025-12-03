package Laba4;

class Reader {

    private String name;
    private Book book;
    private int borrowDay;
    private int borrowMonth;

    public Reader() {
        this.name = "Неизвестно";
        this.book = new Book();
        this.borrowDay = 0;
        this.borrowMonth = 0;
    }

    public Reader(String name, Book book, int borrowDay, int borrowMonth) {
        this.name = name;
        this.book = new Book(book);
        setBorrowDay(borrowDay);
        setBorrowMonth(borrowMonth);
    }

    public Reader(Reader other) {
        this.name = other.name;
        this.book = new Book(other.book);
        this.borrowDay = other.borrowDay;
        this.borrowMonth = other.borrowMonth;
    }

    public String getName() {
        return name;
    }

    public Book getBook() {
        return new Book(book);
    }

    public int getBorrowDay() {
        return borrowDay;
    }

    public int getBorrowMonth() {
        return borrowMonth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBook(Book book) {
        this.book = new Book(book);
    }

    public void setBorrowDay(int borrowDay) {
        if (borrowDay >= 1 && borrowDay <= 31) {
            this.borrowDay = borrowDay;
        } else {
            throw new IllegalArgumentException("День должен быть от 1 до 31");
        }
    }

    public void setBorrowMonth(int borrowMonth) {
        if (borrowMonth >= 1 && borrowMonth <= 12) {
            this.borrowMonth = borrowMonth;
        } else {
            throw new IllegalArgumentException("Месяц должен быть от 1 до 12");
        }
    }

    public void setBookReturnDate(int day, int month) {
        this.book.setReturnDate(day, month);
    }

    public boolean isReturnedOnTime() {
        int returnDay = this.getBook().getReturnDay();
        int returnMonth = this.getBook().getReturnMonth();

        if (returnDay == 0 || returnMonth == 0) {
            return false;
        }
        if (returnMonth < borrowMonth) {
            return true;
        } else if (returnMonth == borrowMonth) {
            return returnDay <= borrowDay;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: книга %s, взял до %d.%d",
                name, book.toString(), borrowDay, borrowMonth);
    }
}