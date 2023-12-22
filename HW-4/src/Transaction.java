import exceptions.InsufficientFundsException;

/**
 * Класс для проведения транзакций между счетами.
 */
public class Transaction {
    /**
     * Перевод средств с одного счета на другой.
     * @param fromAccount Счет, с которого снимают средства.
     * @param toAccount Счет, на который вносят средства.
     * @param amount Сумма перевода.
     */
    public static void transfer(Account fromAccount, Account toAccount, double amount) throws IllegalArgumentException, InsufficientFundsException {
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Транзакция выполнена успешно.");
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка транзакции: " + e.getMessage());
        }
    }
}