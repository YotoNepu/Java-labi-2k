package Laba2;

import java.util.Random;

public class Task5 {
    public static void main(String[] args) {
        int[][] intMatrix = createMatrix();
        System.out.println("Матрица 8x8:");
        printMatrix(intMatrix);

        findLocalMinimums(intMatrix);
    }

    public static int[][] createMatrix() {
        Random random = new Random();
        int[][] matrix = new int[8][8];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(21) - 10;
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] intRow: matrix) {
            for (int intCol: intRow) {
                System.out.printf("%3d ", intCol);
            }
            System.out.println();
        }
    }

    public static void findLocalMinimums(int[][] matrix) {
        int count = 0;

        System.out.println("\nЛокальные минимумы:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isLocalMinimum(matrix, i, j)) {
                    count++;
                    System.out.printf("[%d][%d] = %d%n", i, j, matrix[i][j]);
                }
            }
        }
        System.out.printf("Количество локальных минимумов: %d%n", count);
    }

    public static boolean isLocalMinimum(int[][] matrix, int row, int col) {
        int current = matrix[row][col];

        if (row > 0 && current >= matrix[row - 1][col]) {
            return false;
        }

        if (row < matrix.length - 1 && current >= matrix[row + 1][col]) {
            return false;
        }

        if (col > 0 && current >= matrix[row][col - 1]) {
            return false;
        }

        if (col < matrix[row].length - 1 && current >= matrix[row][col + 1]) {
            return false;
        }

        return true;
    }
}