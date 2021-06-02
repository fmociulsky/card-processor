package eldar.creditcardprocessor.Exceptions;

public class CardProcessException extends Exception {

    private static final long serialVersionUID = -3591727589789733136L;

    public CardProcessException(CardMensajesErrorEnum message) {
        super(message.getValue());
    }

    public enum CardMensajesErrorEnum {

        CARD_DATA_WRONG("Los datos de la tarjeta utilizada son incorrectos"),
        EXPIRED_CARD("Tarjeta vencida");

        private final String value;

        CardMensajesErrorEnum(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
