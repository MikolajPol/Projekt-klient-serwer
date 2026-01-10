import java.io.Serializable;
import java.util.Objects;

// Klasa Samochod, implementująca interfejs Serializable, aby obiekty tej klasy mogły być przesyłane przez sieć
public class Samochod implements Serializable {
    private String model;
    private int rocznik;

    // Konstruktor
    public Samochod(String model, int rocznik) {
        this.model = model;
        this.rocznik = rocznik;
    }

    // Metoda toString, która zwraca reprezentację obiektu w postaci tekstowej
    @Override
    public String toString() {
        return "Samochod{model='" + model + "', rocznik=" + rocznik + "}";
    }

    // Metoda hashCode, która generuje kod hash dla obiektu Samochod
    @Override
    public int hashCode() {
        return Objects.hash(model, rocznik);
    }
}
