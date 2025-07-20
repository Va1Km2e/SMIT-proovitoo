# Seene Leiukohtade Kaardirakendus

[![Java](https://img.shields.io/badge/Java-21-blue.svg?logo=openjdk&logoColor=white)](https://www.java.com)  
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen.svg?logo=spring)](https://spring.io/projects/spring-boot)  
[![PostGIS](https://img.shields.io/badge/PostgreSQL-PostGIS-blue.svg?logo=postgresql)](https://postgis.net/)  
[![React](https://img.shields.io/badge/React-18-blue.svg?logo=react)](https://reactjs.org/)  
[![Docker](https://img.shields.io/badge/Docker-Compose-blue.svg?logo=docker)](https://www.docker.com/)

See on täislahendusega (full-stack) veebirakendus seene leiukohtade kaardistamiseks ja haldamiseks. Projekt on loodud proovitööna Java/Full-stack arendaja (GIS tiim) positsioonile.

Rakendus koosneb REST API-ga backend-teenusest, interaktiivsest veebirakendusest ja andmebaasist, mis on kõik konteineriseeritud Dockeriga lihtsaks käivitamiseks.

## Põhifunktsionaalsus

* **REST API:** Täielik CRUD (Create, Read, Update, Delete) funktsionaalsus seene leiukohtade haldamiseks.
* **GeoJSON tugi:** Kõik API teenused kasutavad GeoJSON formaati asukohaandmete sisendiks ja väljundiks.
* **Interaktiivne kaart:** Veebirakendus kuvab kõik leiukohad interaktiivsel Leafleti kaardil.
* **Andmete lisamine kaardilt:** Uusi leiukohti saab lisada otse kaardile klikkides.
* **Piiratud kaardiala:** Kaardi vaade ja interaktsioonid on piiratud Eesti ja lähiümbrusega.

## Tehnoloogiate Ülevaade

| Komponent              | Tehnoloogiad                                                                                        |
| ---------------------- | -------------------------------------------------------------------------------------------------- |
| **Backend**            | Java 21, Spring Boot 3, PostgreSQL Driver, `org.locationtech.jts` (JTS), Lombok    |
| **Frontend**           | React 18, Vite, Leaflet, JavaScript, CSS                                                      |
| **Andmebaas & DevOps** | PostgreSQL koos PostGIS laiendusega, Docker, Docker Compose                                     |


## Käivitamine

Et projekt dockeris käivitada on vaja see varem paigaldada, ning kindlasti üle vaadata kas docker käib

### 1. Klooni repositoorium

```bash
git clone https://github.com/Va1Km2e/SMIT-proovitoo
```
# 2. Liigu terminalis backend kausta ja ehita projekt Gradle wrapperi abil:
```bash
cd SMIT-proovitoo/backend
gradlew build
```
# 3. Liigu tagasi projekti baaskausta ja käivita rakendus Docker Compose abil:
```bash
cd ..
docker-compose up --build
```
# 4. Kontrolli, kas konteinerid töötavad:
```bash
docker ps
```
# 5. Kui soovid konteinerid peatada:
```bash
docker-compose down
```