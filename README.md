# Asd123-ohtu  [![Build Status](https://travis-ci.com/jjjjm/Asd123-ohtu.svg?branch=master)](https://travis-ci.com/jjjjm/Asd123-ohtu)  [![codecov](https://codecov.io/gh/jjjjm/Asd123-ohtu/branch/master/graph/badge.svg)](https://codecov.io/gh/jjjjm/Asd123-ohtu) [Lukuvinkkikirjasto](https://asd123-ohtu.herokuapp.com/)

Lukuvinkkikirjasto-sovelluksessa käyttäjä voi merkitä muistiin kirjoja, videoita, blogi-kirjoituksia yms., joita haluaa lukea/katsoa myöhemmin. Sovellus tehdään ryhmätyönä Helsingin yliopiston kurssilla Ohjelmistotuotanto 2018. Tarkemman tehtävänannon voi lukea [täältä](https://github.com/mluukkai/ohjelmistotuotanto2018/wiki/miniprojekti-speksi).

Tehtävän pääpainona on harjoitella [Scrumia](https://www.scrum.org/resources/what-is-scrum) ohjelmistotuotannon hallintamenetelmänä.

* Kieli: Java
* Buildaus ja konfiguraatio: [Gradle](https://gradle.org/) & [Spring Boot](https://spring.io/)
* Tietokanta: PostgreSQL

## Dokumentaatio
[Product backlog](https://docs.google.com/spreadsheets/d/19UW1VPX9_UCwK7XIggMv1iKhb0BMqjDI0IBkyVOpkDY/edit#gid=0)

[Viikon 1 sprint backlog](https://docs.google.com/spreadsheets/d/19UW1VPX9_UCwK7XIggMv1iKhb0BMqjDI0IBkyVOpkDY/edit#gid=700264663)

[Viikon 2 sprint backlog](https://docs.google.com/spreadsheets/d/19UW1VPX9_UCwK7XIggMv1iKhb0BMqjDI0IBkyVOpkDY/edit#gid=2112997468)

[Viikon 3 sprint backlog](https://docs.google.com/spreadsheets/d/19UW1VPX9_UCwK7XIggMv1iKhb0BMqjDI0IBkyVOpkDY/edit#gid=760035095)

## Travis
Sovelluksen jatkuva integrointi Travisissa löytyy [täältä](https://travis-ci.com/jjjjm/Asd123-ohtu).

## Codecov
Sovelluksen testikattavuutta voi tarkastella [täältä](https://codecov.io/gh/jjjjm/Asd123-ohtu).

## Asennus ja käynnistys
Avaa komentorivi ja avaa PostreSQL instanssi komennolla
```
sudo -u postgres psql
```
Vaihda käyttäjän postgres salasanaksi 'admin' syöttämällä komento
```
\password postgres
```
Luo tämän jälkeen tietokanta nimeltä 'archive' syöttämällä komento
```
sudo -u postgres createdb archive
```
Nyt voit poistua PostgreSQL instanssista näppäinyhdistelmällä 
```
CTRL+Z
```
Tarkista pyöriikö koneella postgresql taustaprosessi syöttämällä komento
```
sudo service postgresql status
```
Jos prosessi ei ole käynnissä, käynnistä se komennolla
```
sudo service postgresql start
```
Jos prosessi on jo käynnissä, käynnistä se uudelleen komennolla
```
sudo service postgresql restart
```
Siirry komentorivillä siihen hakemistoon, minne haluat ohjelman latautuvan ja lataa se gitistä komennolla
```
git clone https://github.com/jjjjm/Asd123-ohtu.git
```
Siirry projektikansioon komennolla ```cd Asd123-ohtu```. Käynnistä ohjelma komennolla ```gradle run```, tai jos se ei toimi, niin ```./gradlew run```.
Ohjelma käynnistyy. Sovellus käynnistyy osoitteeseen http://localhost:8080.

## Definition of done
Ominaisuus toimii sovelluksessa ja sitä on testattu.

## Muuta
ryhmä-id: 5bed4c347aa7f8001ecd6d36

## Lisenssi
 [![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
