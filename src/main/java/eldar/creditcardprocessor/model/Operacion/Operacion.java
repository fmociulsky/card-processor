package eldar.creditcardprocessor.model.Operacion;

import eldar.creditcardprocessor.exceptions.OperacionProcessException;
import eldar.creditcardprocessor.model.Card.Card;
import eldar.creditcardprocessor.model.Tasa;

import java.io.Serializable;

import static eldar.creditcardprocessor.exceptions.OperacionProcessException.OperacionProcessErrorEnum.AMOUNT_TOO_HIGH;


public class Operacion implements Serializable {

    private static final long serialVersionUID = -7687337068767056591L;
    Card tarjeta;
    Double monto;

    public Operacion(Card tarjeta,  Double monto) {
        this.tarjeta = tarjeta;
        this.monto = monto;
    }

    public Card getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Card tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    boolean checkMonto(){
        return getMonto() <= 1000;
    }

    public void validarOperacion() throws OperacionProcessException {
        if(!checkMonto()) throw new OperacionProcessException(AMOUNT_TOO_HIGH);
    }

    public Tasa informarTasa() {
        return getTarjeta().informarTasa(getMonto());
    }
}
