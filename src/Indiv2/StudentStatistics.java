package Indiv2;

import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StudentStatistics {
    private final List<Student> students;

    public StudentStatistics(List<Student> students) {
        this.students = List.copyOf(students);
    }

    public Map<String, Map<String, List<Student>>> groupByGroupAndEducationLevel() {
        return students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGroup,
                        Collectors.groupingBy(Student::getEducationLevel)
                ));
    }

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

    private GroupStats calculateGroupStats(List<Student> groupStudents) {
        long count = groupStudents.size();
        double averageScore = groupStudents.stream()
                .mapToDouble(Student::getAverageScore)
                .average()
                .orElse(0.0);

        return new GroupStats(count, averageScore);
    }

    public ScoreRange getScoreRangeBySubjectAndGender(String subject, String gender) {
        List<Student> filteredStudents = students.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .toList();

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

    public Map<String, List<Student>> getTopFiveByEducationLevel(String educationLevel) {
        List<Student> levelStudents = students.stream()
                .filter(s -> s.getEducationLevel().equalsIgnoreCase(educationLevel))
                .toList();

        List<Student> completed = levelStudents.stream()
                .filter(s -> "completed".equalsIgnoreCase(s.getCompletionStatus()))
                .sorted(Comparator.comparingInt(Student::getTotalScore).reversed())
                .limit(5)
                .toList();

        List<Student> notCompleted = levelStudents.stream()
                .filter(s -> "none".equalsIgnoreCase(s.getCompletionStatus()))
                .sorted(Comparator.comparingInt(Student::getTotalScore).reversed())
                .limit(5)
                .toList();

        return Map.of(
                "completed", completed,
                "not_completed", notCompleted
        );
    }
}
