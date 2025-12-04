package Laba6;

import java.util.List;

class UniversityStudent extends Student {
    private String university;

    public UniversityStudent(String lastName, String firstName, List<Integer> grades,
                             String university) throws InvalidDataException {
        super(lastName, firstName, grades);
        validateUniversity(university);
        this.university = university;
    }

    private void validateUniversity(String university) throws InvalidDataException {
        if (university == null || university.trim().isEmpty()) {
            throw new InvalidDataException("Ошибка. Название университета не может быть пустым.");
        }
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) throws InvalidDataException {
        validateUniversity(university);
        this.university = university;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s, Рейтинг: %.2f)",
                lastName, firstName, university, getPerformanceRating());
    }
}