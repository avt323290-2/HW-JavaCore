package exceptions;

/**
 * Исключение для обработки ситуации недостаточных средств на счете.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}