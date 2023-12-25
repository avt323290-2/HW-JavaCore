import java.io.File;

/**
 * Класс для вывода дерева директорий и файлов.
 */
public class Tree {

    /**
     * Точка входа в программу. Выводит дерево директорий и файлов начиная с текущей директории.
     *
     * @param args аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        printTree(new File("."));
    }

    /**
     * Выводит дерево директорий и файлов для указанной директории.
     *
     * @param file директория, для которой выводится дерево.
     */
    static void printTree(File file) {
        print(file, "");
    }

    /**
     * Выводит дерево директорий и файлов для указанной директории с использованием отступов.
     *
     * @param file   директория, для которой выводится дерево.
     * @param indent отступ для текущего уровня вложенности.
     */
    static void print(File file, String indent) {
        print(file, indent, true);
    }

    /**
     * Выводит дерево директорий и файлов для указанной директории с использованием отступов и
     * указывает, является ли текущий элемент последним на данном уровне вложенности.
     *
     * @param file   директория, для которой выводится дерево.
     * @param indent отступ для текущего уровня вложенности.
     * @param isLast является ли текущий элемент последним на данном уровне вложенности.
     */
    static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirTotal++;
            }
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                print(files[i], indent, subDirTotal == ++subDirCounter);
            }
        }
    }
}