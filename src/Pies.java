import java.io.Serializable;
import java.util.Objects;

// Klasa Pies, implementująca interfejs Serializable, aby obiekty tej klasy mogły być przesyłane przez sieć
public class Pies implements Serializable {
    private String imie;
    private String rasa;

    // Konstruktor
    public Pies(String imie, String rasa) {
        this.imie = imie;
        this.rasa = rasa;
    }

    // Metoda toString, która zwraca reprezentację obiektu w postaci tekstowej
    @Override
    public String toString() {
        return "Pies{imie='" + imie + "', rasa='" + rasa + "'}";
    }

    // Metoda hashCode, która generuje kod hash dla obiektu Pies
    @Override
    public int hashCode() {
        return Objects.hash(imie, rasa);
    }
}
