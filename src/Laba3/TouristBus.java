package Laba3;

class TouristBus {

    private int totalSeats;
    private double seatPrice;
    private int occupiedSeats;

    public TouristBus() {
        this.totalSeats = 50;
        this.seatPrice = 300;
        this.occupiedSeats = 0;
    }

    public TouristBus(int totalSeats, double seatPrice) {
        setTotalSeats(totalSeats);
        setSeatPrice(seatPrice);
        this.occupiedSeats = 0;  // изначально автобус пустой
    }

    public TouristBus(TouristBus other) {
        this.totalSeats = other.totalSeats;
        this.seatPrice = other.seatPrice;
        this.occupiedSeats = other.occupiedSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        if (totalSeats > 0) {
            this.totalSeats = totalSeats;
        } else {
            throw new IllegalArgumentException("Ошибка. Количество мест должно быть положительным.");
        }
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        if (seatPrice >= 0) {
            this.seatPrice = seatPrice;
        } else {
            throw new IllegalArgumentException("Ошибка. Стоимость места не может быть отрицательной.");
        }
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(int occupiedSeats) {
        if (occupiedSeats < 0) {
            throw new IllegalArgumentException("Ошибка. Количество занятых мест не может быть отрицательным.");
        }
        if (occupiedSeats > totalSeats) {
            throw new IllegalArgumentException("Ошибка. Недостаточно мест в автобусе.");
        }
        this.occupiedSeats = occupiedSeats;
    }

    public int calculateFreeSeats() {
        return totalSeats - occupiedSeats;
    }

    public boolean isFull() {
        return occupiedSeats == totalSeats;
    }

    public double calculateTotalRevenue() {
        return occupiedSeats * seatPrice;
    }

    public Profit checkProfitability(double minRevenue) {
        double totalRevenue = calculateTotalRevenue();
        if (totalRevenue > minRevenue) {
            return Profit.PROFITABLE;
        } else {
            return Profit.UNPROFITABLE;
        }
    }

    @Override
    public String toString() {
        return String.format("Автобус: мест=%d, цена места=%.2f руб., занято=%d, свободно=%d, " +
                        "выручка=%.2f руб., " + "статус: %s",
                totalSeats, seatPrice, occupiedSeats, calculateFreeSeats(), calculateTotalRevenue(),
                (isFull() ? "заполнен" : "не заполнен"));
    }
}