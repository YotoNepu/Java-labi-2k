package Indiv1;

import java.time.LocalDate;
import java.util.List;

public class OutputCometData {
    public static void printAllComets(List<Comet> comets) {
        System.out.println("\n------------------------------------");
        System.out.println("Список всех комет:");
        for (Comet comet: comets) {
            System.out.println(comet);
        }
        System.out.println("------------------------------------");
    }

    public static void printAllCometDates(List<CometDateInfo> cometDateInfos) {
        System.out.println("\n------------------------------------");
        System.out.println("Список всех дат прохождения перигелия:");
        for (CometDateInfo dateInfo: cometDateInfos) {
            System.out.println(dateInfo);
        }
        System.out.println("------------------------------------");
    }

    public static void printCometsWithPerihelionDates(Comet foundComet, CometDateInfo dateInfo, LocalDate nextPerihelion) {
        System.out.println("\n---Комета---");
        System.out.println("Название: " + foundComet.getNumberName());
        System.out.println("Русское название: " + dateInfo.getRussianName());
        System.out.println("Примерная дата следующего прохождения: " + nextPerihelion);
    }
}
