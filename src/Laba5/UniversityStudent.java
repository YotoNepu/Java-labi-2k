package Laba5;

import java.util.ArrayList;
import java.util.List;

class UniversityStudent extends Student {
    private List<Integer> sessionGrades;
    private List<WorkGrade> courseWorks;

    public UniversityStudent(String name, String gender, int age) {
        super(name, gender, age);
        this.sessionGrades = new ArrayList<>();
        this.courseWorks = new ArrayList<>();
    }

    public void addSessionGrade(int grade) {sessionGrades.add(grade);}

    public void addCourseWork(WorkGrade courseWork) {courseWorks.add(courseWork);}

    public double getAverageGrade() {
        if (sessionGrades.isEmpty()) return 0;
        int sum = 0;
        for (int grade: sessionGrades) {
            sum += grade;
        }
        return (double) sum / sessionGrades.size();
    }

    public boolean hasCourseWorks() {
        return !courseWorks.isEmpty();
    }

    @Override
    public boolean canGiveSpecialScholarship() {
        if (getAverageGrade() < 4.75) {
            return false;
        }

        for (WorkGrade courseWork: courseWorks) {
            if (courseWork.getGrade() != 5) {
                return false;
            }
        }

        return !courseWorks.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Студент: ").append(super.toString()).append("\n");
        sb.append("  Оценки за сессию: ").append(sessionGrades).append("\n");
        sb.append("  Средний балл: ").append(String.format("%.2f", getAverageGrade())).append("\n");
        sb.append("  Курсовые: ");
        for (WorkGrade courseWork: courseWorks) {
            sb.append(courseWork).append("; ");
        }
        return sb.toString();
    }
}
