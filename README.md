# Dokumentacja aplikacji Digital Signature
# Opis projektu
Aplikacja Digital
Signature została stworzona w celu zaprezentowania zastosowania kryptografii oraz mechanizmów zabezpieczających przed atakami. Projekt opiera się na technologii Spring Boot i implementuje:

Podpis cyfrowy – Funkcjonalność oparta na algorytmie RSA, pozwalająca na podpisanie wiadomości cyfrowej i weryfikację jej integralności oraz autentyczności.

Zabezpieczenie przed atakiem typu DoS (Denial of Service) – Mechanizm ograniczający liczbę żądań w jednostce czasu (tzw. rate limiting). Zastosowano tu prostą konfigurację pozwalającą na obsłużenie maksymalnie 5 żądań na minutę od jednego użytkownika.

Celem projektu było nie tylko zademonstrowanie praktycznego wykorzystania kryptografii, ale również pokazanie podatności na ataki i sposobu przeciwdziałania takim zagrożeniom.

# Technologie
Aplikacja została zbudowana przy użyciu następujących technologii:

1. Java w wersji 11
2. Spring Boot w wersji 2.6+
3. Maven jako system zarządzania zależnościami i budowania projektu
4. Algorytm RSA – zastosowany do generowania kluczy i podpisywania wiadomości
5. Rate limiting – implementacja ograniczenia liczby żądań w oparciu o prostą konfigurację w Spring Boot
6. JUnit 5 – framework do testowania aplikacji

# Funkcjonalności aplikacji
Podpisywanie wiadomości cyfrowych
Aplikacja umożliwia użytkownikowi przesłanie wiadomości tekstowej, która następnie jest podpisywana cyfrowo przy użyciu klucza prywatnego. Otrzymany podpis (w formacie Base64) może być następnie weryfikowany przez odbiorcę wiadomości za pomocą klucza publicznego.

Zabezpieczenie przed atakiem DoS
Aby zapobiec przeciążeniu serwera, aplikacja implementuje mechanizm rate limiting. Pozwala on na obsługę maksymalnie 5 żądań w ciągu jednej minuty od danego użytkownika. Po przekroczeniu limitu użytkownik otrzymuje odpowiedź z kodem błędu 429 Too Many Requests.

Testowanie funkcjonalności
W aplikacji zaimplementowano testy jednostkowe dla kluczowych komponentów, w tym:

1. Generowania par kluczy publicznych i prywatnych.
2. Podpisywania i weryfikacji wiadomości.
3. Sprawdzania działania mechanizmu ograniczania liczby żądań.
4. Instalacja i uruchomienie
5. Aby uruchomić aplikację, należy wykonać poniższe kroki:

# Przygotowanie środowiska
Upewnij się, że masz zainstalowane następujące oprogramowanie:

1. Java Development Kit (JDK) w wersji 11 lub nowszej.
2. Maven – narzędzie do zarządzania zależnościami i budowania projektu.
3. IntelliJ IDEA (lub inne IDE wspierające Spring Boot).


# Problemy i sposoby ich rozwiązania
W trakcie pracy nad projektem napotkano kilka problemów. Poniżej opisane są napotkane trudności oraz zastosowane rozwiązania:

1. Niepoprawne rozpoznawanie zależności Spring Framework
Opis problemu: IntelliJ IDEA nie mogła znaleźć symbolu springframework, co wskazywało na problem z zależnościami Maven.
Rozwiązanie:

Upewniono się, że wszystkie wymagane zależności są poprawnie zdefiniowane w pliku pom.xml.
W IntelliJ IDEA wybrano opcję Reimport w panelu Maven.
Jeśli to nie pomogło, zastosowano opcję File > Invalidate Caches / Restart.

2. Port 8080 już w użyciu
Opis problemu: Aplikacja nie mogła się uruchomić, ponieważ port 8080 był zajęty przez inny proces.
Rozwiązanie:

Zmieniono port w pliku konfiguracyjnym application.properties na alternatywny:
properties
Skopiuj kod
server.port=8081
Upewniono się, że żadna inna aplikacja nie używa tego samego portu.

3. Błąd rate limiting: "Too Many Requests"
Opis problemu: Użytkownik otrzymywał błąd 429 Too Many Requests, mimo że nie przekroczył limitu żądań.
Rozwiązanie:

Zweryfikowano konfigurację mechanizmu ograniczenia liczby żądań.
Upewniono się, że licznik żądań jest poprawnie resetowany co minutę.
W razie potrzeby zwiększono limit zapytań dla celów testowych.
Wnioski
Aplikacja Digital Signature skutecznie pokazuje praktyczne zastosowanie kryptografii oraz wdrożenie mechanizmów bezpieczeństwa. W trakcie realizacji projektu udało się nie tylko osiągnąć wyznaczone cele, ale również zidentyfikować potencjalne trudności i znaleźć rozwiązania tych problemów.

Projekt jest gotowy do dalszego rozwijania, na przykład poprzez:

Dodanie obsługi większej liczby algorytmów kryptograficznych (np. AES, SHA-256).
Rozbudowanie mechanizmów bezpieczeństwa o bardziej zaawansowane systemy detekcji ataków.

# Wnioski
Aplikacja Digital Signature skutecznie pokazuje praktyczne zastosowanie kryptografii oraz wdrożenie mechanizmów bezpieczeństwa. W trakcie realizacji projektu udało się nie tylko osiągnąć wyznaczone cele, ale również zidentyfikować potencjalne trudności i znaleźć rozwiązania tych problemów.

Projekt jest gotowy do dalszego rozwijania, na przykład poprzez:
1. Dodanie obsługi większej liczby algorytmów kryptograficznych (np. AES, SHA-256).
2. Rozbudowanie mechanizmów bezpieczeństwa o bardziej zaawansowane systemy detekcji ataków.
