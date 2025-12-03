package Laba5;

import java.util.ArrayList;
import java.util.List;

class SchoolStudent extends Student {
    private List<WorkGrade> yearlyGrades;
    private List<OlympiadParticipation> olympiads;

    public SchoolStudent(String name, String gender, int age) {
        super(name, gender, age);
        this.yearlyGrades = new ArrayList<>();
        this.olympiads = new ArrayList<>();
    }

    public void addYearlyGrade(String subject, int grade) {
        yearlyGrades.add(new WorkGrade(subject, grade));
    }

    public void addOlympiadParticipation(OlympiadParticipation participation) {
        olympiads.add(participation);
    }

    public int getGradeForSubject(String subject) {
        for (WorkGrade grade: yearlyGrades) {
            if (grade.getSubject().equals(subject)) {
                return grade.getGrade();
            }
        }
        return -1;
    }

    public List<WorkGrade> getYearlyGrades() {
        return yearlyGrades;
    }

    public List<OlympiadParticipation> getOlympiads() {
        return olympiads;
    }

    public boolean hasFirstPlaceInOlympiad() {
        for (OlympiadParticipation olympiad : olympiads) {
            if (olympiad.isFirstPlace()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEligibleForSpecialScholarship() {
        String[] keySubjects = {"математика", "русский язык", "история", "английский язык"};

        for (String subject: keySubjects) {
            int grade = getGradeForSubject(subject);
            if (grade != 5) {
                return false;
            }
        }

        for (WorkGrade workGrade : yearlyGrades) {
            if (workGrade.getGrade() < 4) {
                return false;
            }
        }

        for (OlympiadParticipation olympiad: olympiads) {
            if (olympiad.getLevel() == OlympiadLevel.REGIONAL) {
                return true;
            } else if (olympiad.getLevel() == OlympiadLevel.SCHOOL && olympiad.getPlace() == 1) {
                return true;
            } else if (olympiad.getLevel() == OlympiadLevel.CITY && olympiad.getPlace() <= 3 && olympiad.getPlace() > 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Школьник: ").append(super.toString()).append("\n");
        sb.append("  Оценки: ");

        for (int i = 0; i < yearlyGrades.size(); i++) {
            sb.append(yearlyGrades.get(i));
            if (i < yearlyGrades.size() - 1) {
                sb.append("; ");
            }
        }

        sb.append("\n  Олимпиады: ");
        for (int i = 0; i < olympiads.size(); i++) {
            sb.append(olympiads.get(i));
            if (i < olympiads.size() - 1) {
                sb.append("; ");
            }
        }

        return sb.toString();
    }
}