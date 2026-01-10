import java.io.Serializable;
import java.util.Objects;



// Klasa Kot, implementująca interfejs Serializable, aby obiekty tej klasy mogły być przesyłane przez sieć
public class Kot implements Serializable {
    private String imie;
    private int wiek;

    // Konstruktor
    public Kot(String imie, int wiek) {
        this.imie = imie;
        this.wiek = wiek;
    }

    // Metoda toString, która zwraca reprezentację obiektu w postaci tekstowej
    @Override
    public String toString() {
        return "Kot{imie='" + imie + "', wiek=" + wiek + "}";
    }

    // Metoda hashCode, która generuje kod hash dla obiektu Kot
    @Override
    public int hashCode() {
        return Objects.hash(imie, wiek);
    }
}
