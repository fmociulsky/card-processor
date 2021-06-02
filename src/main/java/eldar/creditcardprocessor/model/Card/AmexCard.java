package eldar.creditcardprocessor.model.Card;

import java.time.LocalDate;

public class AmexCard extends Card{
    public AmexCard(int numero, String cardHolder, int mesVencimiento, int anoVencimiento) {
        super(CardEnum.AMEX, numero, cardHolder, mesVencimiento, anoVencimiento);
    }

    @Override
    public double calcularTasa() {

        final double tasa =  LocalDate.now().getMonth().getValue() * COEFICIENTE;
        if(tasa < MIN) return MIN;
        if(tasa > MAX) return MAX;
        return tasa;
    }

    static final double COEFICIENTE = 0.1;
}