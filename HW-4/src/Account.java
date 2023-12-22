import exceptions.InsufficientFundsException;

/**
 * Базовый класс для представления банковского счета.
 */
public class Account {
    private double balance;

    /**
     * Конструктор счета.
     * @param initialBalance Начальный баланс счета.
     * @throws IllegalArgumentException Если начальный баланс отрицателен.
     */
    public Account(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным.");
        }
        this.balance = initialBalance;
    }

    /**
     * Внесение депозита на счет.
     * @param amount Сумма депозита.
     * @throws IllegalArgumentException Если сумма депозита отрицательна.
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма депозита не может быть отрицательной.");
        }
        this.balance += amount;
        System.out.println("Депозит в размере " + amount + " успешно внесен. Новый баланс: " + this.balance);
    }

    /**
     * Снятие средств со счета.
     * @param amount Сумма для снятия.
     * @throws InsufficientFundsException Если сумма для снятия превышает текущий баланс.
     * @throws IllegalArgumentException Если сумма для снятия отрицательна.
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма снятия не может быть отрицательной.");
        }
        if (amount > this.balance) {
            throw new InsufficientFundsException("Недостаточно средств на счете. Текущий баланс: " + this.balance);
        }
        this.balance -= amount;
        System.out.println("Сумма " + amount + " успешно снята. Новый баланс: " + this.balance);
    }

    /**
     * Получение текущего баланса счета.
     * @return Текущий баланс.
     */
    public double getBalance() {
        return balance;
    }
}