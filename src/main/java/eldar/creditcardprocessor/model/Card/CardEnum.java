package eldar.creditcardprocessor.model.Card;

import java.io.Serializable;

public enum CardEnum implements Serializable {

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
