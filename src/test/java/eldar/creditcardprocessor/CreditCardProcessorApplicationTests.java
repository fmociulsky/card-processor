package eldar.creditcardprocessor;

import eldar.creditcardprocessor.model.Card.Card;
import eldar.creditcardprocessor.model.Card.CardEnum;
import eldar.creditcardprocessor.model.Card.VisaCard;
import eldar.creditcardprocessor.model.CardProcessor;
import eldar.creditcardprocessor.model.Operacion.Operacion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Month;
import java.util.List;

import static eldar.creditcardprocessor.model.Card.Card.MSJ_TASA;
import static eldar.creditcardprocessor.model.Card.Card.TO_STRING;
import static eldar.creditcardprocessor.model.Card.CardEnum.AMEX;
import static eldar.creditcardprocessor.model.Card.CardEnum.NARA;
import static eldar.creditcardprocessor.model.Card.CardEnum.VISA;
import static java.time.Month.FEBRUARY;

@SpringBootTest
class CreditCardProcessorApplicationTests {

	CardProcessor cardProcessor = new CardProcessor();

	@Test
	void getObtenerInfoTarjetTest() {
		iniciarTarjetas();
		final String allInfo = cardProcessor.getTarjetas().get(0).getAllInfo();
		Assertions.assertThat(allInfo).isEqualTo(String.format(TO_STRING, VISA.getValue(), 123456, "Lionel Messi", Month.AUGUST.getValue(), 2025));
	}

	@Test
	void procesarOperacionOKTest() {
		iniciarTarjetas();
		final Card card = cardProcessor.getTarjetas().stream().filter(t->t.getMarca() == VISA).findFirst().get();
		final boolean procesada = cardProcessor.procesarOperacion(new Operacion(card, 500d));
		Assertions.assertThat(procesada).isTrue();
	}

	@Test
	void procesarOperacionMontoExcedidoTest() {
		iniciarTarjetas();
		final Card card = cardProcessor.getTarjetas().stream().filter(t->t.getMarca() == NARA).findFirst().get();
		final boolean procesada = cardProcessor.procesarOperacion(new Operacion(card, 1500d));
		Assertions.assertThat(procesada).isFalse();
	}

	@Test
	void procesarOperacionTarjetaVencidaTest() {
		iniciarTarjetas();
		final Card card = cardProcessor.getTarjetas().stream().filter(t->t.getMarca() == AMEX).findFirst().get();
		final boolean procesada = cardProcessor.procesarOperacion(new Operacion(card, 500d));
		Assertions.assertThat(procesada).isFalse();
	}

	@Test
	void procesarOperacionTarjetaInexistenteTest() {
		iniciarTarjetas();
		final Card card = new VisaCard(98765, "Gabriel Batistuta", FEBRUARY.getValue(), 2030);
		final boolean procesada = cardProcessor.procesarOperacion(new Operacion(card, 500d));
		Assertions.assertThat(procesada).isFalse();
	}

	@Test
	void tarjetasIgualesOKTest() {
		iniciarTarjetas();
		final Card card = new VisaCard(123456, "Lionel Messi", Month.AUGUST.getValue(), 2025);
		final boolean iguales = card.equals(cardProcessor.getTarjetas().get(0));;
		Assertions.assertThat(iguales).isTrue();
	}

	@Test
	void tarjetasDiferentesTest() {
		iniciarTarjetas();
		//cambio el año de vencimiento
		final Card card = new VisaCard(123456, "Lionel Messi", Month.MAY.getValue(), 2035);
		final boolean iguales = card.equals(cardProcessor.getTarjetas().get(0));;
		Assertions.assertThat(iguales).isFalse();
	}


	public void iniciarTarjetas() {
		cardProcessor.addCard(VISA, 123456, "Lionel Messi", Month.MAY.getValue(), 2025);
		cardProcessor.addCard(CardEnum.NARA, 654321, "Cristiano Ronaldo", Month.JANUARY.getValue(), 2022);

		//Esta tarjeta esta vencida
		cardProcessor.addCard(CardEnum.AMEX, 456789, "Kylian Mbappe", Month.MARCH.getValue(), 2020);
	}

}
