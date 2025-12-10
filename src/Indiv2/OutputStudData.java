package Indiv2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class OutputStudData {
    private Scanner sc;
    private StudentStatistics stats;

    public OutputStudData() {}

    public OutputStudData(Scanner sc, StudentStatistics stats) {
        this.sc = sc;
        this.stats = stats;
    }

    protected void printMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("МЕНЮ:");
        System.out.println("1. Статистика по группам и уровням образования");
        System.out.println("2. Диапазон баллов по предмету и полу");
        System.out.println("3. Топ-5 результатов по уровню образования");
        System.out.println("4. Создать полный отчет в файл");
        System.out.println("5. Выход");
        System.out.print("Выберите опцию: ");
    }

    protected void showGroupStatistics() {
        System.out.println("\n------------------------------------");
        System.out.println("СТАТИСТИКА ПО ГРУППАМ И УРОВНЯМ ОБРАЗОВАНИЯ:");

        var groupStats = stats.getGroupStatistics();

        if (groupStats.isEmpty()) {
            System.out.println("Нет данных");
            return;
        }

        System.out.printf("%-15s %-20s %-10s %-15s%n",
                "Группа", "Уровень", "Кол-во", "Средний балл");
        System.out.println("-".repeat(60));

        groupStats.forEach((group, levelStats) -> levelStats.forEach((level, stats) -> System.out.printf("%-15s %-20s %-10d %-15.2f%n",
                group, level, stats.getStudentCount(), stats.getAverageScore())));

        System.out.println("------------------------------------");
    }

    protected void findScoreRange() {
        System.out.println("\n------------------------------------");
        System.out.println("ПОИСК ДИАПАЗОНА БАЛЛОВ:");

        String subject;
        while (true) {
            System.out.print("Введите предмет (math/reading/writing): ");
            subject = sc.nextLine().trim().toLowerCase();

            if (subject.equals("math") || subject.equals("reading") || subject.equals("writing")) {
                break;
            }
            System.out.println("Ошибка: некорректный предмет");
        }

        String gender;
        while (true) {
            System.out.print("Введите пол (male/female): ");
            gender = sc.nextLine().trim().toLowerCase();

            if (gender.equals("male") || gender.equals("female")) {
                break;
            }
            System.out.println("Ошибка: некорректный пол");
        }

        var range = stats.getScoreRangeBySubjectAndGender(subject, gender);

        System.out.println("\nРезультаты:");
        System.out.println("Предмет: " + subject);
        System.out.println("Пол: " + gender);
        System.out.println("Минимальный балл: " + range.getMinScore());
        System.out.println("Максимальный балл: " + range.getMaxScore());
        System.out.println("------------------------------------");
    }

    protected void showTopFiveResults() {
        System.out.println("\n------------------------------------");
        System.out.println("ТОП-5 РЕЗУЛЬТАТОВ:");

        System.out.print("Введите уровень образования: ");
        String educationLevel = sc.nextLine().trim();

        var topResults = stats.getTopFiveByEducationLevel(educationLevel);

        System.out.println("\nЗАКОНЧИВШИЕ:");
        printStudentList(topResults.get("completed"));

        System.out.println("\nНЕ ЗАКОНЧИВШИЕ:");
        printStudentList(topResults.get("not_completed"));

        System.out.println("------------------------------------");
    }

    private void printStudentList(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("Нет данных");
            return;
        }

        System.out.printf("%-10s %-15s %-15s %-10s%n",
                "Группа", "Уровень", "Сумма баллов", "Средний");
        System.out.println("-".repeat(50));

        students.forEach(student -> System.out.printf("%-10s %-15s %-15d %-10.2f%n",
                student.getGroup(),
                student.getEducationLevel(),
                student.getTotalScore(),
                student.getAverageScore()));
    }

    protected void createFullReport(List<Student> students) {
        System.out.println("\n------------------------------------");
        System.out.println("СОЗДАНИЕ ПОЛНОГО ОТЧЕТА:");

        System.out.print("Введите имя файла для отчета: ");
        String filename = sc.nextLine().trim();

        if (filename.isEmpty()) {
            filename = "student_report.txt";
        }

        try (PrintWriter writer = new PrintWriter(filename)) {
            generateReport(writer, students);
            System.out.println("Отчет успешно сохранен в файл: " + filename);

        } catch (IOException e) {
            System.out.println("Ошибка при создании отчета: " + e.getMessage());
        }

        System.out.println("------------------------------------");
    }

    private void generateReport(PrintWriter writer, List<Student> students) {
        writer.println("ОТЧЕТ ПО РЕЗУЛЬТАТАМ ТЕСТИРОВАНИЯ СТУДЕНТОВ");
        writer.println("Дата создания: " + java.time.LocalDate.now());
        writer.println("Всего студентов: " + students.size());
        writer.println("=============================================\n");

        writer.println("1. СТАТИСТИКА ПО ГРУППАМ И УРОВНЯМ ОБРАЗОВАНИЯ");
        writer.println("---------------------------------------------");

        var groupStats = stats.getGroupStatistics();

        if (groupStats.isEmpty()) {
            writer.println("Нет данных");
        } else {
            writer.printf("%-15s %-20s %-10s %-15s%n",
                    "Группа", "Уровень", "Кол-во", "Средний балл");
            writer.println("-".repeat(60));

            groupStats.forEach((group, levelStats) -> levelStats.forEach((level, stats) -> writer.printf("%-15s %-20s %-10d %-15.2f%n",
                    group, level, stats.getStudentCount(), stats.getAverageScore())));
        }
        writer.println();

        writer.println("2. ДИАПАЗОН БАЛЛОВ");
        writer.println("---------------------------------------------");

        System.out.println("\nДля отчета необходимо ввести параметры:");
        System.out.print("Введите предмет (math/reading/writing): ");
        String subject = sc.nextLine().trim().toLowerCase();

        System.out.print("Введите пол (male/female): ");
        String gender = sc.nextLine().trim().toLowerCase();

        var range = stats.getScoreRangeBySubjectAndGender(subject, gender);

        writer.printf("Предмет: %s, Пол: %s%n", subject, gender);
        writer.printf("Минимальный балл: %d%n", range.getMinScore());
        writer.printf("Максимальный балл: %d%n", range.getMaxScore());
        writer.println();

        writer.println("3. ТОП-5 РЕЗУЛЬТАТОВ");
        writer.println("---------------------------------------------");

        System.out.print("Введите уровень образования для топ-5: ");
        String educationLevel = sc.nextLine().trim();

        var topResults = stats.getTopFiveByEducationLevel(educationLevel);

        writer.printf("Уровень образования: %s%n%n", educationLevel);

        writer.println("ЗАКОНЧИВШИЕ:");
        writer.println("-----------");
        writeStudentList(writer, topResults.get("completed"));
        writer.println();

        writer.println("НЕ ЗАКОНЧИВШИЕ:");
        writer.println("--------------");
        writeStudentList(writer, topResults.get("not_completed"));

        writer.println("\n=============================================");
        writer.println("Конец отчета");
    }

    private void writeStudentList(PrintWriter writer, List<Student> students) {
        if (students == null || students.isEmpty()) {
            writer.println("Нет данных");
            return;
        }

        writer.printf("%-10s %-15s %-15s %-10s%n",
                "Группа", "Уровень", "Сумма баллов", "Средний");
        writer.println("-".repeat(50));

        students.forEach(student -> writer.printf("%-10s %-15s %-15d %-10.2f%n",
                student.getGroup(),
                student.getEducationLevel(),
                student.getTotalScore(),
                student.getAverageScore()));
    }
}
