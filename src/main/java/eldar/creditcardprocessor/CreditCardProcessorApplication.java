package eldar.creditcardprocessor;

import eldar.creditcardprocessor.model.Card.Card;
import eldar.creditcardprocessor.model.Card.CardEnum;
import eldar.creditcardprocessor.model.Card.VisaCard;
import eldar.creditcardprocessor.model.CardProcessor;
import eldar.creditcardprocessor.model.Operacion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Month;

import static java.time.Month.FEBRUARY;

@SpringBootApplication
public class CreditCardProcessorApplication {

	public static void main(String[] args) {

		final CardProcessor cardProcessor = new CardProcessor();
		cardProcessor.iniciarTarjetas();
		final Card card1 = cardProcessor.getTarjetas().get(0);
		card1.getAllInfo();
		cardProcessor.procesarOperacion(new Operacion(card1, 500d));
		final Card card2 = cardProcessor.getTarjetas().get(1);
		cardProcessor.procesarOperacion(new Operacion(card2, 1500d));
		final Card card3 = cardProcessor.getTarjetas().get(2);
		cardProcessor.procesarOperacion(new Operacion(card3, 200d));
		final Card card4 = new VisaCard(98765, "Gabriel Batistuta", FEBRUARY.getValue(), 2030);
		cardProcessor.procesarOperacion(new Operacion(card4, 200d));

		card1.informarTasa(1000d);
		card2.informarTasa(1000d);
		card3.informarTasa(1000d);

		SpringApplication.run(CreditCardProcessorApplication.class, args);
	}

}