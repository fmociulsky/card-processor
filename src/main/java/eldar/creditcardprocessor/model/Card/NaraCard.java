package eldar.creditcardprocessor.model.Card;

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

    @Override
    public String informarTasa(double monto) {
        final double importe = monto * calcularTasa() / 100;
        return String.format(MSJ_TASA, getMarca().getValue(), importe);
    }

    static final double COEFICIENTE = 0.5;
}
