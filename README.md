
# Aplikacja Klient-Serwer

Aplikacja Klient-Serwer w Javie, która umożliwia komunikację między serwerem a klientem za pomocą połączenia TCP/IP. Klient może wysyłać żądania o obiekty do serwera, który odpowiada odpowiednią kolekcją obiektów.

## Funkcjonalności:

1. **Serwer**:
    - Serwer obsługuje wiele połączeń klientów jednocześnie (do maksymalnej liczby 5 klientów).
    - Serwer przyjmuje żądania o obiekty (np. `Kot`, `Pies`, `Samochod`).
    - Jeśli klient wybierze klasę, która nie istnieje w mapie, serwer wysyła **dowolny obiekt** (np. pustego `Kota`).
    - Jeśli klient wybierze opcję zakończenia połączenia, serwer zakończy komunikację z danym klientem.
    - Serwer rejestruje liczbę aktywnych połączeń i wypisuje ją w logach za każdym razem, gdy klient się łączy lub rozłącza.
    - W przypadku przekroczenia maksymalnej liczby klientów, serwer odrzuca połączenie i informuje klienta.

2. **Klient**:
    - Klient łączy się z serwerem, odbiera swoje ID i wykonuje żądania.
    - Klient ma do wyboru trzy klasy (`Kot`, `Pies`, `Samochod`) oraz opcję zakończenia połączenia.
    - Klient wielokrotnie wybiera różne obiekty do pobrania, aż osiągnie limit prób (3 próby).
    - Klient obsługuje błąd rzutowania w przypadku, gdy serwer wyśle obiekt, który nie może zostać poprawnie zrzutowany.

## Jak uruchomić:

### 1. Uruchomienie serwera:

Serwer należy uruchomić w pierwszej kolejności. Otwórz terminal, przejdź do katalogu z plikami i uruchom serwer:


javac -d bin src/Serwer.java
java -cp bin Serwer

### 2. Uruchomienie klienta:

Po uruchomieniu serwera, uruchom klienta:

javac -d bin src/Klient.java
java -cp bin Klient

### 3. Interakcja z klientem:

1. Klient połączy się z serwerem i otrzyma swoje ID.
2. Klient wybiera klasę, z której chce otrzymać obiekty (np. `1` dla `Kot`).
3. Serwer wysyła obiekty do klienta, a klient wypisuje je na konsoli.
4. Klient może wybrać zakończenie połączenia, wpisując `4`.
5. Liczba aktywnych połączeń jest rejestrowana przez serwer i wyświetlana w logach.

## Szczegóły techniczne:

### Serwer:
- Serwer korzysta z **TCP** i działa na porcie 12345.
- Serwer obsługuje wielu klientów jednocześnie przy użyciu wątków (ExecutorService).
- Serwer ogranicza liczbę połączeń do maksymalnie 5 klientów. Dalsze próby połączenia są odrzucane.

### Klient:
- Klient łączy się z serwerem i odbiera obiekty z serwera.
- Klient może wybierać obiekty do pobrania (np. `Kot`, `Pies`, `Samochod`) oraz zakończyć połączenie.


