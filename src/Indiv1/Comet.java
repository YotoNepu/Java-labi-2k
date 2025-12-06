package Indiv1;

class Comet {
    private String numberName;
    private double eccentricity;
    private double inclination;
    private double size;
    private double perihelion;
    private double aphelion;
    private double orbitalPeriod;

    public Comet(String numberName, double eccentricity, double inclination,
                 double size, double perihelion, double aphelion, double orbitalPeriod) {
        this.numberName = numberName;
        validateDouble(eccentricity, 0, 1, true, false, "Эксцентриситет");
        this.eccentricity = eccentricity;
        validateDouble(inclination, 0, 180, false, false, "Угол наклона");
        this.inclination = inclination;
        validatePositive(size, "Размер");
        this.size = size;
        validatePositive(perihelion, "Перигелий");
        this.perihelion = perihelion;
        validatePositive(aphelion, "Афелий");
        this.aphelion = aphelion;
        validatePositive(orbitalPeriod, "Период обращения");
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getNumberName() {return numberName;}

    public double getEccentricity() {return eccentricity;}

    public double getInclination() {return inclination;}

    public double getSize() {return size;}

    public double getPerihelion() {return perihelion;}

    public double getAphelion() {return aphelion;}

    public double getOrbitalPeriod() {return orbitalPeriod;}

    public void setNumberName(String numberName) {this.numberName = numberName;}

    public void setEccentricity(double eccentricity) {
        validateDouble(eccentricity, 0, 1, true, false, "Эксцентриситет");
        this.eccentricity = eccentricity;
    }

    public void setInclination(double inclination) {
        validateDouble(inclination, 0, 180, false, false, "Угол наклона");
        this.inclination = inclination;
    }

    public void setSize(double size) {
        validatePositive(size, "Размер");
        this.size = size;
    }

    public void setPerihelion(double perihelion) {
        validatePositive(perihelion, "Перигелий");
        this.perihelion = perihelion;
    }

    public void setAphelion(double aphelion) {
        validatePositive(aphelion, "Афелий");
        this.aphelion = aphelion;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        validatePositive(orbitalPeriod, "Период обращения");
        this.orbitalPeriod = orbitalPeriod;
    }

    public static boolean validateDouble(double value, double min, double max, boolean inclusiveMin,
                                         boolean inclusiveMax, String paramName) {
        boolean isValid = true;

        if (inclusiveMin) {isValid = value >= min;}
        else {isValid = value > min;}
        if (isValid) {
            if (inclusiveMax) {isValid = value <= max;}
            else {isValid = value < max;}
        }

        if (!isValid) {
            String minBracket = inclusiveMin ? "[" : "(";
            String maxBracket = inclusiveMax ? "]" : ")";
            throw new IllegalArgumentException(
                    String.format("%s должен быть в диапазоне %s%.2f, %.2f%s.", paramName, minBracket,
                            min, max, maxBracket)
            );
        }

        return true;
    }

    public static boolean validatePositive(double value, String paramName) {
        return validateDouble(value, 0, Double.MAX_VALUE, false, true, paramName);
    }

    @Override
    public String toString() {
        return String.format("Comet: \"%s\", Eccentricity: %.4f, Inclination: %.2f°, Size: %.2f km, " +
                        "Perihelion: %.2f AU, Aphelion: %.2f AU, Period: %.2f years",
                numberName, eccentricity, inclination, size,
                perihelion, aphelion, orbitalPeriod);
    }
}
