package eldar.creditcardprocessor.model.Operacion;

import com.sun.istack.internal.NotNull;
import eldar.creditcardprocessor.Exceptions.OperacionProcessException;
import eldar.creditcardprocessor.model.Card.Card;

import static eldar.creditcardprocessor.Exceptions.OperacionProcessException.OperacionProcessErrorEnum.AMOUNT_TOO_HIGH;


public class Operacion {

    Card tarjeta;
    Double monto;

    public Operacion(@NotNull Card tarjeta, @NotNull Double monto) {
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
}
