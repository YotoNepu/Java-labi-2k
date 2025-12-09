package Indiv2;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private StudentStatistics statistics;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        manager.getStudentDataFromFile("src/Indiv2/data_stud_mark.txt");

        if (!manager.students.isEmpty()) {
            manager.createStatistics();
            manager.runMenu();
        } else {
            System.out.println("Нет данных для обработки. Завершение работы.");
        }
    }

    public void getStudentDataFromFile(String filepath) {
        int lineCount = 0;
        int errorCount = 0;

        System.out.println("\nЧтение данных о студентах...");

        try (Scanner sc = new Scanner(new FileReader(filepath))) {
            while (sc.hasNextLine()) {
                String[] studentData = new String[8];

                for (int i = 0; i < 8; i++) {
                    if (sc.hasNextLine()) {
                        studentData[i] = sc.nextLine();
                        lineCount++;
                    } else {
                        if (i > 0) {
                            errorCount++;
                            System.out.println("Ошибка. Недостаточное кол-во данных для студента.");
                        }
                        break;
                    }
                }

                if (studentData[7] != null) {
                    try {
                        Student student = parseStudentData(studentData, lineCount - 7);
                        students.add(student);

                    } catch (Exception e) {
                        errorCount++;
                        System.out.println("Ошибка при обработке студента: " + e.getMessage());
                    }
                }
            }

            System.out.println("Найдено ошибок: " + errorCount);

        } catch (IOException e) {
            System.out.println("Ошибка. Не удается прочитать файл: " + e.getMessage());
            System.out.println("Завершение работы программы.");
            System.exit(1);
        }
    }

    private Student parseStudentData(String[] data, int startLine) {
        checkLength(data.length, 8);

        String gender = data[0].trim();
        String group = data[1].trim();
        String educationLevel = data[2].trim();
        String paymentCondition = data[3].trim();
        String completionStatus = data[4].trim();

        int mathScore = parseScore(data[5].trim(), "математика", startLine + 5);
        int readingScore = parseScore(data[6].trim(), "чтение", startLine + 6);
        int writingScore = parseScore(data[7].trim(), "письмо", startLine + 7);

        return new Student(gender, group, educationLevel, paymentCondition,
                completionStatus, mathScore, readingScore, writingScore);
    }

    /**
     * Парсинг и валидация балла
     */
    private int parseScore(String scoreStr, String subject, int lineNumber) {
        try {
            int score = Integer.parseInt(scoreStr);
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException(
                        String.format("Балл по предмету '%s' должен быть от 0 до 100, получено: %d (строка %d)",
                                subject, score, lineNumber)
                );
            }
            return score;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format("Некорректный балл по предмету '%s': '%s' (строка %d)",
                            subject, scoreStr, lineNumber)
            );
        }
    }

    /**
     * Проверка количества данных
     */
    public void checkLength(int length, int n) {
        if (length != n) {
            throw new IllegalArgumentException("Неверное количество данных. Ожидается: " + n);
        }
    }

    /**
     * Создание статистики
     */
    private void createStatistics() {
        this.statistics = new StudentStatistics(students);
    }

    /**
     * Главное меню программы
     */
    private void runMenu() {
        boolean exit = false;

        while (!exit) {
            printMenu();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showGroupStatistics();
                    break;
                case "2":
                    findScoreRange();
                    break;
                case "3":
                    showTopFiveResults();
                    break;
                case "4":
                    createFullReport();
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

    /**
     * Вывод меню
     */
    private void printMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("МЕНЮ:");
        System.out.println("1. Статистика по группам и уровням образования");
        System.out.println("2. Диапазон баллов по предмету и полу");
        System.out.println("3. Топ-5 результатов по уровню образования");
        System.out.println("4. Создать полный отчет в файл");
        System.out.println("5. Выход");
        System.out.print("Выберите опцию: ");
    }

    /**
     * Показать статистику по группам
     */
    private void showGroupStatistics() {
        System.out.println("\n------------------------------------");
        System.out.println("СТАТИСТИКА ПО ГРУППАМ И УРОВНЯМ ОБРАЗОВАНИЯ:");

        var groupStats = statistics.getGroupStatistics();

        if (groupStats.isEmpty()) {
            System.out.println("Нет данных");
            return;
        }

        System.out.printf("%-15s %-20s %-10s %-15s%n",
                "Группа", "Уровень", "Кол-во", "Средний балл");
        System.out.println("-".repeat(60));

        groupStats.forEach((group, levelStats) -> {
            levelStats.forEach((level, stats) -> {
                System.out.printf("%-15s %-20s %-10d %-15.2f%n",
                        group, level, stats.getStudentCount(), stats.getAverageScore());
            });
        });

        System.out.println("------------------------------------");
    }

    /**
     * Найти диапазон баллов
     */
    private void findScoreRange() {
        System.out.println("\n------------------------------------");
        System.out.println("ПОИСК ДИАПАЗОНА БАЛЛОВ:");

        // Ввод предмета
        String subject;
        while (true) {
            System.out.print("Введите предмет (math/reading/writing): ");
            subject = scanner.nextLine().trim().toLowerCase();

            if (subject.equals("math") || subject.equals("reading") || subject.equals("writing")) {
                break;
            }
            System.out.println("Ошибка: некорректный предмет");
        }

        // Ввод пола
        String gender;
        while (true) {
            System.out.print("Введите пол (male/female): ");
            gender = scanner.nextLine().trim().toLowerCase();

            if (gender.equals("male") || gender.equals("female")) {
                break;
            }
            System.out.println("Ошибка: некорректный пол");
        }

        var range = statistics.getScoreRangeBySubjectAndGender(subject, gender);

        System.out.println("\nРезультаты:");
        System.out.println("Предмет: " + subject);
        System.out.println("Пол: " + gender);
        System.out.println("Минимальный балл: " + range.getMinScore());
        System.out.println("Максимальный балл: " + range.getMaxScore());
        System.out.println("------------------------------------");
    }

    /**
     * Показать топ-5 результатов
     */
    private void showTopFiveResults() {
        System.out.println("\n------------------------------------");
        System.out.println("ТОП-5 РЕЗУЛЬТАТОВ:");

        System.out.print("Введите уровень образования: ");
        String educationLevel = scanner.nextLine().trim();

        var topResults = statistics.getTopFiveByEducationLevel(educationLevel);

        System.out.println("\nЗАКОНЧИВШИЕ:");
        printStudentList(topResults.get("completed"));

        System.out.println("\nНЕ ЗАКОНЧИВШИЕ:");
        printStudentList(topResults.get("not_completed"));

        System.out.println("------------------------------------");
    }

    /**
     * Вывод списка студентов
     */
    private void printStudentList(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("Нет данных");
            return;
        }

        System.out.printf("%-10s %-15s %-15s %-10s%n",
                "Группа", "Уровень", "Сумма баллов", "Средний");
        System.out.println("-".repeat(50));

        students.forEach(student -> {
            System.out.printf("%-10s %-15s %-15d %-10.2f%n",
                    student.getGroup(),
                    student.getEducationLevel(),
                    student.getTotalScore(),
                    student.getAverageScore());
        });
    }

    /**
     * Создание полного отчета в файл
     */
    private void createFullReport() {
        System.out.println("\n------------------------------------");
        System.out.println("СОЗДАНИЕ ПОЛНОГО ОТЧЕТА:");

        System.out.print("Введите имя файла для отчета: ");
        String filename = scanner.nextLine().trim();

        if (filename.isEmpty()) {
            filename = "student_report.txt";
        }

        try (PrintWriter writer = new PrintWriter(filename)) {
            generateReport(writer);
            System.out.println("Отчет успешно сохранен в файл: " + filename);

        } catch (IOException e) {
            System.out.println("Ошибка при создании отчета: " + e.getMessage());
        }

        System.out.println("------------------------------------");
    }

    /**
     * Генерация отчета
     */
    private void generateReport(PrintWriter writer) {
        // Заголовок
        writer.println("ОТЧЕТ ПО РЕЗУЛЬТАТАМ ТЕСТИРОВАНИЯ СТУДЕНТОВ");
        writer.println("Дата создания: " + java.time.LocalDate.now());
        writer.println("Всего студентов: " + students.size());
        writer.println("=============================================\n");

        // 1. Статистика по группам
        writer.println("1. СТАТИСТИКА ПО ГРУППАМ И УРОВНЯМ ОБРАЗОВАНИЯ");
        writer.println("---------------------------------------------");

        var groupStats = statistics.getGroupStatistics();

        if (groupStats.isEmpty()) {
            writer.println("Нет данных");
        } else {
            writer.printf("%-15s %-20s %-10s %-15s%n",
                    "Группа", "Уровень", "Кол-во", "Средний балл");
            writer.println("-".repeat(60));

            groupStats.forEach((group, levelStats) -> {
                levelStats.forEach((level, stats) -> {
                    writer.printf("%-15s %-20s %-10d %-15.2f%n",
                            group, level, stats.getStudentCount(), stats.getAverageScore());
                });
            });
        }
        writer.println();

        // 2. Диапазон баллов (запрашиваем у пользователя)
        writer.println("2. ДИАПАЗОН БАЛЛОВ");
        writer.println("---------------------------------------------");

        System.out.println("\nДля отчета необходимо ввести параметры:");
        System.out.print("Введите предмет (math/reading/writing): ");
        String subject = scanner.nextLine().trim().toLowerCase();

        System.out.print("Введите пол (male/female): ");
        String gender = scanner.nextLine().trim().toLowerCase();

        var range = statistics.getScoreRangeBySubjectAndGender(subject, gender);

        writer.printf("Предмет: %s, Пол: %s%n", subject, gender);
        writer.printf("Минимальный балл: %d%n", range.getMinScore());
        writer.printf("Максимальный балл: %d%n", range.getMaxScore());
        writer.println();

        // 3. Топ-5 результатов
        writer.println("3. ТОП-5 РЕЗУЛЬТАТОВ");
        writer.println("---------------------------------------------");

        System.out.print("Введите уровень образования для топ-5: ");
        String educationLevel = scanner.nextLine().trim();

        var topResults = statistics.getTopFiveByEducationLevel(educationLevel);

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

    /**
     * Запись списка студентов в файл
     */
    private void writeStudentList(PrintWriter writer, List<Student> students) {
        if (students == null || students.isEmpty()) {
            writer.println("Нет данных");
            return;
        }

        writer.printf("%-10s %-15s %-15s %-10s%n",
                "Группа", "Уровень", "Сумма баллов", "Средний");
        writer.println("-".repeat(50));

        students.forEach(student -> {
            writer.printf("%-10s %-15s %-15d %-10.2f%n",
                    student.getGroup(),
                    student.getEducationLevel(),
                    student.getTotalScore(),
                    student.getAverageScore());
        });
    }
}