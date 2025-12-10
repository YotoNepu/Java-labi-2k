package Indiv2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static LoadStudData loadData = new LoadStudData();
    private static List<Student> students = new ArrayList<>();
    private static OutputStudData printData = new OutputStudData();
    private static StudentStatistics statistics;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main manager = new Main();

        loadData.getStudentDataFromFile("src/Indiv2/data_stud_mark.txt", students);

        if (!students.isEmpty()) {
            manager.createStatistics();
            printData = new OutputStudData(scanner, statistics);
            manager.runMenu();
        } else {
            System.out.println("Нет данных для обработки. Завершение работы.");
        }
    }

    private void createStatistics() {
        statistics = new StudentStatistics(students);
    }

    private void runMenu() {
        boolean exit = false;

        while (!exit) {
            printData.printMenu();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    printData.showGroupStatistics();
                    break;
                case "2":
                    printData.findScoreRange();
                    break;
                case "3":
                    printData.showTopFiveResults();
                    break;
                case "4":
                    printData.createFullReport(students);
                    break;
                case "5":
                    exit = true;
                    System.out.println("Выход из программы");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}