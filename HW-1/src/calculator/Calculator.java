package calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int num1 = scanner.nextInt();

        System.out.print("Введите второе число: ");
        int num2 = scanner.nextInt();

        System.out.println("Сложение: " + MathOperations.add(num1, num2));
        System.out.println("Вычитание: " + MathOperations.subtract(num1, num2));
        System.out.println("Умножение: " + MathOperations.multiply(num1, num2));

        try {
            System.out.println("Деление: " + MathOperations.divide(num1, num2));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }
}