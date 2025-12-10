package Laba7;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class SchoolJournal {
    private Map<Integer, ClassGrade> classes;
    private List<Student> allStudents;

    public SchoolJournal() {
        classes = new TreeMap<>();
        allStudents = new ArrayList<>();
    }

    public Map<Integer, ClassGrade> getClasses() {
        return classes;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void addStudent(Student student) {
        allStudents.add(student);
    }

    public void putClass(Integer classes, ClassGrade grade) {
        this.classes.put(classes, grade);
    }

    public void sortStudentsByLastName(List<Student> students) {
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

    public List<ClassGrade> sortClassesByAverage(List<ClassGrade> classesToSort) {
        for (int i = 0; i < classesToSort.size() - 1; i++) {
            for (int j = 0; j < classesToSort.size() - i - 1; j++) {
                ClassGrade class1 = classesToSort.get(j);
                ClassGrade class2 = classesToSort.get(j + 1);

                if (class2.getAverageMark() > class1.getAverageMark()) {
                    classesToSort.set(j, class2);
                    classesToSort.set(j + 1, class1);
                }
            }
        }

        return classesToSort;
    }

    public double getSubjectsAverage(List<Student> subjectStudents, String subject) {
        sortStudentsByLastName(subjectStudents);
        double subjectAverage;

        if (subjectStudents.isEmpty()) {
            return  0.0;
        } else {
            int sum = 0;
            for (Student student: subjectStudents) {
                sum += student.getMark();
            }
            subjectAverage = (double) sum / subjectStudents.size();
        }

        return subjectAverage;
    }

    public Map<String, List<Integer>> getSubjectMarks() {
        Map<String, List<Integer>> subjectMarks = new HashMap<>();

        for (Student student: getAllStudents()) {
            String subject = student.getSubject();
            subjectMarks.putIfAbsent(subject, new ArrayList<>());
            subjectMarks.get(subject).add(student.getMark());
        }
        return subjectMarks;
    }

    public Map.Entry<String, Double> getBestSubject() {
        Map<String, Double> subjectAverages = new HashMap<>();
        Map<String, List<Integer>> subjectMarks = getSubjectMarks();

        for (Map.Entry<String, List<Integer>> entry : subjectMarks.entrySet()) {
            String subject = entry.getKey();
            List<Integer> marks = entry.getValue();

            double average;
            if (marks.isEmpty()) {
                average = 0.0;
            } else {
                int sum = 0;
                for (Integer mark : marks) {
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

        return bestSubject;
    }
}
