import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Класс для управления созданием резервных копий файлов в директории.
 */
public class BackupManager {

    /**
     * Точка входа в программу. Создает резервную копию файлов и выводит дерево директорий.
     *
     * @param args аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        createBackup("./backup");
        Tree.printTree(new File("."));
    }

    /**
     * Создает резервную копию всех файлов в указанной директории.
     *
     * @param backupDirectoryPath путь к директории, в которой создается резервная копия.
     */
    static void createBackup(String backupDirectoryPath) {
        File sourceDirectory = new File(".");
        File backupDirectory = new File(sourceDirectory, backupDirectoryPath);

        // Создание директории, если её нет
        if (!backupDirectory.exists()) {
            if (backupDirectory.mkdir()) {
                System.out.println("Backup directory created: " + backupDirectory.getAbsolutePath());
            } else {
                System.err.println("Failed to create backup directory");
                return;
            }
        }

        // Копирование файлов
        File[] filesToBackup = sourceDirectory.listFiles();
        if (filesToBackup != null) {
            for (File file : filesToBackup) {
                if (file.isFile()) {
                    copyFile(file, new File(backupDirectory, file.getName()));
                    System.out.println("Backup created for: " + file.getName());
                }
            }
        }
    }

    /**
     * Копирует файл из одного места в другое.
     *
     * @param sourceFile      исходный файл.
     * @param destinationFile файл, в который производится копирование.
     */
    static void copyFile(File sourceFile, File destinationFile) {
        try {
            Path source = sourceFile.toPath();
            Path destination = destinationFile.toPath();
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}