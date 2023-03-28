Bobocu Alexandra-Florentina, 321CA

In realizarea temei, pentru a nu modifica fisierele din pachetul "fileio"
si pentru a tine mai usor evidenta filmelor, serialelor, actorilor si
utilizatorilor, am creat o baza de date in care am aplicat patternul Singleton.
In pachetul "database", am clasele care se ocupa de baza de date:
-> in Helpers.java, am 4 metode corespunzatoare entitatilor pe care le modelez.
Ma folosesc de aceasta clasa pentru a adauga in baza de date informatiile
ce caracterizeaza aceste entitati. Tot in aceasta clasa apelez metoda
creata in Database.java menita sa goleasca baza de date.
-> in Database.java, creez 4 noi liste in care adaug filmele, serialele,
actorii, utilizatorii. Tot in acest fisier golesc baza de date.

Tot pentru a nu modifica fisierele din pachetul "fileio", ci doar pentru a ma
folosi de ele, in pachetul "entertainment", mi-am creat propriile clase ce au
legatura cu videoclipurile. In Video.java, am inclus campuri comune atat pentru
filme, cat si pentru seriale, cum ar fi: titlul unui videoclip, anul aparitiei,
gen, actorii care joaca in videoclip, numarul total de filme/seriale favorite,
vizionate, nota acordata. Clasa Video este mostenita de clasele Movie si Show,
in care la randul lor voi completa cu informatii specifice tipului de
videoclip: in clasa Movie, am o lista de note acordate unui anumit film
din partea utilizatorilor, dar si o lista de nume de utilizatori ce au
evaluat filmul. In clasa Show, nu am o lista de note acordate serialului,
ci aceasta lista exista in clasa Season, deoarece, spre deosebire de
filme, serialele au sezoane, iar ratingul se calculeaza diferit, conform
enuntului. Tot in clasa Show, am o lista, care la randul ei contine
referinte catre alte liste de nume de utilizatori care au evaluat fiecare
sezon din serial in parte. In ambele clase, Movie si Show, fac verificari
specifice cu privire la utilizatorii ce acorda note videoclipului. Daca
acestia dau nota filmului/sezonului din serial, dar nu apar in lista celor
care au dat rating, numele utilizatorilor vor fi adaugate in lista. In ambele
clase, completez listele de note date filmului/ serialului. In clasa
Movie, doar returnez durata unui film, in timp ce in clasa Show, iterez prin
fiecare sezon al serialului in parte si adun duratele.
In pachetul existent "actor", mi-am creat clasa Actor, care seamana cu cea
specifica unui actor din pachetul "fileio": ActorInputData, dar in care am mai
adaugat 2 noi campuri: rating, ce se refera la nota acordata actorului si
numAwards, care se refera la numarul de premii castigate de actor.
Am creat un nou pachet "user", in care am inclus clasa ce se refera la un
utilizator, asemanatoare cu UserInputData. In plus, am mai adaugat o metoda
folositoare (getNumRatings) pentru Query pentru utilizator, care numara cate
note a acordat utilizatorul filmelor si serialelor.

Cele 3 tipuri de actiuni: comenzile, queriurile si recomandarile sunt grupate
intr-un nou pachet numit "actions".

1) COMENZILE
-> Favorite: Pentru utilizatorul care da comanda, verific lista
de videoclipuri favorite. Daca videoclipul care se doreste a fi adaugat la
lista de favorite exista deja in aceasta lista, se da un mesaj sugestiv si se
actualizeaza numarul de filme si seriale favorite. Daca videoclipul nu exista
in lista de favorite, verificam daca a fost vizionat. Daca da, se adauga
videoclipul in lista de favorite si se da un mesaj sugestiv. Daca nu,
videoclipul nu poate fi adaugat in lista de favorite din moment ce nu a fost
nici macar vizionat, deci dam un mesaj sugestiv.

-> View: Pentru utilizatorul care da comanda, verific in istoric daca
videoclipul pe care il vizioneaza mai apare. Daca da, atunci actualizez
numarul de vizualizari din istoric. Daca nu, inseamna ca videoclipul este
vazut de utilizator pentru prima oara si numarul de vizualizari din istoric
va fi 1. Pentru fiecare film si serial din baza de date, actualizez numarul
corespunzator de vizualizari, folosindu-ma de metoda incTotalViews din clasa
mostenita Video.

-> Rating: Daca numarul de sezoane primit in input este zero, inseamna
ca avem un film, in caz contrar un serial. Pentru utilizatorul care da comanda,
atat in cazul filmelor, cat si al serialelor, verific prima oara daca
videoclipul nu a fost vazut. Daca da, ratingul nu poate fi acordat deci dau un
mesaj sugestiv. Daca videoclipul a fost vazut:
- Pentru filme: Dupa ce am gasit filmul care trebuie evaluat, ma
folosesc de metoda hasUserGraded din clasa Movie pentru a verifica daca
numele utilizatorului care trebuie sa acorde nota filmului apare deja in
lista utilizatorilor ce au evaluat filmul. Daca da, dau un mesaj sugestiv,
iar in caz contrar, actualizez corespunzator lista de utilizatori din clasa
Movie ce au acordat note unui anumit film, dar si lista de note tot din clasa
Movie a notelor primite din partea mai multor utilizatori pentru un anumit
film.
- Pentru seriale: Procedez similar ca in cazul filmelor, insa ma
folosesc de metoda hasUserGraded din clasa Show. In aceasta metoda, pentru
fiecare serial in parte, am la dispozitie o lista de sezoane. Pentru fiecare
sezon, verific in lista de utilizatori care au dat nota sezonului daca apare
utilizatorul ce a dat actiunea. Daca da, dau un mesaj sugestiv, daca nu,
actualizez lista de utilizatori ce au dat rating serialului prin adaugarea
acestui nou utilizator (folosesc metoda addUserGraded din clasa Show) si
actualizez lista de note din clasa Season. Pentru a nu modifica clasa Season,
ma folosesc de metoda addGrade din clasa Show.

2) QUERY-URILE

Pentru rezolvarea acestui task, am creat pachetul "grades" in care am
calculat media aritmetica in mod corespunzator pentru entitatile:
- actor (in ActorGrade.java);
- film (in MovieGrade.java);
- serial (in ShowGrade.java).
  Actualizez in clasele Actor.java si Video.java media pentru cele 3 entitati.

  I) Pentru actori:
    - Average: Apelez metodele din clasele din pachetul "grades" pentru
      a calcula mediile si a actualiza campul rating din Actor.java si campul
      averageGrade din Video.java. Sortez corespunzator lista de actori in metoda
      findAscDesAverage. In noua lista result pun numele actorilor ce respecta
      conditiile.
    - Awards: Aplic filtrul din input pentru a gasi premiile. Elimin
      actorii care nu au premiile din filtru, iar actorii ramasi vor fi sortati
      dupa criteriile din enunt in metoda findAscDesAwards. Actualizez numarul de
      premii ale unui actor si populez lista result.
      -Filter description: Pentru fiecare actor in parte, verific daca in descrierea
      sa apar cuvintele cheie cautate. Descrierea urmeaza sa fie despartita in
      cuvinte. Retinem intr-o variabila ct numarul de cuvinte cheie care se regasesc
      si in descrierea actorului. Daca nu apar toate cuvintele cheie, actorul este
      eliminat. Sortez lista de actori conform cerintei si populez lista result.

  II) Pentru video-uri:
    - Rating: Folosesc functiile gradeForMovie si gradeForShow pentru a calcula
      mediile. Creez o noua lista de videouri in care adaug pe rand filmele si
      serialele, tinand cont de tipul entitatii cerute in query. Sortez videourile
      corespunzator folosind functia findAscDesRating. Parcurg lista sortata si pentru
      fiecare video in parte, verific prima oara filtrul de an, dupa care filtrul
      de gen. Daca anul din filtru nu coincide cu cel al videoclipului, nu adaug videoul
      din lista (shouldFilter devine true).
    - Favorite: Procedez similar ca la Rating in cazul listei de videouri. Inainte
      de a incepe cautarile in lista de videouri favorite a utilizatorului, o sa resetez
      numarul de videouri favorite la zero pentru a ma asigura ca am numarul real de
      videouri favorite la acest task. Sortez corespunzator videourile cu ajutorul
      metodei findAscDesFavorite. In cazul filtrelor, procedez similar ca la Rating.
    - Longest: Procedez similar ca la Rating si Favorite, doar ca apelez metoda
      findAscDesLongest pentru sortare.
    - Most Viewed: Analog Longest, doar ca apelez metoda findAscDesMostViewed
      pentru sortare.

  III) Pentru utilizatori:
    - Number of ratings: Sortez corespunzator lista de utilizatori in
      metoda findAscDesNumOfRatings si adaug doar primii utilizatori in result.

3) RECOMANDARILE

I) Basic:
- Standard: Pentru fiecare utilizator in parte, parcurg lista de filme si
de seriale. Verific in istoric sa nu existe filmul si serialul, deoarece un video
vor fi recomandat utilizatorului si pus in obiectul json. Daca utilizatorul a
vizionat toate filmele si serialele din baza de date, nu am recomandari de facut.
- Best unseen: Creez o noua lista in care adaug toate filmele si
serialele din baza de date, deoarece in Database.java tin doar evidenta filmelor
si serialelor separat. Folosesc si aici functiile din pachetul "grades":
gradeForMovie si gradeForShow pentru calculul mediilor in cazul filmelor si
in cazul serialelor. Dupa ce am sortat corespunzator videoclipurile, il
pun in obiectul json pe primul care nu apare in istoric si poate fi recomandat.

II) Premium:
- Popular: Lista videos contine toate filmele si serialele din baza de date.
Creez un map popGenre pe care il voi popula cu informatiile legate de video necesare la acest
task: genul videoclipului si numarul de vizualizari. O sa creez o noua lista popularGenreList
pe care o pot sorta dupa numarul de vizualizari corespunzator genului. In result retin
in acelasi timp genul unui videoclip, dar si numarul de vizualizari. Daca am gasit
utilizatorul potrivit, iteram in lista ce contine toate filmele si serialele si
verificam daca videoul exista in istoric. Daca nu, putem face recomandarea
utilizatorului. Retin in lista videoGenre genurile videoclipului gasit in lista
videos, iar in variabila key genul videoclipului gasit in result. Daca acest gen
din key se regaseste in videoGenre, inseamna ca am gasit primul videoclip
ce respecta cerinta din enunt si il pun in obiectul json.
- Favorite: Creez o noua lista in care adaug toate filmele si serialele din
baza de date. Inainte de a incepe cautarile in lista de videouri favorite a
fiecarui utilizator din baza de date, o sa resetez numarul de videouri
favorite la zero pentru a ne asigura ca avem numarul real de videouri favorite
la acest task. Sortez corespunzator videourile. Daca avem utilizatorul potrivit,
parcurgem lista sortata de videoclipuri si punem in obiectul json primul video
ce nu apare in istoric si care are un numar de favorite nenul.
- Search: Creez lista de videouri similar ca la Favorite. Inainte de a face
sortarea, elimin videoclipurile ce nu respecta genul cerut in input. Pentru
utilizatorul premium, parcurgem lista sortata de videoclipuri si in noua lista
result punem toate videoclipurile ce respecta cerinta.
