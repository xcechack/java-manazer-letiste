Navod k spusteniu weboveho rozhrania:

  1. Vytvorime si novy adresar (napr. Airportmanager)
  1. Do tohoto adresara si checkoutneme zdrojovy kod z repozitara na tejto stránke
  1. Otvorime si prikazovy riadok a nasmerujeme sa do tohoto adresara
  1. Prikazom **mvn install** skompilujeme backend aj frontend aplikacie a nainstalujeme do maven lokalneho repozitaru
  1. po uspesnom vykonani mvn install sa nasmerujeme do adresara **airportmanager-web**
  1. Ak je backend nainstalovany, treba skontrolovat ci bezi databaza. Ta musi byt typu **Java DB**, spustena na **localhoste**, porte **1527** a v nej vytvorena `*` databaza **pa165** s prihlasovacimi udajmi **pa165**/**pa165**.
  1. Ak nam bezi databaza, mozeme prikazom mvn jetty:run spustit server jetty.
  1. Po uspesnom spusteni servera jetty, by mala byt aplikacia dostupna na adrese http://localhost:8080/pa165/
  1. Pre vytvorenie prveho uzivatela, staci prejst na adresu http://localhost:8080/pa165/users/init - aplikacia vytvori uzivatela **admin/admin**, prihlasi sa za neho a zobrazi stranku spravy uzivatelov. Tato operacia prebehne iba za podmienky, ze uzivatel s menom admin este neexistuje a zaroven, ze v databaze uzivatelov sa nenachadza ziadny iny uzivatel.

> `*` - hibernate je nakonfigurovany na create-drop