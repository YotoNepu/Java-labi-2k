package Laba5;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<SchoolStudent> schoolStudents = createSchoolStudents();
        List<UniversityStudent> universityStudents = createUniversityStudents();

        task1(schoolStudents);
        task2(schoolStudents, universityStudents);
        int scholarshipCount = task3(schoolStudents, universityStudents);

        if (scholarshipCount == 0) {
            System.out.println("Нет кандидатов на специальную стипендию.");
        }
    }

    private static void task1(List<SchoolStudent> schoolStudents) {
        System.out.println("-----------------------------------------------");
        System.out.println("Девочки, получившие первые места на олимпиадах:");
        for (SchoolStudent student: schoolStudents) {
            if (student.getGender().equals("девочка") && student.hasFirstPlaceInOlympiad()) {
                System.out.println(student);
                System.out.println("  Первые места на олимпиадах:");
                for (OlympiadParticipation olympiad : student.getOlympiads()) {
                    if (olympiad.isFirstPlace()) {
                        System.out.println("    - " + olympiad);
                    }
                }
                System.out.println();
            }
        }
        System.out.println("-----------------------------------------------");
    }

    private static void task2(List<SchoolStudent> schoolStudents, List<UniversityStudent> universityStudents) {
        System.out.println("-----------------------------------------------");
        System.out.println("Студенты, имеющие курсовые работы:");
        for (UniversityStudent student : universityStudents) {
            if (student.hasCourseWorks()) {
                System.out.println(student);
                System.out.println();
            }
        }
        System.out.println("-----------------------------------------------");
    }

    private static int task3(List<SchoolStudent> schoolStudents, List<UniversityStudent> universityStudents) {
        System.out.println("-----------------------------------------------");
        System.out.println("Кандидаты на специальную стипендию:");

        List<Student> allStudents = new ArrayList<>();
        allStudents.addAll(schoolStudents);
        allStudents.addAll(universityStudents);

        int scholarshipCount = 0;
        for (Student student: allStudents) {
            if (student.isEligibleForSpecialScholarship()) {
                scholarshipCount++;
                System.out.println(scholarshipCount + ". " + student);
                System.out.println();
            }
        }
        System.out.println("-----------------------------------------------");

        return scholarshipCount;
    }

    public static List<SchoolStudent> createSchoolStudents() {
        List<SchoolStudent> schoolStudents = new ArrayList<>();

        SchoolStudent student1 = new SchoolStudent("Анна Иванова", "девочка", 16);
        student1.addYearlyGrade("математика", 5);
        student1.addYearlyGrade("русский язык", 5);
        student1.addYearlyGrade("история", 5);
        student1.addYearlyGrade("английский язык", 5);
        student1.addYearlyGrade("физика", 4);
        student1.addOlympiadParticipation(new OlympiadParticipation("математика", OlympiadLevel.REGIONAL, 0));
        student1.addOlympiadParticipation(new OlympiadParticipation("физика", OlympiadLevel.SCHOOL, 1));
        schoolStudents.add(student1);

        SchoolStudent student2 = new SchoolStudent("Мария Петрова", "девочка", 15);
        student2.addYearlyGrade("математика", 5);
        student2.addYearlyGrade("русский язык", 5);
        student2.addYearlyGrade("история", 5);
        student2.addYearlyGrade("английский язык", 5);
        student2.addYearlyGrade("химия", 5);
        student2.addOlympiadParticipation(new OlympiadParticipation("химия", OlympiadLevel.CITY, 2));
        schoolStudents.add(student2);

        SchoolStudent student3 = new SchoolStudent("Иван Сидоров", "мальчик", 17);
        student3.addYearlyGrade("математика", 4);
        student3.addYearlyGrade("русский язык", 4);
        student3.addYearlyGrade("история", 5);
        student3.addYearlyGrade("английский язык", 4);
        student3.addOlympiadParticipation(new OlympiadParticipation("история", OlympiadLevel.SCHOOL, 1));
        schoolStudents.add(student3);

        SchoolStudent student4 = new SchoolStudent("Елена Смирнова", "девочка", 16);
        student4.addYearlyGrade("математика", 5);
        student4.addYearlyGrade("русский язык", 5);
        student4.addYearlyGrade("история", 5);
        student4.addYearlyGrade("английский язык", 5);
        student4.addYearlyGrade("биология", 4);
        student4.addOlympiadParticipation(new OlympiadParticipation("биология", OlympiadLevel.SCHOOL, 2));
        schoolStudents.add(student4);

        return schoolStudents;
    }

    public static List<UniversityStudent> createUniversityStudents() {
        List<UniversityStudent> universityStudents = new ArrayList<>();

        UniversityStudent student5 = new UniversityStudent("Алексей Козлов", "юноша", 20);
        student5.addSessionGrade(5);
        student5.addSessionGrade(5);
        student5.addSessionGrade(4);
        student5.addSessionGrade(5);
        student5.addCourseWork(new WorkGrade("Программирование", 5));
        student5.addCourseWork(new WorkGrade("Математика", 5));
        universityStudents.add(student5);

        UniversityStudent student6 = new UniversityStudent("Ольга Новикова", "девушка", 19);
        student6.addSessionGrade(4);
        student6.addSessionGrade(5);
        student6.addSessionGrade(4);
        student6.addSessionGrade(4);
        universityStudents.add(student6);

        UniversityStudent student7 = new UniversityStudent("Дмитрий Волков", "юноша", 21);
        student7.addSessionGrade(5);
        student7.addSessionGrade(5);
        student7.addSessionGrade(5);
        student7.addSessionGrade(5);
        student7.addCourseWork(new WorkGrade("Физика", 5));
        student7.addCourseWork(new WorkGrade("Химия", 4)); // Здесь оценка 4, не подходит для стипендии
        universityStudents.add(student7);

        UniversityStudent student8 = new UniversityStudent("Татьяна Белова", "девушка", 20);
        student8.addSessionGrade(5);
        student8.addSessionGrade(5);
        student8.addSessionGrade(5);
        student8.addSessionGrade(4);
        student8.addCourseWork(new WorkGrade("Экономика", 5));
        universityStudents.add(student8);

        return universityStudents;
    }
}