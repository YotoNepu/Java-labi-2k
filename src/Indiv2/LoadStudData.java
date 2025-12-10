package Indiv2;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LoadStudData {
    public void getStudentDataFromFile(String filepath, List<Student> students) {
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

    public void checkLength(int length, int n) {
        if (length != n) {
            throw new IllegalArgumentException("Неверное количество данных. Ожидается: " + n);
        }
    }

}
