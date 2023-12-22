/**
 * Класс для представления дебетового счета.
 */
public class DebitAccount extends Account {
    /**
     * Конструктор дебетового счета.
     * @param initialBalance Начальный баланс дебетового счета.
     */
    public DebitAccount(double initialBalance) {
        super(initialBalance);
    }
}