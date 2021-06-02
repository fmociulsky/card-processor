package eldar.creditcardprocessor.model.Card;

import eldar.creditcardprocessor.model.Tasa;

import java.time.LocalDate;

public class VisaCard extends Card {

    public VisaCard(int numero, String cardHolder, int mesVencimiento, int anoVencimiento) {
        super(CardEnum.VISA, numero, cardHolder, mesVencimiento, anoVencimiento);
    }

    @Override
    public double calcularTasa() {
        final LocalDate now = LocalDate.now();
        final double tasa = (double) (now.getYear() % 100) / (double) now.getMonth().getValue();
        if(tasa < MIN) return MIN;
        if(tasa > MAX) return MAX;

        return tasa;
    }
}
