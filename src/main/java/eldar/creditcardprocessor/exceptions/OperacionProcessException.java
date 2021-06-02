package eldar.creditcardprocessor.exceptions;

public class OperacionProcessException extends Exception {

    private static final long serialVersionUID = -4600911157190210083L;

    public OperacionProcessException(OperacionProcessErrorEnum message) {
        super(message.getValue());
    }

    public enum OperacionProcessErrorEnum {

        AMOUNT_TOO_HIGH("El monto maximo para una operacion es de 1000");

        private final String value;

        OperacionProcessErrorEnum(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
