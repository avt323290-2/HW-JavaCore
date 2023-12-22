package exceptions;

/**
 * Исключение для обработки ситуации недостаточных средств на счете.
 */
public class InsufficientFundsException extends Exception {
    /**
     * Конструктор с сообщением об ошибке.
     * @param message Сообщение об ошибке.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}