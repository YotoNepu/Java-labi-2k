package Laba7;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class ClassGrade {
    private int gradeNumber;
    private List<Student> students;

    public ClassGrade(int gradeNumber) {
        this.gradeNumber = gradeNumber;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public int getGradeNumber() {
        return gradeNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Student> getStudentsWithMark(int mark) {
        List<Student> result = new ArrayList<>();
        for (Student student: students) {
            if (student.getMark() == mark) {
                result.add(student);
            }
        }
        return result;
    }

    public double getAverageMark() {
        if (students.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (Student student: students) {
            sum += student.getMark();
        }
        return (double) sum / students.size();
    }

    public Set<String> getSubjects() {
        Set<String> subjects = new HashSet<>();
        for (Student student: students) {
            subjects.add(student.getSubject());
        }
        return subjects;
    }

    public List<Student> getStudentsBySubject(String subject) {
        List<Student> result = new ArrayList<>();
        for (Student student: students) {
            if (student.getSubject().equals(subject)) {
                result.add(student);
            }
        }
        return result;
    }

    public boolean hasStudent(String lastName, String firstName) {
        for (Student student: students) {
            if (student.getLastName().equals(lastName) &&
                    student.getFirstName().equals(firstName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Класс " + gradeNumber + " (учеников: " + students.size() +
                ", средний балл: " + String.format("%.2f", getAverageMark()) + ")";
    }
}
