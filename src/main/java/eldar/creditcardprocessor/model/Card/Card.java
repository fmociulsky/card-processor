package eldar.creditcardprocessor.model.Card;

import eldar.creditcardprocessor.Exceptions.CardProcessException;

import java.time.LocalDate;
import java.util.List;

import static eldar.creditcardprocessor.Exceptions.CardProcessException.CardMensajesErrorEnum.CARD_DATA_WRONG;
import static eldar.creditcardprocessor.Exceptions.CardProcessException.CardMensajesErrorEnum.EXPIRED_CARD;

public abstract class Card{

    private CardEnum marca;
    private Integer numero;
    private String cardHolder;
    private int mesVencimiento;
    private int anoVencimiento;

    protected Card(CardEnum marca, Integer numero, String cardHolder, int mesVencimiento, int anoVencimiento) {
        this.marca = marca;
        this.numero = numero;
        this.cardHolder = cardHolder;
        this.mesVencimiento = mesVencimiento;
        this.anoVencimiento = anoVencimiento;
    }

    public CardEnum getMarca() {
        return marca;
    }

    public void setMarca(CardEnum marca) {
        this.marca = marca;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public int getMesVencimiento() {
        return mesVencimiento;
    }

    public void setMesVencimiento(int mesVencimiento) {
        this.mesVencimiento = mesVencimiento;
    }

    public int getAnoVencimiento() {
        return anoVencimiento;
    }

    public void setAnoVencimiento(int anoVencimiento) {
        this.anoVencimiento = anoVencimiento;
    }

    public abstract double calcularTasa();

    public abstract String informarTasa(double monto);


    public void isValid(List<Card> tarjetas) throws CardProcessException {
        if(tarjetas.stream().noneMatch(c -> c.equals(this))){
            throw new CardProcessException(CARD_DATA_WRONG);
        }

        if(!checkVencimiento()){
            throw new CardProcessException(EXPIRED_CARD);
        }
    }

    public boolean checkVencimiento() {
        final LocalDate fechaVencimiento = LocalDate.now().withYear(getAnoVencimiento()).withMonth(getMesVencimiento());
        return !fechaVencimiento.isBefore(LocalDate.now());
    }

    public String getAllInfo() {
        return toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) {
            return false;
        }

        final Card card = (Card) obj;
        return card.getMarca() == getMarca()
                && card.getNumero().equals(getNumero())
                && card.getCardHolder().equals(getCardHolder())
                && card.getMesVencimiento() == getMesVencimiento()
                && card.getAnoVencimiento() == getAnoVencimiento();
    }

    @Override
    public String toString() {
        return String.format(TO_STRING, getMarca().getValue(), getNumero(), getCardHolder(), getMesVencimiento(), getAnoVencimiento());
    }

    static final double MIN = 0.3;
    static final double MAX = 5;
    public static final String TO_STRING = "Marca %s, Numero %d, Titular %s, Vencimiento %d/%d";
    public static final String MSJ_TASA = "El importe de tasa para la tarjeta de marca %s es de %,.2f";
}
