import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Программа с различными методами для работы с текстовыми файлами.
 */
public class Program {

    private static final Random random = new Random();

    private static final int CHAR_BOUND_L = 65;
    private static final int CHAR_BOUND_H = 90;

    private static final String TO_SEARCH = "GeekBrains";

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        System.out.println(generateSymbols(23));

        writeFileContents("sample01.txt", 30, TO_SEARCH);
        writeFileContents("sample02.txt", 50, TO_SEARCH);

        concatenate("sample01.txt", "sample02.txt", "sampleOut.txt");

        long i = 0;

        while ((i = searchInFile("sampleOut.txt", i, TO_SEARCH)) > 0) {
            System.out.printf("Файл содержит искомое слово, смещение: %d\n", i);
        }
        System.out.println("Завершение поиска");

        String[] fileNames = new String[10];
        for (int j = 0; j < fileNames.length; j++) {
            fileNames[j] = "file_" + j + ".txt";
            writeFileContents(fileNames[j], 30, TO_SEARCH);
            System.out.printf("Файл %s создан.\n", fileNames[j]);
        }

        List<String> result = searchMatch(new File("."), TO_SEARCH);
        for (String s : result) {
            System.out.printf("Файл %s содержит искомое слово '%s'\n", s, TO_SEARCH);
        }
    }

    /**
     * Генерирует последовательность случайных символов.
     *
     * @param length количество символов.
     * @return последовательность символов.
     */
    static String generateSymbols(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) random.nextInt(CHAR_BOUND_L, CHAR_BOUND_H + 1));
        }
        return stringBuilder.toString();
    }

    /**
     * Записывает в файл случайную последовательность символов.
     *
     * @param fileName  имя файла.
     * @param length    длина последовательности символов.
     */
    static void writeFileContents(String fileName, int length) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(generateSymbols(length).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Записывает в файл случайную последовательность символов, а также указанное слово.
     *
     * @param fileName   имя файла.
     * @param length     длина последовательности символов.
     * @param searchWord слово для записи.
     */
    static void writeFileContents(String fileName, int length, String searchWord) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(generateSymbols(length).getBytes());
            if (random.nextInt(3) == 0) {
                fileOutputStream.write(searchWord.getBytes());
                fileOutputStream.write(generateSymbols(random.nextInt(15)).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Склеивает содержимое двух файлов в третий файл.
     *
     * @param fileIn1  первый входной файл.
     * @param fileIn2  второй входной файл.
     * @param fileOut  выходной файл (результат склейки).
     */
    static void concatenate(String fileIn1, String fileIn2, String fileOut) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)) {
            int c;
            try (FileInputStream fileInputStream = new FileInputStream(fileIn1)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
            try (FileInputStream fileInputStream = new FileInputStream(fileIn2)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Поиск указанного слова в файле.
     *
     * @param fileName   имя файла.
     * @param searchWord слово для поиска.
     * @return смещение, если слово найдено; -1, если не найдено.
     */
    static long searchInFile(String fileName, String searchWord) {
        return searchInFile(fileName, 0, searchWord);
    }

    /**
     * Поиск указанного слова в файле с заданным смещением.
     *
     * @param fileName   имя файла.
     * @param offset     начальное смещение для поиска.
     * @param searchWord слово для поиска.
     * @return смещение, если слово найдено; -1, если не найдено.
     */
    static long searchInFile(String fileName, long offset, String searchWord) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            fileInputStream.skipNBytes(offset);
            byte[] searchData = searchWord.getBytes();
            int c;
            int i = 0;
            while ((c = fileInputStream.read()) != -1) {
                if (c == searchData[i]) {
                    i++;
                } else {
                    i = 0;
                    if (c == searchData[i])
                        i++;
                }
                if (i == searchData.length) {
                    return offset;
                }
                offset++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Поиск указанного слова в файлах внутри директории и её поддиректорий.
     *
     * @param file       директория для поиска.
     * @param searchWord слово для поиска.
     * @return список файлов, содержащих указанное слово.
     */
    static List<String> searchMatch(File file, String searchWord) {
        List<String> list = new ArrayList<>();
        try {
            File[] files = file.listFiles();
            if (files == null)
                return list;
            for (File file1 : files) {
                if (file1.isFile()) {
                    if (searchInFile(file1.getCanonicalPath(), searchWord) > -1) {
                        list.add(file1.getCanonicalPath());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}