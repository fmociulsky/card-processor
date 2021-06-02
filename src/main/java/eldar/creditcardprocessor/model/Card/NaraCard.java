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
    public void informarTasa(double monto) {
        final double importe = monto * calcularTasa() / 100;
        System.out.println(String.format("El importe de tasa para la tarjeta de marca %s es de %,.2f", getMarca().getValue(), importe));
    }

    static final double COEFICIENTE = 0.5;
}
