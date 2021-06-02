package eldar.creditcardprocessor;

import eldar.creditcardprocessor.exceptions.CardProcessException;
import eldar.creditcardprocessor.exceptions.OperacionProcessException;
import eldar.creditcardprocessor.model.Card.AmexCard;
import eldar.creditcardprocessor.model.Card.Card;
import eldar.creditcardprocessor.model.Card.CardEnum;
import eldar.creditcardprocessor.model.Card.NaraCard;
import eldar.creditcardprocessor.model.Card.VisaCard;
import eldar.creditcardprocessor.model.Operacion.Operacion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CardProcessor {

    List<Card> tarjetas = new ArrayList<>();

    public List<Card> getTarjetas() {
        return tarjetas;
    }

    public void addCard(CardEnum marca, int numero, String cardHolder, int mesVencimiento, int anoVencimiento) {
        switch (marca) {
            case VISA:
                tarjetas.add(new VisaCard(numero, cardHolder, mesVencimiento, anoVencimiento));
                break;
            case NARA:
                tarjetas.add(new NaraCard(numero, cardHolder, mesVencimiento, anoVencimiento));
                break;
            case AMEX:
                tarjetas.add(new AmexCard(numero, cardHolder, mesVencimiento, anoVencimiento));
                break;
        }
    }

    public boolean procesarOperacion(Operacion operacion){

        try {
            operacion.validarOperacion();
            operacion.getTarjeta().isValid(getTarjetas());
            System.out.println("Transaccion procesada");
            return true;
        } catch (final CardProcessException | OperacionProcessException error) {
            System.out.println(error.getMessage());
            return false;
        }
    }

    public void iniciarTarjetas() {
        addCard(CardEnum.VISA, 123456, "Lionel Messi", Month.AUGUST.getValue(), 2025);
        addCard(CardEnum.NARA, 654321, "Cristiano Ronaldo", Month.JANUARY.getValue(), 2022);

        //Esta tarjeta esta vencida
        addCard(CardEnum.AMEX, 456789, "Kylian Mbappe", Month.MARCH.getValue(), 2020);
    }
}
