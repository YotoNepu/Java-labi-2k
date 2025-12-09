package Indiv2;

import java.util.Objects;

public class Student {
    private final String gender;
    private final String group;
    private final String educationLevel;
    private final String paymentCondition;
    private final String completionStatus;
    private final int mathScore;
    private final int readingScore;
    private final int writingScore;

    public Student(String gender, String group, String educationLevel,
                   String paymentCondition, String completionStatus,
                   int mathScore, int readingScore, int writingScore) {
        this.gender = Objects.requireNonNull(gender, "Gender cannot be null");
        this.group = Objects.requireNonNull(group, "Group cannot be null");
        this.educationLevel = Objects.requireNonNull(educationLevel, "Education level cannot be null");
        this.paymentCondition = Objects.requireNonNull(paymentCondition, "Payment condition cannot be null");
        this.completionStatus = Objects.requireNonNull(completionStatus, "Completion status cannot be null");
        this.mathScore = validateScore(mathScore, "math");
        this.readingScore = validateScore(readingScore, "reading");
        this.writingScore = validateScore(writingScore, "writing");
    }

    private int validateScore(int score, String subject) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    String.format("Score for %s must be between 0 and 100, got: %d", subject, score)
            );
        }
        return score;
    }

    // Геттеры
    public String getGender() { return gender; }
    public String getGroup() { return group; }
    public String getEducationLevel() { return educationLevel; }
    public String getPaymentCondition() { return paymentCondition; }
    public String getCompletionStatus() { return completionStatus; }
    public int getMathScore() { return mathScore; }
    public int getReadingScore() { return readingScore; }
    public int getWritingScore() { return writingScore; }

    public int getScoreBySubject(String subject) {
        switch (subject.toLowerCase()) {
            case "math": return mathScore;
            case "reading": return readingScore;
            case "writing": return writingScore;
            default: throw new IllegalArgumentException("Unknown subject: " + subject);
        }
    }

    public int getTotalScore() {
        return mathScore + readingScore + writingScore;
    }

    public double getAverageScore() {
        return getTotalScore() / 3.0;
    }

    @Override
    public String toString() {
        return String.format("%s (группа: %s, уровень: %s, сумма: %d)",
                group, educationLevel, getTotalScore());
    }
}