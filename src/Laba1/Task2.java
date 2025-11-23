package Laba1;

public class Task2 {
    public static final int MAX_N = 9;
    public static final int MAX_LENGTH = String.valueOf(MAX_N * MAX_N).length();

    public static void main(String[] args) {
        text_output();
        System.out.println();
        table_output();
    }

    public static void text_output() {
        for (int first_n = 1; first_n <= MAX_N; first_n++) {
            for (int second_n = 1; second_n <= MAX_N; second_n++) {
                System.out.printf("%" + MAX_LENGTH + "d × %" + MAX_LENGTH + "d = %" + MAX_LENGTH + "d    ",
                        first_n, second_n, first_n * second_n);
            }
            System.out.println();
        }
    }

    public static void table_output() {
        for (int n = 0; n <= MAX_N; n++)
            System.out.printf("%" + MAX_LENGTH + "d ", n);
        System.out.println();
        for (int first_n = 1; first_n <= MAX_N; first_n++) {
            System.out.printf("%" + MAX_LENGTH + "d ", first_n);
            for (int second_n = 1; second_n <= MAX_N; second_n++) {
                System.out.printf("%" + MAX_LENGTH + "d ", first_n * second_n);
            }
            System.out.println();
        }
    }
}
