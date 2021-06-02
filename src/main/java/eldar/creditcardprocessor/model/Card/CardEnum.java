package eldar.creditcardprocessor.model.Card;

public enum CardEnum {

        VISA("VISA"),
        NARA("NARA"),
        AMEX("AMEX");

        private final String value;

        CardEnum(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
}
