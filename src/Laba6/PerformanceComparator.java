package Laba6;

import java.util.Comparator;

class PerformanceComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.getPerformanceRating(), s1.getPerformanceRating());
    }
}
