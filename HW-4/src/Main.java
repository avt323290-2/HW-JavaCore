import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Создаем дебетовый счет
            System.out.print("Введите начальный баланс для дебетового счета: ");
            double initialBalanceDebit = scanner.nextDouble();
            DebitAccount debitAccount = new DebitAccount(initialBalanceDebit);

            // Создаем кредитный счет
            System.out.print("Введите начальный баланс для кредитного счета: ");
            double initialBalanceCredit = scanner.nextDouble();
            CreditAccount creditAccount = new CreditAccount(initialBalanceCredit);

            System.out.println("Начальные балансы:");
            System.out.println("Дебетовый счет: " + debitAccount.getBalance());
            System.out.println("Кредитный счет: " + creditAccount.getBalance());

            // Внесение денег на счет
            boolean success = false;
            while (!success) {
                System.out.print("Выберите счет для внесения денег (1 - Дебетовый, 2 - Кредитный): ");
                int selectedAccount = scanner.nextInt();

                if (selectedAccount == 1) {
                    System.out.print("Введите сумму для внесения на дебетовый счет: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        debitAccount.deposit(depositAmount);
                        success = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                } else if (selectedAccount == 2) {
                    System.out.print("Введите сумму для внесения на кредитный счет: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        creditAccount.deposit(depositAmount);
                        success = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                } else {
                    System.out.println("Выбран некорректный счет.");
                }
            }

            System.out.println("Итоговые балансы:");
            System.out.println("Дебетовый счет: " + debitAccount.getBalance());
            System.out.println("Кредитный счет: " + creditAccount.getBalance());

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}