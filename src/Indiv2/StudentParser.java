package Indiv2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для чтения и парсинга данных о студентах из файла.
 * Обрабатывает ошибочные ситуации согласно условию задачи.
 */
public class StudentParser {

    /**
     * Читает данные о студентах из файла
     * @param filePath путь к файлу с данными
     * @return список студентов
     * @throws IOException если файл не найден или произошла ошибка чтения
     * @throws IllegalArgumentException если данные в файле некорректны
     */
    public static List<Student> parseStudentsFromFile(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        if (lines.size() % 8 != 0) {
            throw new IllegalArgumentException(
                    String.format("Некорректное количество строк в файле: %d. Должно быть кратно 8.", lines.size())
            );
        }

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += 8) {
            try {
                Student student = parseStudent(lines.subList(i, i + 8));
                students.add(student);
            } catch (Exception e) {
                System.err.printf("Ошибка при обработке студента начиная со строки %d: %s%n",
                        i + 1, e.getMessage());
                throw new IllegalArgumentException(
                        String.format("Некорректные данные в файле начиная со строки %d", i + 1), e);
            }
        }

        if (students.isEmpty()) {
            throw new IllegalArgumentException("Файл не содержит корректных данных о студентах");
        }

        return students;
    }

    /**
     * Парсит данные одного студента из списка строк
     */
    private static Student parseStudent(List<String> studentData) {
        if (studentData.size() != 8) {
            throw new IllegalArgumentException("Для студента должно быть 8 параметров");
        }

        String gender = studentData.get(0).trim();
        String group = studentData.get(1).trim();
        String educationLevel = studentData.get(2).trim();
        String paymentCondition = studentData.get(3).trim();
        String completionStatus = studentData.get(4).trim();

        int mathScore = parseScore(studentData.get(5), "math");
        int readingScore = parseScore(studentData.get(6), "reading");
        int writingScore = parseScore(studentData.get(7), "writing");

        return new Student(gender, group, educationLevel, paymentCondition,
                completionStatus, mathScore, readingScore, writingScore);
    }

    /**
     * Парсит балл из строки с валидацией
     */
    private static int parseScore(String scoreStr, String subject) {
        try {
            int score = Integer.parseInt(scoreStr.trim());
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException(
                        String.format("Балл по предмету '%s' должен быть от 0 до 100, получено: %d", subject, score)
                );
            }
            return score;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format("Некорректный балл по предмету '%s': '%s'", subject, scoreStr), e);
        }
    }
}