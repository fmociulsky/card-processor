package eldar.creditcardprocessor;

import eldar.creditcardprocessor.exceptions.CardProcessException;
import eldar.creditcardprocessor.model.Card.Card;
import eldar.creditcardprocessor.model.Card.VisaCard;
import eldar.creditcardprocessor.model.Operacion.Operacion;
import eldar.creditcardprocessor.model.Tasa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static eldar.creditcardprocessor.model.Card.Card.MSJ_TASA;
import static java.time.Month.FEBRUARY;

@SpringBootApplication
public class CreditCardProcessorApplication {

	public static void main(String[] args) {

		SpringApplication.run(CreditCardProcessorApplication.class, args);

		final CardProcessor cardProcessor = new CardProcessor();
		//Cargo 3 tarjetas en el sistema.
		cardProcessor.iniciarTarjetas();


		final Card card1 = cardProcessor.getTarjetas().get(0);
		final Card card2 = cardProcessor.getTarjetas().get(1);
		final Card card3 = cardProcessor.getTarjetas().get(2);
		final Card card4 = new VisaCard(98765, "Gabriel Batistuta", FEBRUARY.getValue(), 2030);

		//Invocar un método que devuelva toda la información de una tarjeta
		cardProcessor.getTarjetas().forEach(tarjeta-> System.out.println(tarjeta.getAllInfo()));

		//Informar si una tarjeta es válida para operar
		cardProcessor.getTarjetas().forEach(tarjeta->{
			try {
				tarjeta.isValid(cardProcessor.getTarjetas());
				System.out.println("Tarjeta válida");
			} catch (final CardProcessException error) {
				System.out.println(String.format("Tarjeta inválida: %s", error.getMessage()));
			}
		});

		//Informar si una operación es valida
		final boolean operacionValida1 = cardProcessor.procesarOperacion(new Operacion(card1, 500d));
		System.out.println(String.format("Operacion %s", operacionValida1 ? "Valida":"Invalida"));
		final boolean operacionValida2 = cardProcessor.procesarOperacion(new Operacion(card2, 1500d));
		System.out.println(String.format("Operacion %s", operacionValida2 ? "Valida":"Invalida"));
		final boolean operacionValida3 = cardProcessor.procesarOperacion(new Operacion(card4, 200d));
		System.out.println(String.format("Operacion %s", operacionValida3 ? "Valida":"Invalida"));

		//Identificar si una tarjeta es distinta a otra
		final boolean iguales = cardProcessor.compararTarjetas(card1, card2);
		System.out.println(String.format("La tarjeta %d y la tarjeta %d %s iguales", card1.getNumero(), card2.getNumero(), iguales ? "" : "NO"));


		final Tasa tasaVisa = card1.informarTasa(1000d);
		System.out.println(String.format(MSJ_TASA, tasaVisa.getMarca(), tasaVisa.getImporte()));
		final Tasa tasaNara = card2.informarTasa(1000d);
		System.out.println(String.format(MSJ_TASA, tasaNara.getMarca(), tasaNara.getImporte()));
		final Tasa tasaAmex = card3.informarTasa(1000d);
		System.out.println(String.format(MSJ_TASA, tasaAmex.getMarca(), tasaAmex.getImporte()));
	}

}
