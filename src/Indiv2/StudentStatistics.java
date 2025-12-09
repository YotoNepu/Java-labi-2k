package Indiv2;

import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Класс для вычисления статистики по студентам.
 * Использует Stream API и лямбда-выражения согласно условию задачи.
 */
public class StudentStatistics {
    private final List<Student> students;

    public StudentStatistics(List<Student> students) {
        this.students = List.copyOf(students);
    }

    /**
     * Группирует студентов по группе и уровню образования
     */
    public Map<String, Map<String, List<Student>>> groupByGroupAndEducationLevel() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGroup,
                        Collectors.groupingBy(Student::getEducationLevel)
                ));
    }

    /**
     * Возвращает статистику по группам и уровням образования
     */
    public Map<String, Map<String, GroupStats>> getGroupStatistics() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGroup,
                        Collectors.groupingBy(
                                Student::getEducationLevel,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        this::calculateGroupStats
                                )
                        )
                ));
    }

    /**
     * Рассчитывает статистику для группы студентов
     */
    private GroupStats calculateGroupStats(List<Student> groupStudents) {
        long count = groupStudents.size();
        double averageScore = groupStudents.stream()
                .mapToDouble(Student::getAverageScore)
                .average()
                .orElse(0.0);

        return new GroupStats(count, averageScore);
    }

    /**
     * Находит максимальный и минимальный балл по выбранному предмету и полу
     */
    public ScoreRange getScoreRangeBySubjectAndGender(String subject, String gender) {
        List<Student> filteredStudents = students.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());

        if (filteredStudents.isEmpty()) {
            return new ScoreRange(0, 0);
        }

        int maxScore = filteredStudents.stream()
                .mapToInt(s -> s.getScoreBySubject(subject))
                .max()
                .orElse(0);

        int minScore = filteredStudents.stream()
                .mapToInt(s -> s.getScoreBySubject(subject))
                .min()
                .orElse(0);

        return new ScoreRange(minScore, maxScore);
    }

    /**
     * Возвращает 5 лучших результатов для закончивших и незакончивших указанный уровень образования
     */
    public Map<String, List<Student>> getTopFiveByEducationLevel(String educationLevel) {
        List<Student> levelStudents = students.stream()
                .filter(s -> s.getEducationLevel().equalsIgnoreCase(educationLevel))
                .collect(Collectors.toList());

        List<Student> completed = levelStudents.stream()
                .filter(s -> "completed".equalsIgnoreCase(s.getCompletionStatus()))
                .sorted(Comparator.comparingInt(Student::getTotalScore).reversed())
                .limit(5)
                .collect(Collectors.toList());

        List<Student> notCompleted = levelStudents.stream()
                .filter(s -> "none".equalsIgnoreCase(s.getCompletionStatus()))
                .sorted(Comparator.comparingInt(Student::getTotalScore).reversed())
                .limit(5)
                .collect(Collectors.toList());

        return Map.of(
                "completed", completed,
                "not_completed", notCompleted
        );
    }

    /**
     * Вспомогательный класс для хранения статистики группы
     */
    public static class GroupStats {
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

    /**
     * Вспомогательный класс для хранения диапазона баллов
     */
    public static class ScoreRange {
        private final int minScore;
        private final int maxScore;

        public ScoreRange(int minScore, int maxScore) {
            this.minScore = minScore;
            this.maxScore = maxScore;
        }

        public int getMinScore() {
            return minScore;
        }

        public int getMaxScore() {
            return maxScore;
        }

        @Override
        public String toString() {
            return String.format("Min: %d, Max: %d", minScore, maxScore);
        }
    }
}
