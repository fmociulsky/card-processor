package eldar.creditcardprocessor.Exceptions;

public class OperacionProcessException extends Exception {

    private static final long serialVersionUID = -3591727589789733136L;

    public OperacionProcessException(CardMensajesErrorEnum message) {
        super(message.getValue());
    }

    public enum CardMensajesErrorEnum {

        AMOUNT_TOO_HIGH("El monto maximo para una operacion es de 1000");

        private final String value;

        CardMensajesErrorEnum(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
