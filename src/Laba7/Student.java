package Laba7;

import java.util.Objects;

public class Student {
    private String lastName;
    private String firstName;
    private int grade;
    private String subject;
    private int mark;

    public Student(String lastName, String firstName, int grade, String subject, int mark) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.grade = grade;
        this.subject = subject;
        this.mark = mark;
    }

    public String getLastName() {return lastName;}

    public String getFirstName() {return firstName;}

    public String getFullName() {return lastName + " " + firstName;}

    public int getGrade() {return grade;}

    public String getSubject() {return subject;}

    public int getMark() {return mark;}

    @Override
    public String toString() {
        return lastName + " " + firstName + " - " + subject + ": " + mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return grade == student.grade &&
                mark == student.mark &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(subject, student.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, grade, subject, mark);
    }
}
