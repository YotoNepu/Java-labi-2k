package Indiv1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CometManager {
    private List<Comet> comets = new ArrayList<>();
    private List<CometDateInfo> cometDateInfos = new ArrayList<>();

    public static void main(String[] args) {
        CometManager manager = new CometManager();

        manager.getCometDataFromFile("src/Indiv1/data_comets.csv");
        manager.getCometDataFromFile("src/Indiv1/data_comets_date.txt");

        manager.printAllComets();
        manager.printAllCometDates();

        manager.findCometsWithPerihelionDates();
    }

    public void getCometDataFromFile(String filepath) {
        int lineCount = 0;
        int errorCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            if (filepath.endsWith(".csv")) {
                System.out.println("\nПолучение данных о кометах...");
                errorCount = readCometsInfos(br, errorCount, lineCount);
            }
            else if (filepath.endsWith(".txt")) {
                System.out.println("\nПолучение данных о дате прохождения перигелия...");
                errorCount = readCometDates(br, errorCount, lineCount);
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

    public int readCometsInfos(BufferedReader br, int errorCount, int lineCount) throws IOException {
        String line;

        while ((line = br.readLine()) != null) {
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

    public int readCometDates(BufferedReader br, int errorCount, int lineCount) throws IOException {
        String line;

        while ((line = br.readLine()) != null) {
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

    public void findCometsWithPerihelionDates() {
        System.out.println("\n------------------------------------");
        System.out.println("Поиск комет с известной датой прохождения перигелия:");

        if (comets.isEmpty()) {
            System.out.println("Нет данных о кометах. Проверьте файл data_comets.csv");
            return;
        }
        if (cometDateInfos.isEmpty()) {
            System.out.println("Нет данных о датах прохождения перигелия. Проверьте файл data_comets_date.txt");
            return;
        }

        int foundCount = 0;

        for (CometDateInfo dateInfo : cometDateInfos) {
            Comet foundComet = null;
            String searchNumber = dateInfo.getNumber();

            for (Comet comet: comets) {
                if (comet.getNumberName().startsWith(searchNumber + "/") ||
                        comet.getNumberName().startsWith(searchNumber + " ")) {
                    foundComet = comet;
                    break;
                }
            }

            if (foundComet != null) {
                foundCount++;
                LocalDate lastPerihelion = dateInfo.getPerihelionDate();
                double orbitalPeriodYears = foundComet.getOrbitalPeriod();

                long orbitalPeriodDays = Math.round(orbitalPeriodYears * 365.25); // Преобразую период из лет в дни (приблизительно)
                LocalDate nextPerihelion = lastPerihelion.plusDays(orbitalPeriodDays);

                System.out.println("\n---Комета---");
                System.out.println("Название: " + foundComet.getNumberName());
                System.out.println("Русское название: " + dateInfo.getRussianName());
                System.out.println("Примерная дата следующего прохождения: " + nextPerihelion);
            }
        }

        if (foundCount == 0) {
            System.out.println("Не найдено комет с известной датой прохождения перигелия.");
        }
        System.out.println("------------------------------------");
    }

    public void checkLength(int length, int n) {
        if (length != n) {
            throw new IllegalArgumentException("Неверное кол-во данных.");
        }
    }

    public void printAllComets() {
        System.out.println("\n------------------------------------");
        System.out.println("Список всех комет:");
        for (Comet comet: comets) {
            System.out.println(comet);
        }
        System.out.println("------------------------------------");
    }

    public void printAllCometDates() {
        System.out.println("\n------------------------------------");
        System.out.println("Список всех дат прохождения перигелия:");
        for (CometDateInfo dateInfo : cometDateInfos) {
            System.out.println(dateInfo);
        }
        System.out.println("------------------------------------");
    }
}
