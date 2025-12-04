package Laba6;

import java.util.ArrayList;
import java.util.List;

abstract class Student implements Comparable<Student>, PerformanceComparable {
    protected String lastName;
    protected String firstName;
    protected List<Integer> grades;

    public Student(String lastName, String firstName, List<Integer> grades)
            throws InvalidDataException {
        validateName(lastName);
        validateName(firstName);
        validateGrades(grades);

        this.lastName = lastName;
        this.firstName = firstName;
        this.grades = new ArrayList<>(grades);
    }

    protected void validateName(String name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Ошибка. Имя не может быть пустым.");
        }
        if (!Character.isUpperCase(name.charAt(0))) {
            throw new InvalidDataException("Ошибка. Имя должно начинаться с заглавной буквы.");
        }
        for (int i = 1; i < name.length(); i++) {
            if (!Character.isLowerCase(name.charAt(i))) {
                throw new InvalidDataException("Ошибка. В имени после первой буквы должны быть строчные.");
            }
        }
    }

    protected void validateGrades(List<Integer> grades) throws InvalidDataException {
        if (grades == null || grades.isEmpty()) {
            throw new InvalidDataException("Ошибка. Список оценок не может быть пустым.");
        }
        for (Integer grade: grades) {
            if (grade == null || grade < 2 || grade > 5) {
                throw new InvalidDataException("Ошибка. Оценка должна быть числом от 2 до 5.");
            }
        }
    }

    @Override
    public double getPerformanceRating() {
        if (grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    @Override
    public int compareTo(Student other) {
        return this.lastName.compareTo(other.lastName);
    }

    public boolean canGiveScholarship() {
        return getPerformanceRating() > 4.5;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InvalidDataException {
        validateName(lastName);
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InvalidDataException {
        validateName(firstName);
        this.firstName = firstName;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

    public void setGrades(List<Integer> grades) throws InvalidDataException {
        validateGrades(grades);
        this.grades = new ArrayList<>(grades);
    }

    public void addGrade(int grade) throws InvalidDataException {
        validateGrades(new ArrayList<>(grade));
        this.grades.add(grade);
    }

    @Override
    public String toString() {
        return String.format("%s %s (Рейтинг: %.2f)", lastName, firstName, getPerformanceRating());
    }
}
