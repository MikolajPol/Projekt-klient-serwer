import java.io.*;
import java.net.*;
import java.util.*;

public class Klient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter output = null;
        BufferedReader input = null;
        ObjectOutputStream objOutput = null;
        ObjectInputStream objInput = null;
        Scanner scanner = null;
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);

            // Wczytywanie ID klienta
            System.out.print("Podaj swoje ID klienta: ");
            String idInput = scanner.nextLine();  // Wczytujemy pełną linię
            Integer klientId = Integer.parseInt(idInput.trim()); // Parsujemy ID klienta
            output.println(klientId);  // Wysyłamy ID klienta do serwera

            // Odbieranie odpowiedzi od serwera
            String response = input.readLine();
            if (response != null && response.startsWith("Polaczenie odrzucone")) {
                System.out.println("Odrzucono polaczenie");
                return;  // Zakończenie działania klienta, jeśli połączenie zostało odrzucone
            }

            System.out.println("Polaczono z serwerem. Status: " + response);

            // Teraz tworzymy Object streams dla dalszej komunikacji
            objOutput = new ObjectOutputStream(socket.getOutputStream());
            objInput = new ObjectInputStream(socket.getInputStream());

            while (true) {
                // Używamy nextLine() dla całej linii wejściowej
                System.out.print("Podaj nazwę klasy do pobrania (kot/pies/samochod): ");
                String klasyRequest = scanner.nextLine().trim();  // Wczytujemy nazwę klasy

                objOutput.writeObject(klasyRequest);  // Wysyłamy żądanie o obiekty
                objOutput.flush();

                // Odbieranie odpowiedzi od serwera
                Object responseObj = objInput.readObject();

                // Sprawdzamy, czy odpowiedź jest typu List<?>
                if (responseObj instanceof List<?>) {
                    List<?> obiekty = (List<?>) responseObj;
                    // Sprawdzamy, czy obiekty w liście są typu Serializable
                    if (!obiekty.isEmpty() && obiekty.get(0) instanceof Serializable) {
                        // Bezpieczne rzutowanie, ponieważ mamy pewność, że elementy są typu Serializable
                        List<Serializable> serializables = new ArrayList<>();
                        for (Object obj : obiekty) {
                            // Rzutowanie każdego elementu w liście
                            if (obj instanceof Serializable) {
                                serializables.add((Serializable) obj);
                            }
                        }
                        // Wypisanie otrzymanych obiektów
                        System.out.println("Otrzymane obiekty: ");
                        serializables.forEach(System.out::println);
                    } else {
                        System.out.println("Nie znaleziono obiektów dla klasy: " + klasyRequest);
                    }
                } else {
                    System.out.println("Otrzymany obiekt nie jest listą.");
                }
            }

        } catch (IOException e) {
            System.out.println("Odrzucono polaczenie");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objInput != null) try { objInput.close(); } catch (IOException e) {}
            if (objOutput != null) try { objOutput.close(); } catch (IOException e) {}
            if (input != null) try { input.close(); } catch (IOException e) {}
            if (output != null) output.close();
            if (socket != null) try { socket.close(); } catch (IOException e) {}
            if (scanner != null) scanner.close();
        }
    }
}
