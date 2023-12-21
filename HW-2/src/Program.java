import java.util.Random;
import java.util.Scanner;

/**
 * Программа для игры в крестики-нолики на поле размером 5x5.
 */
public class Program {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '*';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;

    private static final int WIN_COUNT = 4; // Выигрышная комбинация

    /**
     * Точка входа в программу.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkState(DOT_HUMAN, "Вы победили!")) {
                    break;
                }
                aiTurn();
                printField();
                if (checkState(DOT_AI, "Победил компьютер!")) {
                    break;
                }
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    /**
     * Инициализация объектов игры.
     */
    static void initialize() {
        fieldSizeX = 5;
        fieldSizeY = 5;

        field = new char[fieldSizeX][fieldSizeY];

        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля.
     */
    static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");

        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека).
     */
    static void humanTurn() {
        int x;
        int y;
        do {
            System.out.print("Введите координаты хода X и Y\n(от 1 до 5) через пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Ход игрока (компьютера).
     */
    static void aiTurn() {
        // Попробовать выиграть следующим ходом
        if (!blockPlayerMove(DOT_AI)) {
            // Если не получается, делаем случайный ход
            int x;
            int y;
            do {
                x = random.nextInt(fieldSizeX);
                y = random.nextInt(fieldSizeY);
            } while (!isCellEmpty(x, y));
            field[x][y] = DOT_AI;
        }
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой.
     *
     * @param x координата x.
     * @param y координата y.
     * @return true, если ячейка пуста, иначе false.
     */
    static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка валидности координат хода.
     *
     * @param x координата x.
     * @param y координата y.
     * @return true, если координаты валидны, иначе false.
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Проверка ничьи (все ячейки игрового поля заполнены).
     *
     * @return true, если ничья, иначе false.
     */
    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы в игре.
     *
     * @param dot фишка игрока.
     * @return true, если есть победитель, иначе false.
     */
    static boolean checkWin(char dot) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkDirection(i, j, 1, 0, dot) || // Горизонталь
                        checkDirection(i, j, 0, 1, dot) || // Вертикаль
                        checkDirection(i, j, 1, 1, dot) || // Диагональ \
                        checkDirection(i, j, 1, -1, dot)) { // Диагональ /
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверка состояния игры (победа, ничья или продолжение игры).
     *
     * @param dot фишка игрока.
     * @param s   победный слоган.
     * @return true, если игра завершена, иначе false.
     */
    static boolean checkState(char dot, String s) {
        if (checkWin(dot)) {
            System.out.println(s);
            return true;
        } else if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    /**
     * Проверка направления для выигрышной комбинации.
     *
     * @param x   координата x.
     * @param y   координата y.
     * @param dx  приращение по x.
     * @param dy  приращение по y.
     * @param dot фишка игрока.
     * @return true, если есть выигрышная комбинация, иначе false.
     */
    static boolean checkDirection(int x, int y, int dx, int dy, char dot) {
        int count = 0;
        for (int i = 0; i < WIN_COUNT; i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;
            if (newX >= 0 && newX < fieldSizeX && newY >= 0 && newY < fieldSizeY &&
                    field[newX][newY] == dot) {
                count++;
            }
        }
        return count == WIN_COUNT;
    }

    /**
     * Блокировка хода игрока компьютером.
     *
     * @param dot фишка компьютера.
     * @return true, если ход заблокирован, иначе false.
     */
    static boolean blockPlayerMove(char dot) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (isCellEmpty(i, j)) {
                    field[i][j] = DOT_HUMAN;
                    if (checkWin(DOT_HUMAN)) {
                        field[i][j] = DOT_AI;
                        return true;
                    }
                    field[i][j] = DOT_EMPTY;
                }
            }
        }
        return false;
    }
}