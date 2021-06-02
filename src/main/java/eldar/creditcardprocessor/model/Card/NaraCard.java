package eldar.creditcardprocessor.model.Card;

import eldar.creditcardprocessor.model.Tasa;

import java.time.LocalDate;

public class NaraCard extends Card{
    public NaraCard(int numero, String cardHolder, int mesVencimiento, int anoVencimiento) {
        super(CardEnum.NARA, numero, cardHolder, mesVencimiento, anoVencimiento);
    }

    @Override
    public double calcularTasa() {
        final double tasa =  LocalDate.now().getDayOfMonth() * COEFICIENTE;
        if(tasa < MIN) return MIN;
        if(tasa > MAX) return MAX;
        return tasa;
    }

    static final double COEFICIENTE = 0.5;
}
