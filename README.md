# Budowanie, uruchomienie i testowanie
* Budowanie i testy jednostkowe
```./gradlew build```
* Uruchomienie (SpringBoot)
```./gradlew bootRun -Dnewsapi_org_api_key=API_KEY```
* Uruchomienie testów integracyjnych (SpringBoot)
```./gradlew integrationTest```

# Dokumentacja usług
Po uruchomieniu projektu należy w przeglądarce wejść:
http://localhost:8080/swagger-ui.html

# Docker
Obraz i kontener mają nazwę newsreader-dwalczak
* Utworzenie obrazu docker
```./gradlew dockerBuildImage```
* Utworzenie obrazu docker i uruchomienie kontenera
```./gradlew dockerRun -Dnewsapi_org_api_key=API_KEY```
* Podgląd logów aplikacji w kontenerze
```docker logs --tail=10 -f newsreader-dwalczak```
* Usunięcie obrazu docker
```./gradlew dockerRemoveImage```

# Podział kodów

## . (Projekt główny)
Zawiera:
* specyfikację wystawianych usług (swagger-api.yaml),
* definicję obrazu docker

## web-rs
Warstwa usług REST
Zawiera punkty wejścia usług.
Stub kodów serwera wygenerowany przez swagger.

## service
Warstwa usług
Zawiera "właściwą" implementację usług.

## adapter-newsapi
Warstwa integracyjna z newsapi.org.
Zawiera implementację komunikacji z serwisem newsapi.org.

## model
Zawiera klasy modelu danych. 

## frontend
Zawiera frontend (angular)