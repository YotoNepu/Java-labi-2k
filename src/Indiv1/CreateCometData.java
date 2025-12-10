package Indiv1;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateCometData {
    private List<Comet> comets = new ArrayList<>();
    private List<CometDateInfo> cometDateInfos = new ArrayList<>();

    public List<Comet> getComets() {return comets;}
    public List<CometDateInfo> getCometDateInfos() {return cometDateInfos;}

    public void getCometDataFromFile(String filepath) {
        int lineCount = 0;
        int errorCount = 0;

        try (Scanner sc = new Scanner(new FileReader(filepath))) {
            if (filepath.endsWith(".csv")) {
                System.out.println("\nПолучение данных о кометах...");
                errorCount = readCometsInfos(sc, errorCount, lineCount);
            }
            else if (filepath.endsWith(".txt")) {
                System.out.println("\nПолучение данных о дате прохождения перигелия...");
                errorCount = readCometDates(sc, errorCount, lineCount);
            }
            else {
                throw new IllegalArgumentException("Ошибка. указан неверный файл.");
            }

            System.out.println("Получено некорректных данных: " + errorCount);

        }
        catch (IOException e) {
            System.out.println("Ошибка. Не удается прочитать файл: " + e.getMessage());
            System.out.println("Завершение работы программы.");
            System.exit(1);
        }
    }

    public int readCometsInfos(Scanner sc, int errorCount, int lineCount) throws IOException {
        String line;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            lineCount++;

            try {
                String[] parts = parseCSVLine(line);

                checkLength(parts.length, 7);

                String numberName = parts[0].trim();
                double eccentricity = Double.parseDouble(parts[1].trim());
                double inclination = Double.parseDouble(parts[2].trim());
                double size = Double.parseDouble(parts[3].trim());
                double perihelion = Double.parseDouble(parts[4].trim());
                double aphelion = Double.parseDouble(parts[5].trim());
                double orbitalPeriod = Double.parseDouble(parts[6].trim());

                if (aphelion <= perihelion) {
                    throw new IllegalArgumentException("Афелий должен быть больше перигелия");
                }

                Comet comet = new Comet(numberName, eccentricity, inclination,
                        size, perihelion, aphelion, orbitalPeriod);
                comets.add(comet);

            } catch (Exception e) {
                errorCount++;
                System.out.println("Ошибка. Неверные данные: " + e.getMessage());
            }
        }

        return errorCount;
    }

    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }

        result.add(current.toString());
        return result.toArray(new String[0]);
    }

    public int readCometDates(Scanner sc, int errorCount, int lineCount) throws IOException {
        String line;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            lineCount++;

            try {
                String[] parts = line.trim().split("\\s+");

                checkLength(parts.length, 3);

                String number = parts[0].trim();
                String russianName = parts[1].trim();
                LocalDate perihelionDate = LocalDate.parse(parts[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                CometDateInfo dateInfo = new CometDateInfo(number, russianName, perihelionDate);
                cometDateInfos.add(dateInfo);

            } catch (DateTimeParseException e) {
                errorCount++;
                System.out.println("Ошибка. Неверный формат даты: " + e.getMessage());
            } catch (Exception e) {
                errorCount++;
                System.out.println("Ошибка. Неверные данные: " + e.getMessage());
            }
        }

        return errorCount;
    }

    public void checkLength(int length, int n) {
        if (length != n) {
            throw new IllegalArgumentException("Неверное кол-во данных.");
        }
    }
}
