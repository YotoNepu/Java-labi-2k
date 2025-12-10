package Indiv1;

import java.time.LocalDate;
import java.util.List;

public class CometManager {
    public static void findCometsWithPerihelionDates(List<Comet> comets, List<CometDateInfo> cometDateInfos,
                                                     OutputCometData output) {
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
        LocalDate nextPerihelion;

        for (CometDateInfo dateInfo: cometDateInfos) {
            Comet foundComet = null;
            String searchNumber = dateInfo.getNumber();

            foundComet = findComet(comets, searchNumber, foundComet);

            if (foundComet != null) {
                foundCount++;
                nextPerihelion = countNextPerihelion(dateInfo, foundComet);

                output.printCometsWithPerihelionDates(foundComet, dateInfo, nextPerihelion);
            }
        }

        if (foundCount == 0) {System.out.println("Не найдено комет с известной датой прохождения перигелия.");}
        else {System.out.println("Найдено " + foundCount + " комет.");}
        System.out.println("------------------------------------");
    }

    private static Comet findComet(List<Comet> comets, String searchNumber, Comet foundComet) {
        for (Comet comet: comets) {
            if (comet.getNumberName().startsWith(searchNumber + "/") ||
                    comet.getNumberName().startsWith(searchNumber + " ")) {
                foundComet = comet;
                break;
            }
        }

        return foundComet;
    }

    private static LocalDate countNextPerihelion(CometDateInfo dateInfo, Comet foundComet) {
        LocalDate lastPerihelion = dateInfo.getPerihelionDate();
        double orbitalPeriodYears = foundComet.getOrbitalPeriod();
        long orbitalPeriodDays = Math.round(orbitalPeriodYears * 365.25); // Преобразую период из лет в дни (приблизительно)
        LocalDate nextPerihelion = lastPerihelion.plusDays(orbitalPeriodDays);

        return nextPerihelion;
    }
}
