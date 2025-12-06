package Indiv1;

import java.time.LocalDate;

class CometDateInfo {
    private String number;
    private String russianName;
    private LocalDate perihelionDate;

    public CometDateInfo(String number, String russianName, LocalDate perihelionDate) {
        this.number = number;
        this.russianName = russianName;
        this.perihelionDate = perihelionDate;
    }

    public String getNumber() {
        return number;
    }

    public String getRussianName() {
        return russianName;
    }

    public LocalDate getPerihelionDate() {
        return perihelionDate;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }

    public void setPerihelionDate(LocalDate perihelionDate) {
        this.perihelionDate = perihelionDate;
    }

    @Override
    public String toString() {
        return String.format("CometDateInfo: Number=%s, Name=%s, PerihelionDate=%s",
                number, russianName, perihelionDate);
    }
}
