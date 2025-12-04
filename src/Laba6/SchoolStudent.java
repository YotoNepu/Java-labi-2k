package Laba6;

import java.util.List;

class SchoolStudent extends Student {
    private int schoolNumber;

    public SchoolStudent(String lastName, String firstName, List<Integer> grades, int schoolNumber)
            throws InvalidDataException {
        super(lastName, firstName, grades);
        validateSchoolNumber(schoolNumber);
        this.schoolNumber = schoolNumber;
    }

    private void validateSchoolNumber(int schoolNumber) throws InvalidDataException {
        if (schoolNumber <= 0) {
            throw new InvalidDataException("Номер школы должен быть положительным числом: " + schoolNumber);
        }
    }

    public int getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(int schoolNumber) throws InvalidDataException {
        validateSchoolNumber(schoolNumber);
        this.schoolNumber = schoolNumber;
    }

    @Override
    public String toString() {
        return String.format("%s %s (Школа №%d, Рейтинг: %.2f)",
                lastName, firstName, schoolNumber, getPerformanceRating());
    }
}
