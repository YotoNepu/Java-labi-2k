package Laba9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        System.out.println("--- Температура ---");
        List<Integer> temperatures = Arrays.asList(
                -2, -5, -2, -4, 3, -6, -2, -1, 5, 1,
                1, 0, -1, 0, 3, -1, 2, 5, 2, 4,
                4, 0, 6, 1, 4, 6, -1, 2, 4, 7, 11
        );

        long negativeDays = temperatures.stream()
                .filter(t -> t < 0)
                .count();
        System.out.println("1. Дней с отрицательной температурой: " + negativeDays);

        boolean hasAbove10 = temperatures.stream()
                .anyMatch(t -> t > 10);
        System.out.println("2. Были ли дни с температурой выше 10°C: " + hasAbove10);

        Optional<Integer> maxFirstWeek = temperatures.stream()
                .limit(7)
                .max(Integer::compareTo);
        maxFirstWeek.ifPresentOrElse(
                max -> System.out.println("3. Максимальная температура в первую неделю: " + max + "°C"),
                () -> System.out.println("3. В первую неделю не было данных о температуре")
        );
    }

    public static void task2() {
        System.out.println("\n--- Предложение ---");
        String sentence = "They used 233 features including 227 stylometric features and six novel social " +
                "network-specific features like character-based ones numbers of alphabets, " +
                "uppercase characters, special characters, word-based ones the total number of words, " +
                "average word length, the number of words with 1 char, syntactic ones numbers of " +
                "punctuation marks and functional words, the total number of sentences and many others";

        String[] words = sentence.split("[\\s,]+");
        List<String> wordList = Arrays.asList(words);

        System.out.println("1. Слова, заканчивающиеся на 'es':");
        List<String> wordsEndingWithEs = wordList.stream()
                .filter(w -> w.endsWith("es"))
                .toList();
        System.out.println("   Найдено слов: " + wordsEndingWithEs.size());

        System.out.println("2. Слова предложения, упорядоченные по возрастанию длины:");

        List<String> sortedByLength = wordList.stream()
                .distinct()
                .sorted(Comparator.comparingInt(String::length))
                .toList();

        System.out.println("   Слова по возрастанию длины: " + sortedByLength);
    }
}