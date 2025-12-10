package Indiv2;

public class ScoreRange {
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
