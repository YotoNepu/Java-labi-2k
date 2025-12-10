package Indiv2;

public class GroupStats {
    private final long studentCount;
    private final double averageScore;

    public GroupStats(long studentCount, double averageScore) {
        this.studentCount = studentCount;
        this.averageScore = averageScore;
    }

    public long getStudentCount() {
        return studentCount;
    }

    public double getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return String.format("Count: %d, Average: %.2f", studentCount, averageScore);
    }
}
