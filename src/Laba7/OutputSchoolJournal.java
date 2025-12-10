package Laba7;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class OutputSchoolJournal {
    private SchoolJournal journalToPrint;
    
    public OutputSchoolJournal(SchoolJournal journal) {
        this.journalToPrint = journal;
    }
    
    public void printClassesToConsoleAndFiles(String filepath) throws IOException {
        System.out.println("\n--- Список всех классов ---");

        for (ClassGrade classGrade: journalToPrint.getClasses().values()) {
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
                journalToPrint.sortStudentsByLastName(sortedStudents);

                for (Student student: sortedStudents) {
                    writer.println(student);
                }
            }

            System.out.println("-> Данные сохранены в файл: " + filename);
        }
    }

    // Задание 1
    public void printStudentsWithMark(int mark) {
        System.out.println("\n--- Ученики с оценкой " + mark + " ---");

        for (ClassGrade classGrade: journalToPrint.getClasses().values()) {
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
    public void printClassesSortedByAverage(List<ClassGrade> sortedClasses) {
        System.out.println("\n--- Классы, отсортированные по средней успеваемости ---");
        
        for (ClassGrade classGrade: sortedClasses) {
            System.out.printf("Класс %2d: средний балл = %.2f%n", classGrade.getGradeNumber(),
                    classGrade.getAverageMark());
        }
    }

    // Задание 3
    public void printStudentsBySubject(String subject) {
        System.out.println("\n--- Ученики по предмету: " + subject + " ---");

        List<Student> studentsBySubject = new ArrayList<>();

        for (Student student: journalToPrint.getAllStudents()) {
            if (student.getSubject().equalsIgnoreCase(subject)) {
                studentsBySubject.add(student);
            }
        }

        if (studentsBySubject.isEmpty()) {
            System.out.println("Нет учеников по предмету: " + subject);
            return;
        }

        journalToPrint.sortStudentsByLastName(studentsBySubject);

        for (Student student: studentsBySubject) {
            System.out.printf("%-20s (класс %2d) - оценка: %d%n",
                    student.getFullName(),
                    student.getGrade(),
                    student.getMark());
        }
    }

    // Задание 4
    public void printClassSubjectsReport(String filepath, int grade) throws IOException {
        if (!journalToPrint.getClasses().containsKey(grade)) {
            System.out.println("Класс " + grade + " не найден.");
            return;
        }

        ClassGrade classGrade = journalToPrint.getClasses().get(grade);
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

                for (Student student: subjectStudents) {
                    writer.printf("  %s - оценка: %d%n",
                            student.getFullName(),
                            student.getMark());
                }
                
                writer.printf("Средний балл по предмету: %.2f%n", journalToPrint.getSubjectsAverage(subjectStudents, subject));
                writer.println("-------------------------");
            }

            System.out.println("Ведомость класса " + grade + " сохранена в файл: " + filename);
        }
    }

    // Задание 5
    public void findStudentClass(String lastName, String firstName) {
        System.out.println("\n--- Поиск ученика: " + lastName + " " + firstName + " ---");

        boolean found = false;

        for (Student student: journalToPrint.getAllStudents()) {
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

        Map<String, List<Integer>> subjectMarks = journalToPrint.getSubjectMarks();

        if (subjectMarks.isEmpty()) {
            System.out.println("Нет данных о предметах.");
            return;
        }

        Map.Entry<String, Double> bestSubject = journalToPrint.getBestSubject();
        if (bestSubject != null) {
            System.out.printf("Предмет: %s%n", bestSubject.getKey());
            System.out.printf("Средняя оценка: %.2f%n", bestSubject.getValue());
            System.out.printf("Количество оценок: %d%n", subjectMarks.get(bestSubject.getKey()).size());
        }
    }
}
