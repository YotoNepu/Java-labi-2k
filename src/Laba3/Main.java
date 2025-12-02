package Laba3;

public class Main {
    public static void main(String[] args) {
        TouristBus bus1 = new TouristBus(54, 400);
        TouristBus bus2 = new TouristBus(45, 500);

        System.out.println("Автобусы для туристов:");
        System.out.println(bus1);
        System.out.println(bus2);
        System.out.println();

        int group1 = 25;
        int group2 = 30;

        System.out.println("Размещаем туристов:");
        System.out.println("Группа 1: " + group1 + " человек");
        System.out.println("Группа 2: " + group2 + " человек");
        System.out.println();

        bus1.setOccupiedSeats(group1);
        bus2.setOccupiedSeats(group2);

        System.out.println("После размещения туристов:");
        System.out.println(bus1);
        System.out.println(bus2);
        System.out.println();

        System.out.println("Определяем рентабельность:");
        analyzeProfit(bus1, 1, 11000);
        analyzeProfit(bus2, 2, 11000);
    }

    public static void analyzeProfit(TouristBus bus, int busNumber, double minProfit) {
        double totalRevenue = bus.calculateTotalRevenue();
        Profit result = bus.checkProfitability(minProfit);

        System.out.printf("Автобус %d: Общая выручка = %.2f руб.%n", busNumber, totalRevenue);
        System.out.printf("           Минимальная прибыль = %.2f руб.%n", minProfit);
        System.out.printf("           Статус: %s", result.getDescription());
        System.out.println();
    }
}