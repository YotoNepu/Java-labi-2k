package Laba5;

class OlympiadParticipation {
    private String subject;
    private OlympiadLevel level;
    private int place;

    public OlympiadParticipation(String subject, OlympiadLevel level, int place) {
        this.subject = subject;
        this.level = level;
        this.place = place;
    }

    public String getSubject() {return subject;}

    public OlympiadLevel getLevel() {return level;}

    public int getPlace() {return place;}

    public boolean isFirstPlace() {return place == 1;}

    @Override
    public String toString() {
        String placeStr = (place > 3 || place == 0) ? "участие" : place + " место";
        return subject + " - " + level.getDescription() + " (" + placeStr + ")";
    }
}
