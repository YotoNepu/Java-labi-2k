package Laba6;

import java.util.Comparator;

class SchoolStudentComparator implements Comparator<SchoolStudent> {
    @Override
    public int compare(SchoolStudent s1, SchoolStudent s2) {

        int performanceCompare = Double.compare(s2.getPerformanceRating(), s1.getPerformanceRating());
        if (performanceCompare != 0) {
            return performanceCompare;
        }

        return Integer.compare(s1.getSchoolNumber(), s2.getSchoolNumber());
    }
}
