# PositionSearchApplication

## Az alkalmazás futtatásához a következő lépéseket kell végrehajtani: ##

1. Klónozzuk le a repository-t a GitHub-ról a saját számítógépre:

   ```
   git clone https://github.com/sisanny/PositionSearchApplication.git
   ```

2. Nyissuk meg az Intellij IDEA-t, és válasszuk a `File -> Open` menüt.

3. Keresünk és válasszuk ki az alkalmazásunk mappáját.

4. Várjuk meg, amíg a projekt betöltődik az Intellij IDEA-ba.

5. Nyissuk meg a `src/main/resources/application.properties` fájlt, és ellenőrizzük, hogy a következő adatok helyesek-e:

   ```
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.defer-datasource-initialization=true
   spring.sql.init.mode=always
   spring.jpa.hibernate.ddl-auto=create-drop
   ```

6. Ha az alkalmazásunkat az Intellij IDEA-ban szeretnénk futtatni, kattintsunk a jobb felső sarokban található zöld "Run" gombra, majd válasszuk ki az `Application` opciót. Az alkalmazásunk most már fut, és elérhető a `localhost:8080` webcímen.

7. Ha a terminálban szeretnénk futtatni az alkalmazást, navigáljunk a projektünk gyökérkönyvtárába, majd írjuk be az alábbi parancsokat:

   ```
   mvn clean install
   java -jar target/spring-application-position-search-0.0.1-SNAPSHOT.jar.jar
   ```

   Az alkalmazásunk most már fut, és elérhető a `localhost:8080` webcímen.
   Az alkalmazás dokumentációja megtekinthető a Swagger UI-n keresztül: http://localhost:8080/swagger-ui/index.html#


## Ahhoz, hogy deployment ready legyen az alkalmazás, a következő lépésekre van szükség: ##


1. CI/CD pipeline beállítása: A CI/CD pipeline egy nagyon hasznos eszköz a folyamatok automatizálására. Először is fel kell venni egy új pipeline-t, amely figyeli a forráskód repository változásait, majd az automatikus build és deployment folyamatokat elvégzi.

2. Build folyamat beállítása: A build folyamat során az alkalmazás forráskódját le kell fordítani, majd létre kell hozni egy futtatható .jar fájlt. A Maven egy nagyon hasznos eszköz a build folyamat automatizálásához. A pipeline-ban be kell állítani a Maven build parancsot.

3. Tesztelés: Ahhoz, hogy az alkalmazás megbízhatóan fusson, szükség van tesztekre. Peldaul a JUnit hasznalataval. A Maven lehetővé teszi a JUnit tesztek futtatását a build folyamatban. Ha a tesztek nem futnak le, akkor az alkalmazás nem kerülhet deployra.

4. Konfigurációk: A konfigurációk különböző környezetekhez eltérőek lehetnek. Az alkalmazásnak legalább két környezetet kell támogatnia, az egyik a development, a másik a production. Az alkalmazás konfigurációs fájljait az adott környezetnek megfelelően kell beállítani. A Maven lehetővé teszi a konfigurációs fájlok beillesztését a build folyamatba.

5. Adatbázis: Az alkalmazásnak több adatbázis példányt kell támogatnia, különösen akkor, ha különböző környezetekben kell futtatni. A helyes konfigurációhoz a spring.profiles.active beállítás segítségével különböző profilokat kell definiálni az alkalmazásban. Ez lehetővé teszi, hogy különböző adatbázis példányokhoz tartozó beállításokat adjunk meg a különböző környezetekben való futtatáshoz. Tehát a helyes beállításhoz hozzá kell adni a spring.profiles.active konfigurációs opciót a megfelelő értékkel az adatbázis példányokhoz tartozó profilok használatához.

6. Dockerizálás: A Docker egy eszköz az alkalmazások konténerizálására. A konténerizálás lehetővé teszi az alkalmazás függőségeinek és a futási környezetnek a szétválasztását. A Docker konténerbe csomagolja az alkalmazást, majd az elkészült image-t fel lehet tölteni a Docker Hub-ra vagy a saját registry-re.

7. Deployment: A deployment során az alkalmazás konténer formájában történő indítása és a környezet beállítása a két alapvető lépés. A konténer indítására például a Kubernetes használható, amely lehetővé teszi az alkalmazások könnyű skálázását és menedzselését konténerekben. A környezet beállítása során pedig fontos figyelembe venni az alkalmazás környezeti változóit és konfigurációs beállításait, amelyek biztosítják a helyes működést.


## Az alkalmazás akkor lesz production ready, ha megfelel a következő követelményeknek: ##

1. Biztonság: Az position search alkalmazás akkor lesz production ready, ha biztonságos autentikáció és autorizáció is elérhető rajta keresztül. Ehhez érdemes implementálni egy login endpointot és egy autentikációs rendszert, például a Spring Security segítségével. Így a felhasználók tudnak regisztrálni, majd belépni a felhasználói fiókjukba, és csak az engedélyezett erőforrásokhoz férhetnek hozzá.

2. Skálázhatóság: Az alkalmazásnak lehetővé kell tennie a horizontális skálázhatóságot, amely lehetővé teszi az alkalmazás elosztott futtatását több gépen, hogy a terhelés elosztása hatékonyabb legyen. Erre példa lehet a Kubernetes, amely lehetővé teszi a konténerek elosztott futtatását.

3. Monitorozás: Az alkalmazásnak monitorozni kell az állapotát és az eseményeit, hogy az üzemeltetők gyorsan és hatékonyan reagálhassanak a problémákra. Erre példa lehet az ELK (Elasticsearch, Logstash, Kibana) stack, amely lehetővé teszi az alkalmazás logfájljainak monitorozását és elemzését.

4. Rugalmasság: Az alkalmazásnak rugalmasnak kell lennie az üzleti követelmények változásaival szemben. Erre példa lehet a Spring Cloud, amely lehetővé teszi az alkalmazás mikroszolgáltatásokra való felbontását, amely lehetővé teszi az egyes szolgáltatások függetlenül történő fejlesztését és telepítését.

