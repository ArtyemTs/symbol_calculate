//Задача запросить у пользователя файл, посчитать количество символов (таких-то столько, таких-то столько...).
//Результат вывести в другой файл.
//Для тестирования используется файл содержащий первый том величайшего произведения "Война и мир" Льва Толстого .
//Код содержит два варианта подсчета символов (более медленный закомментирован).

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) {
// Запрашиваем в консоле путь к файлу.
        // Для теста можно использовать файл "voyna-i-mir-tom-1.txt" скопировав в консоль путь src\voyna-i-mir-tom-1.txt

        Scanner pathReqest = new Scanner(System.in);
        System.out.println("Введите путь к файлу");
        String filePathIn = pathReqest.next();
// throws FileNotFoundException
        try {
            symbolCalculate(filePathIn);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    // Далее представлена реализация двух вариантов считывания и подсчета симфолов в файле.
    // В методы встроен замер времени выполнения каждого из вариантов.

    // ВАРИАНТ №1. Читаем из файла, заносим в HashMap, затем переносим в TreeMap, чтобы получить отсортированный вывод
    // подсчитанных символов.
    // Итог в среднем 80 миллисекунд (выбран этот вариант как более быстрый).
    public static void symbolCalculate(String filePathIn) throws IOException {
// Отсечка начала подсчета символов
//long startTime = System.currentTimeMillis();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePathIn));
            HashMap<Character, Integer> result = new HashMap<>();

// Читаем файл построчно, переводим в массив char, бежим по массиву и добавляем значение символа в ключ HashMap,
// а значение устанавливаем равное единице, если такой ключ есть, то увеличиваем значение по этому ключу на единицу
            String s;
            while ((s = br.readLine()) != null) {
                char[] cr = s.toCharArray();
                for (char c : cr) {
                    if (result.containsKey(c)) {
                        result.put(c, result.get(c) + 1);
                    } else result.put(c, 1);
                }
            }

// Делаем сортировку по ключу для удобства чтения выведенного результата
            Map<Character, Integer> treeMap = new TreeMap<Character, Integer>(result);

            FileWriter fw = new FileWriter(new File("Result.txt"));
            for (Map.Entry entry : treeMap.entrySet()) {
                String fr = "Символ: " + entry.getKey() + " Значение: " + entry.getValue() + "\n";
                fw.write(fr);

// Отсечка конца подсчета символов и вывод результата в консоль
//   long stopTime = System.currentTimeMillis();
//   long elapsedTime = stopTime - startTime;
//   System.out.println(elapsedTime);
            }

            fw.flush();
            System.out.println("Результаты подсчета символов находятся в файле Result.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти указанный файл");
        }
    }
}
// ВАРИАНТ №. Читаем из файла, заносим сразу в TreeMap чтобы получить отсортированный вывод подсчитанных символов.
// Итог в среднем 95 миллисекунд.
//    public static void symbolCalculate(String filePathIn) throws IOException {
//
//  long startTime = System.currentTimeMillis();
//
//        BufferedReader br = new BufferedReader(new FileReader(filePathIn));
//        TreeMap<Character, Integer> result = new TreeMap<>();
//
//        // Читаем файл построчно, переводим в массив char, бежим по массиву и добавляем значение символа в ключ TreeMap,
//        // а значение устанавливаем равное единице, если такой ключ есть, то увеличиваем значение по этому ключу на единицу
//        String s;
//        while ((s = br.readLine()) != null) {
//            char[] cr = s.toCharArray();
//            for (char c : cr) {
//                if (result.containsKey(c)) {
//                    result.put(c, result.get(c) + 1);
//                } else result.put(c, 1);
//            }
//        }
//
//        FileWriter fw = new FileWriter(new File("Result.txt"));
//        for (Map.Entry entry : result.entrySet()) {
//            String fr = "Символ: " + entry.getKey() + " Значение: " + entry.getValue() + "\n";
//            fw.write(fr);
//
//            long stopTime = System.currentTimeMillis();
//            long elapsedTime = stopTime - startTime;
//            System.out.println(elapsedTime);
//        }
//        fw.flush();
//        System.out.println("Результаты подсчета символов находятся в файле Result.txt");
//    }
//}

