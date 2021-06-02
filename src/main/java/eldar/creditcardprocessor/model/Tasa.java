package eldar.creditcardprocessor.model;

public class Tasa {

    String marca;
    double importe;

    public Tasa() {
    }

    public String getMarca() {
        return marca;
    }

    public Tasa(String marca, double importe) {
        this.marca = marca;
        this.importe = importe;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
