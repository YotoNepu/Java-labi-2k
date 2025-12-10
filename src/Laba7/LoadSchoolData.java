package Laba7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadSchoolData {
    public SchoolJournal loadDataFromFile(String filepath, String filename) throws IOException {
        SchoolJournal journal = new SchoolJournal();
        BufferedReader reader = new BufferedReader(new FileReader(filepath + filename));
        String line;

        System.out.println("Загрузка данных из файла...");

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");
            if (parts.length != 5) {
                System.out.println("Ошибка. Неверное кол-во данных.");
                continue;
            }

            try {
                String lastName = parts[0];
                String firstName = parts[1];
                int grade = Integer.parseInt(parts[2]);
                String subject = parts[3];
                int mark = Integer.parseInt(parts[4]);

                if (grade < 1 || grade > 11) {
                    System.out.println("Ошибка. Некорректный класс в строке: " + line);
                    continue;
                }

                if (mark < 2 || mark > 5) {
                    System.out.println("Ошибка. Некорректная оценка в строке: " + line);
                    continue;
                }

                Student student = new Student(lastName, firstName, grade, subject, mark);

                journal.addStudent(student);

                if (!journal.getClasses().containsKey(grade)) {
                    journal.putClass(grade, new ClassGrade(grade));
                }
                journal.getClasses().get(grade).addStudent(student);

            } catch (NumberFormatException e) {
                System.out.println("Ошибка преобразования числа в строке.");
            }
        }

        reader.close();

        return journal;
    }
}
