package Laba6;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        try {
            List<SchoolStudent> schoolStudents = createSchoolStudents();
            List<UniversityStudent> universityStudents = createUniversityStudents();

            List<Student> allStudents = new ArrayList<>();
            allStudents.addAll(schoolStudents);
            allStudents.addAll(universityStudents);

            task1(allStudents);
            task2(schoolStudents, universityStudents);
            task3(schoolStudents);
            task4(universityStudents);
        }
        catch (InvalidDataException e) {
            System.out.println("Критическая ошибка. " + e.getMessage());
        }
    }

    private static List<SchoolStudent> createSchoolStudents() throws InvalidDataException {
        List<SchoolStudent> schoolStudents = new ArrayList<>();

        schoolStudents.add(new SchoolStudent("Иванов", "Петр",
                Arrays.asList(5, 5, 5, 5), 15));
        schoolStudents.add(new SchoolStudent("Петров", "Иван",
                Arrays.asList(4, 5, 4, 5, 4), 10));
        schoolStudents.add(new SchoolStudent("Сидоров", "Алексей",
                Arrays.asList(3, 4, 4, 5), 15));
        schoolStudents.add(new SchoolStudent("Кузнецов", "Дмитрий",
                Arrays.asList(5, 5, 4, 5, 5), 8));
        schoolStudents.add(new SchoolStudent("Алексеев", "Сергей",
                Arrays.asList(5, 5, 5, 5, 5), 20));
        schoolStudents.add(new SchoolStudent("Морозов", "Антон",
                Arrays.asList(5, 4, 5, 4), 12));
        schoolStudents.add(new SchoolStudent("Николаев", "Владимир",
                Arrays.asList(4, 4, 4, 5), 15));
        schoolStudents.add(new SchoolStudent("Федоров", "Максим",
                Arrays.asList(5, 5, 5, 5), 10));

        return schoolStudents;
    }

    private static List<UniversityStudent> createUniversityStudents() throws InvalidDataException {
        List<UniversityStudent> universityStudents = new ArrayList<>();

        universityStudents.add(new UniversityStudent("Смирнов", "Андрей",
                Arrays.asList(5, 5, 4, 5), "МГУ"));
        universityStudents.add(new UniversityStudent("Попов", "Сергей",
                Arrays.asList(4, 4, 5, 4, 5), "МГТУ"));
        universityStudents.add(new UniversityStudent("Васильев", "Михаил",
                Arrays.asList(3, 4, 4, 3), "ЯрГУ"));
        universityStudents.add(new UniversityStudent("Новиков", "Александр",
                Arrays.asList(5, 5, 5, 5, 5), "ЯрГУ"));
        universityStudents.add(new UniversityStudent("Орлов", "Денис",
                Arrays.asList(4, 5, 4, 5), "СПбГУ"));
        universityStudents.add(new UniversityStudent("Киселев", "Павел",
                Arrays.asList(5, 5, 5, 5), "ЯГТУ"));
        universityStudents.add(new UniversityStudent("Белов", "Артем",
                Arrays.asList(4, 4, 4, 4), "ЯГТУ"));
        universityStudents.add(new UniversityStudent("Григорьев", "Константин",
                Arrays.asList(5, 5, 5, 5, 4), "ЯрГУ"));

        return universityStudents;
    }

    private static void task1(List<Student> allStudents) {
        System.out.println("-".repeat(70));

        List<Student> scholarshipStudents = new ArrayList<>();
        for (Student student: allStudents) {
            if (student.canGiveScholarship()) {
                scholarshipStudents.add(student);
            }
        }

        Collections.sort(scholarshipStudents);

        if (scholarshipStudents.isEmpty()) {
            System.out.println("Нет кандидатов на стипендию.");
        } else {
            printStudents("Кандидаты на стипендию", scholarshipStudents);
        }

        System.out.println("-".repeat(70));
    }

    private static void task2(List<SchoolStudent> schoolStudents,
                              List<UniversityStudent> universityStudents) {
        System.out.println("-".repeat(70));

        SchoolStudent bestSchoolStudent = findBestSchoolStudent(schoolStudents);
        if (bestSchoolStudent != null) {
            System.out.println("Лучший школьник: " + bestSchoolStudent);
        }

        UniversityStudent bestUniversityStudent = findBestUniversityStudent(universityStudents);
        if (bestUniversityStudent != null) {
            System.out.println("Лучший студент: " + bestUniversityStudent);
        }

        System.out.println("-".repeat(70));
    }

    private static SchoolStudent findBestSchoolStudent(List<SchoolStudent> schoolStudents) {
        if (schoolStudents.isEmpty()) return null;

        int comparation;
        SchoolStudent best = schoolStudents.getFirst();
        for (SchoolStudent student: schoolStudents) {
            comparation = Double.compare(student.getPerformanceRating(), best.getPerformanceRating());
            if (comparation > 0) {
                best = student;
            }
        }
        return best;
    }

    private static UniversityStudent findBestUniversityStudent(List<UniversityStudent> universityStudents) {
        if (universityStudents.isEmpty()) return null;

        int comparation;
        UniversityStudent best = universityStudents.getFirst();
        for (UniversityStudent student: universityStudents) {
            comparation = Double.compare(student.getPerformanceRating(), best.getPerformanceRating());
            if (comparation > 0) {
                best = student;
            }
        }
        return best;
    }

    private static void task3(List<SchoolStudent> schoolStudents) {
        System.out.println("-".repeat(70));

        schoolStudents.sort(new SchoolStudentComparator());
        printStudents("Рейтинг школьников", schoolStudents);

        System.out.println("-".repeat(70));
    }

    private static void task4(List<UniversityStudent> universityStudents) {
        System.out.println("-".repeat(70));

        universityStudents.sort(new PerformanceComparator());
        printStudents("Рейтинг студентов", universityStudents);

        System.out.println("-".repeat(70));
    }

    private static void printStudents(String title, List<?> students) {
        System.out.println(title + ":");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
}