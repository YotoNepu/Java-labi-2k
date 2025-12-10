package Laba7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoadSchoolData loadData = new LoadSchoolData();
        SchoolJournal journal = new SchoolJournal();

        try {
            journal = loadData.loadDataFromFile("src/Laba7/", "data_school.txt");
        } catch (IOException e) {
            System.out.println("Ошибка. Не удалось прочитать файл: " + e.getMessage());
            System.out.println("Завершение программы.");
            System.exit(1);
        }

        OutputSchoolJournal printData = new OutputSchoolJournal(journal);

        try {
            printData.printClassesToConsoleAndFiles("src/Laba7/OutData/");
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Меню задач ---");
            System.out.println("1. Найти учеников с определенной оценкой");
            System.out.println("2. Отсортировать классы по успеваемости");
            System.out.println("3. Вывести учеников по предмету");
            System.out.println("4. Создать ведомость класса");
            System.out.println("5. Найти класс ученика");
            System.out.println("6. Найти предмет с лучшей успеваемостью");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. Введите число!");
                continue;
            }

            switch (choice) {
                case 1:
                    // Задание 1: Ученики с определенной оценкой
                    System.out.print("Введите оценку (2-5): ");
                    try {
                        int mark = Integer.parseInt(scanner.nextLine());
                        if (mark >= 2 && mark <= 5) {
                            printData.printStudentsWithMark(mark);
                        } else {
                            System.out.println("Ошибка. Оценка должна быть от 2 до 5!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите число!");
                    }
                    break;

                case 2:
                    // Задание 2: Сортировка классов по успеваемости
                    printData.printClassesSortedByAverage(journal.sortClassesByAverage(
                            new ArrayList<>(journal.getClasses().values())));
                    break;

                case 3:
                    // Задание 3: Ученики по предмету
                    System.out.print("Введите название предмета: ");
                    String subject = scanner.nextLine();
                    printData.printStudentsBySubject(subject);
                    break;

                case 4:
                    // Задание 4: Ведомость класса
                    System.out.print("Введите номер класса (1-11): ");
                    try {
                        int grade = Integer.parseInt(scanner.nextLine());
                        if (grade >= 1 && grade <= 11) {
                            printData.printClassSubjectsReport("src/Laba7/OutData/", grade);
                        } else {
                            System.out.println("Класс должен быть от 1 до 11!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите число!");
                    } catch (IOException e) {
                        System.out.println("Ошибка при записи в файл: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Задание 5: Поиск класса ученика
                    System.out.print("Введите фамилию ученика: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Введите имя ученика: ");
                    String firstName = scanner.nextLine();
                    printData.findStudentClass(lastName, firstName);
                    break;

                case 6:
                    // Задание 6: Предмет с лучшей успеваемостью
                    printData.bestGradesSubject();
                    break;

                case 0:
                    exit = true;
                    System.out.println("Программа завершена.");
                    break;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
