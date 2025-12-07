package Laba7;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;

public class SchoolJournal {
    private Map<Integer, ClassGrade> classes;
    private List<Student> allStudents;

    public SchoolJournal() {
        classes = new TreeMap<>();
        allStudents = new ArrayList<>();
    }

    public void loadDataFromFile(String filepath, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath + filename));
        String line;

        System.out.println("Загрузка данных из файла " + filename + "...");

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");
            if (parts.length != 5) {
                System.out.println("Ошибка. Неверное кол-во данных.");
                continue;
            }

            try {
                String lastName = parts[0];
                String firstName = parts[1];
                int grade = Integer.parseInt(parts[2]);
                String subject = parts[3];
                int mark = Integer.parseInt(parts[4]);

                if (grade < 1 || grade > 11) {
                    System.out.println("Ошибка. Некорректный класс в строке: " + line);
                    continue;
                }

                if (mark < 2 || mark > 5) {
                    System.out.println("Ошибка. Некорректная оценка в строке: " + line);
                    continue;
                }

                Student student = new Student(lastName, firstName, grade, subject, mark);

                allStudents.add(student);

                if (!classes.containsKey(grade)) {
                    classes.put(grade, new ClassGrade(grade));
                }
                classes.get(grade).addStudent(student);

            } catch (NumberFormatException e) {
                System.out.println("Ошибка преобразования числа в строке: " + line);
            }
        }

        reader.close();
        System.out.println("Загружено " + allStudents.size() + " записей.");
        System.out.println("Найдено " + classes.size() + " классов.");
    }


    public void printClassesToConsoleAndFiles(String filepath) throws IOException {
        System.out.println("\n--- Список всех классов ---");

        for (ClassGrade classGrade: classes.values()) {
            System.out.println("\n" + classGrade.toString());

            for (Student student: classGrade.getStudents()) {
                System.out.println("  " + student);
            }

            String filename = "class_" + classGrade.getGradeNumber() + ".txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(filepath + filename))) {
                writer.println("Класс " + classGrade.getGradeNumber());
                writer.println("Количество учеников: " + classGrade.getStudents().size());
                writer.println("Средний балл: " + String.format("%.2f", classGrade.getAverageMark()));
                writer.println("\nСписок учеников:");

                List<Student> sortedStudents = new ArrayList<>(classGrade.getStudents());
                sortStudentsByLastName(sortedStudents);

                for (Student student: sortedStudents) {
                    writer.println(student);
                }
            }

            System.out.println("-> Данные сохранены в файл: " + filename);
        }
    }

    private void sortStudentsByLastName(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return;
        }

        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                Student s1 = students.get(j);
                Student s2 = students.get(j + 1);

                if (s1.getLastName().compareTo(s2.getLastName()) > 0) {
                    students.set(j, s2);
                    students.set(j + 1, s1);
                }
            }
        }
    }

    // Задание 1
    public void printStudentsWithMark(int mark) {
        System.out.println("\n--- Ученики с оценкой " + mark + " ---");

        for (ClassGrade classGrade : classes.values()) {
            List<Student> studentsWithMark = classGrade.getStudentsWithMark(mark);

            if (!studentsWithMark.isEmpty()) {
                System.out.println("\nКласс " + classGrade.getGradeNumber() + ":");
                for (Student student : studentsWithMark) {
                    System.out.println("  " + student.getFullName() + " - " + student.getSubject());
                }
            }
        }
    }

    // Задание 2
    public void printClassesSortedByAverage() {
        System.out.println("\n--- Классы, отсортированные по средней успеваемости ---");

        List<ClassGrade> sortedClasses = new ArrayList<>(classes.values());

        for (int i = 0; i < sortedClasses.size() - 1; i++) {
            for (int j = 0; j < sortedClasses.size() - i - 1; j++) {
                ClassGrade class1 = sortedClasses.get(j);
                ClassGrade class2 = sortedClasses.get(j + 1);

                if (class2.getAverageMark() > class1.getAverageMark()) {
                    sortedClasses.set(j, class2);
                    sortedClasses.set(j + 1, class1);
                }
            }
        }

        for (ClassGrade classGrade: sortedClasses) {
            System.out.printf("Класс %2d: средний балл = %.2f%n", classGrade.getGradeNumber(),
                    classGrade.getAverageMark());
        }
    }

    // Задание 3
    public void printStudentsBySubject(String subject) {
        System.out.println("\n--- Ученики по предмету: " + subject + " ---");

        List<Student> studentsBySubject = new ArrayList<>();

        for (Student student: allStudents) {
            if (student.getSubject().equalsIgnoreCase(subject)) {
                studentsBySubject.add(student);
            }
        }

        if (studentsBySubject.isEmpty()) {
            System.out.println("Нет учеников по предмету: " + subject);
            return;
        }

        sortStudentsByLastName(studentsBySubject);

        for (Student student: studentsBySubject) {
            System.out.printf("%-20s (класс %2d) - оценка: %d%n",
                    student.getFullName(),
                    student.getGrade(),
                    student.getMark());
        }
    }

    // Задание 4
    public void printClassSubjectsReport(String filepath, int grade) throws IOException {
        if (!classes.containsKey(grade)) {
            System.out.println("Класс " + grade + " не найден.");
            return;
        }

        ClassGrade classGrade = classes.get(grade);
        String filename = "report_class_" + grade + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath + filename))) {
            writer.println("--- Ведомость класса " + grade + " --- ");
            writer.println("Всего учеников: " + classGrade.getStudents().size());
            writer.println("Средний балл: " + String.format("%.2f", classGrade.getAverageMark()));
            writer.println("-------------------------");

            Set<String> subjects = classGrade.getSubjects();

            for (String subject: subjects) {
                writer.println("\nПредмет: " + subject.toUpperCase());

                List<Student> subjectStudents = classGrade.getStudentsBySubject(subject);

                sortStudentsByLastName(subjectStudents);

                for (Student student : subjectStudents) {
                    writer.printf("  %s - оценка: %d%n",
                            student.getFullName(),
                            student.getMark());
                }

                double subjectAverage;
                if (subjectStudents.isEmpty()) {
                    subjectAverage = 0.0;
                } else {
                    int sum = 0;
                    for (Student student: subjectStudents) {
                        sum += student.getMark();
                    }
                    subjectAverage = (double) sum / subjectStudents.size();
                }

                writer.printf("Средний балл по предмету: %.2f%n", subjectAverage);
                writer.println("-------------------------");
            }

            System.out.println("Ведомость класса " + grade + " сохранена в файл: " + filename);
        }
    }

    // Задание 5
    public void findStudentClass(String lastName, String firstName) {
        System.out.println("\n--- Поиск ученика: " + lastName + " " + firstName + " ---");

        boolean found = false;

        for (Student student: allStudents) {
            if (student.getLastName().equalsIgnoreCase(lastName) &&
                    student.getFirstName().equalsIgnoreCase(firstName)) {

                System.out.println("Найден ученик:");
                System.out.println("  Класс: " + student.getGrade());
                System.out.println("  Предмет: " + student.getSubject());
                System.out.println("  Оценка: " + student.getMark());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Ученик " + lastName + " " + firstName + " не найден.");
        }
    }

    // Задание 6
    public void bestGradesSubject() {
        System.out.println("\n--- Предмет с самой высокой успеваемостью ---");

        Map<String, List<Integer>> subjectMarks = new HashMap<>();

        for (Student student: allStudents) {
            String subject = student.getSubject();
            subjectMarks.putIfAbsent(subject, new ArrayList<>());
            subjectMarks.get(subject).add(student.getMark());
        }

        if (subjectMarks.isEmpty()) {
            System.out.println("Нет данных о предметах.");
            return;
        }

        Map<String, Double> subjectAverages = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry: subjectMarks.entrySet()) {
            String subject = entry.getKey();
            List<Integer> marks = entry.getValue();

            double average;
            if (marks.isEmpty()) {
                average = 0.0;
            } else {
                int sum = 0;
                for (Integer mark: marks) {
                    sum += mark;
                }
                average = (double) sum / marks.size();
            }

            subjectAverages.put(subject, average);
        }

        Map.Entry<String, Double> bestSubject = null;

        for (Map.Entry<String, Double> entry: subjectAverages.entrySet()) {
            if (bestSubject == null || entry.getValue() > bestSubject.getValue()) {
                bestSubject = entry;
            }
        }

        if (bestSubject != null) {
            System.out.printf("Предмет: %s%n", bestSubject.getKey());
            System.out.printf("Средняя оценка: %.2f%n", bestSubject.getValue());
            System.out.printf("Количество оценок: %d%n", subjectMarks.get(bestSubject.getKey()).size());
        }
    }
}
