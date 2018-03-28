# Budowanie, uruchomienie i testowanie

### Budowanie i testy jednostkowe

```
./gradlew build
```

### Uruchomienie SpringBoot

```
./gradlew bootRun -Dnewsapi_org_api_key=API_KEY
```
W przeglądarce wejść na stronę: http://localhost:8080

Aby (z uruchomionym SpringBoot'em) był podmieniany dynamicznie kod frontend'u należy także uruchomić:
```
./gradlew buildClientWatch
```
Wówcza, żeby przeglądarka zaczytała zmiany należy odświeżyć stronę.

### Uruchomienie SpringBoot (bez generowania frontend'u)

```
./gradlew bootRun -Dnewsapi_org_api_key=API_KEY -PdisableFrontendDev
```

### Uruchomienie testów integracyjnych (SpringBoot)

```
./gradlew integrationTest
```

# Dokumentacja usług
Po uruchomieniu projektu należy w przeglądarce wejść:
http://localhost:8080/swagger-ui.html

# Docker
Obraz i kontener mają nazwę newsreader-dwalczak
### Utworzenie obrazu docker
```
./gradlew dockerBuildImage
```
### Utworzenie obrazu docker i uruchomienie kontenera
```
./gradlew dockerRun -Dnewsapi_org_api_key=API_KEY
```
### Podgląd logów aplikacji w kontenerze
```
docker logs --tail=10 -f newsreader-dwalczak
```
### Usunięcie obrazu docker
```
./gradlew dockerRemoveImage
```


# Podział kodów

## . (Projekt główny)
Zawiera:
* specyfikację wystawianych usług (swagger-api.yaml),
* definicję obrazu docker

## web-rs
Moduł WEB.
Zawiera punkty wejścia usług REST oraz pliki zbudowanego frontend'u angular.

Stub kodów serwera wygenerowany przez swagger.
Nie wprowadzamy zmian w kodach generowanych:
* com.dwalczak.newsreader.rs.dto.* 
* com.dwalczak.newsreader.rs.configuration.SwaggerDocumentationConfig
* com.dwalczak.newsreader.rs.api.* (z wyjątkiem *ApiController.java)

## service
Moduł usług
Zawiera "właściwą" implementację usług.

## adapter-newsapi
Moduł integracji z newsapi.org.
Zawiera implementację komunikacji z serwisem newsapi.org.

## frontend
Zawiera kody źródłowe frontend'u w technologii angular