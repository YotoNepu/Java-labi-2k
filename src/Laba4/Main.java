package Laba4;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Java 8. Полное руководство", "Г. Шилдт");
        Book book2 = new Book("Крейцерова соната", "Л. Толстой");

        Reader reader1 = new Reader("Петров", book1, 1, 4);
        Reader reader2 = new Reader("Васечкин", book2, 25, 3);

        System.out.println("Читатели библиотеки:");
        System.out.println(reader1);
        System.out.println(reader2);
        System.out.println();

        int[] returnDate = {29, 3};
        reader1.setBookReturnDate(returnDate[0], returnDate[1]);
        reader2.setBookReturnDate(returnDate[0], returnDate[1]);

        System.out.printf("Проверка сдачи книг %d.%d:%n%n", returnDate[0], returnDate[1]);

        boolean reader1OnTime = reader1.isReturnedOnTime();
        System.out.printf("%s: книга \"%s\" %s%n",
                reader1.getName(),
                reader1.getBook().getTitle(),
                reader1OnTime ? "сдана вовремя" : "сдана не вовремя"
        );

        boolean reader2OnTime = reader2.isReturnedOnTime();
        System.out.printf("%s: книга \"%s\" %s%n",
                reader2.getName(),
                reader2.getBook().getTitle(),
                reader2OnTime ? "сдана вовремя" : "сдана не вовремя"
        );

        System.out.println("\nИтог:");
        if (reader1OnTime && reader2OnTime) {
            System.out.println("Оба читателя сдали книги вовремя");
        } else if (!reader1OnTime && !reader2OnTime) {
            System.out.println("Оба читателя сдали книги не вовремя");
        } else if (reader1OnTime) {
            System.out.println("Только Петров сдал книгу вовремя");
        } else {
            System.out.println("Только Васечкин сдал книгу вовремя");
        }
    }
}