Bobocu Alexandra-Florentina, 321CA

Pentru a tine mai usor evidenta copiilor si cadourilor, am creat o baza de date
in care am aplicat pattern-ul Singleton.
In pachetul "database", am clasele care se ocupa de baza de date:<br>
-> in Helpers.java, am 2 metode corespunzatoare entitatilor pe care le modelez.
Ma folosesc de aceasta clasa pentru a adauga in baza de date informatiile
ce caracterizeaza aceste entitati. Tot in aceasta clasa apelez metoda
creata in Database.java menita sa goleasca baza de date.<br>
-> in Database.java, creez 2 noi liste in care adaug copiii si cadourile.
Tot in acest fisier golesc baza de date.

In pachetul "entities", am inclus clasele care refera entitatile ce urmeaza
a fi modelate.<br>
-> clasa Children.java contine informatiile referitoare la un copil, asa cum
sunt prezentate in enuntul temei.<br>
-> clasa SantaGifts.java contine informatiile referitoare la un cadou pentru
copil, asa cum sunt prezentate in enuntul temei.

Pachetul "model" contine clasele menite sa stocheze date.<br>
-> clasa InitialData.java cuprinde listele de copii si cadouri in faza initiala.
Cu ajutorul acestei clase, reusesc sa adaug in baza de date listele de copii si
cadouri, folosindu-ma de input.<br>
-> clasa AnnualChanges.java, asa cum se precizeaza in enunt, contine informatii
despre schimbarile ce au loc in fiecare an. In cadrul acestei clase, includ
noul buget al mosului, o lista cu noi cadouri, o lista cu noi copii si o lista
cu actualizari ale informatiilor pentru fiecare copil.<br>
-> clasa ChildUpdate.java cuprinde actualizarile pentru copil, in care am inclus
campuri referitoare la id, pentru identificarea copilului caruia modific
informatiile, la un nou scor de cumintenie si la noi preferinte de cadouri.<br>
-> clasa AnnualChildren.java este utila pentru clasa AnnualChildrenWrapper.java,
deoarece include informatiile necesare formatului de output. Este o clasa care
tine evidenta informatiilor legate de copil anual. Astfel, spre deosebire
de clasa Children.java din pachetul "entities", aceasta clasa contine o lista
de scoruri de cumintenie (istoricul) nu doar un scor de cumintenie; in plus
contine media scorurilor de cumintenie, bugetul asignat pentru cumpararea
cadourilor si lista cu cadourile primite.<br>
-> clasa AnnualChildrenWrapper.java incapsuleaza clasa AnnualChildren.java.
In cadrul constructorului, se creeaza o lista noua de copii cu toate
informatiile relevante pentru output.

Pachetul "input" contine clasele legate de formatul JSON input.<br>
-> clasa Input.java prezinta formatul de intrare. Contine campuri relevante
simularii scenariului de impartire a cadourilor: numarul de ani pe care se
implementeaza simularea, bugetul initial al mosului, referinta catre datele
initiale, prin care am acces la listele initiale de copii si cadouri. In plus,
am si o lista cu schimbarile ce au loc in fiecare an din cei numberOfYears ani.<br>
-> clasa JsonReader.java este cea care citeste din testele de intrare datele si
trece prin clase pentru a le popula cu informatiile citite.

Pachetul "output" contine clasele legate de formatul JSON output.<br>
-> clasa Output.java prezinta formatul de iesire. In cadrul acestei clase, ma
folosesc de clasa AnnualChildrenWrapper.java.<br>
-> clasa JsonWriter.java este cea care creeaza folderul de output in care sunt
testele de iesire sub forma JSON.

In clasa Main.java din pachetul "main" se trece prin toate testele de input si se
genereaza output-ul corespunzator. Din aceasta clasa se apeleaza metoda
startSimulation(), in cadrul careia se deschide folderul ce contine testele de
intrare. Testele sunt sortate crescator in functie de numarul testului.
Se itereaza prin fiecare test de intrare sub format JSON, se creeaza o instanta
a simularii propriu-zise, se completeaza clasa Output.java cu informatiile si
se scriu rezultatele finale corespunzatoare fiecarui test de intrare in
fisierele de iesire din folderul "output", sub format JSON.

Pachetul "calculator" contine clase de ajutor pentru calculul averageScore-ului,
dar si pentru actualizarea bugetului mosului.

*Am folosit design pattern-ul Strategy pentru calcularea averageScore-ului.
Astfel, calculez o medie a scorului specializata in functie de categoria de
varsta in care se incadreaza copilul: daca e bebelus, copil sau
adolescent, media se calculeaza intr-un anumit fel (in clasele BabyStrategy.java,
KidStrategy.java si TeenStrategy.java). Interfata SantaClausStrategy.java doar
defineste metoda de calcul a mediei scorurilor de cumintenie, aceasta urmand sa
fie implementata de clasele aferente. Cu ajutorul design pattern-ului Factory,
creez copilul de tipul categoriei de varsta potrivite, cu media calculata.
Daca copilul este young adult, se arunca o exceptie specifica.

-> clasa AgeCalculator.java se foloseste de clasa enum AgeCategory.java si
are scopul de a stabili categoria in care se incadreaza copilul: baby, kid,
teen, young adult in functie de varsta sa.<br>
-> in clasa SantaBudgetCalculator.java calculez bugetul pe care il aloca mosul
pentru fiecare copil. Am respectat formulele date in enunt.<br>
-> in clasa SantaGiftsDelivery.java am acces la lista de cadouri a mosului, la
lista de preferinte a copilului dar si la bugetul alocat de mos pentru cumpararea
cadourilor. In aceasta clasa, determin cadourile ce vor fi oferite copilului
pe baza unor criterii:
- cadoul sa apartina unei categorii dorite de copil dar sa fie si existent in
  lista mosului;
- cadoul sa fie cat mai ieftin si sa se incadreze in bugetul alocat.
  Astfel, in metoda getSantaGifts() parcurg lista de preferinte pentru cadouri
  a copilului si verific sa coincida cu categoriile de cadouri puse la
  dispozitie de mos. In acest sens, folosesc metoda suplimentara
  findGiftFromCategory() (se parcurge lista de cadouri a mosului si se returneaza
  o lista cu cadouri a caror categorie respecta atat preferinta copilului, cat si
  categoriile existente in lista mosului). Odata ce am strans mai multe cadouri
  dintr-o anumita categorie, il voi alege pe cel mai ieftin dintre ele. In acest
  sens, folosesc metoda suplimentara findCheapestGift() (de data aceasta se
  parcurge lista de cadouri a mosului, dar dintr-o anumita categorie. Folosesc
  variabila minPrice pentru retinerea pretului minim).
  Daca cadoul a fost gasit si pretul sau nu depaseste bugetul alocat, adaug cadoul
  in lista de cadouri si actualizez bugetul alocat pentru cumpararea cadourilor.

*Am creat si o clasa NoAverageScoreException.java in pachetul "exception";
in cazul in care copilul devine adult, se arunca o astfel de exceptie.
In pachetul "enums" existent in schelet am mai adaugat o clasa enum
AgeCategory.java, in care am inclus tipul copilului in functie de varsta lui.

Pachetul "simulation" contine clasa Simulation.java, cea care simuleaza intreg
scenariul de impartire a cadourilor.<br>
In aceasta clasa, am ca si campuri un obiect de tipul clasei Output.java
si o lista de copii, cu toate informatiile relevante pentru output. In
constructorul clasei, creez un obiect de tipul Output, in cadrul caruia, prin
metoda setAnnualChildren() creez o noua lista wrapper, ce va incapsula lista
annualChildrenList creata si ea la randul sau in constructorul Simulation().<br>
Simularea incepe odata cu metoda startSimulation(), in care populez baza de
date cu informatiile initiale, urmand sa apelez pe rand metodele round0() si
roundNumberOfYears(), relevante pentru flow-ul simularii. In cele din urma,
voi returna output-ul actualizat.

*Pe langa metodele aferente rundelor (round0() si roundNumberOfYears()), am
creat si alte metode ajutatoare:
- buildAnnualChildren() -> folosita in round0()
- getAverageSum() -> folosita in metodele aferente ambelor runde
- rewriteAnnualChildrenList() -> folosita in roundNumberOfYears()
- incrementAge() -> folosita in roundNumberOfYears()
- updateChildren() -> folosita in roundNumberOfYears()
- findAnnualChildrenById() -> folosita in metoda updateChildren(), utila
  rundei roundNumberOfYears()

*Pentru fiecare runda in parte: runda 0 si rundele care se desfasoara pe cei
numberOfYears ani, am urmat intocmai pasii descrisi in enunt.

~ Runda 0 ~<br>
Determin categoria de varsta pentru fiecare copil, deci parcurg
lista de copii din datele initiale. Pentru copiii care se incadreaza in
categoria de varsta a celor care primesc cadou, creez o noua lista care
retine scorul de cumintenie pentru fiecare copil si adaug in aceasta lista
scorul initial.
Urmeaza sa calculez averageScore-ul pentru fiecare copil din lista initiala,
tinand cont de varsta sa dar si de scorurile de cumintenie acumulate
(in aceasta runda un singur scor, acela initial). Pentru aceasta runda, am
creat o metoda buildAnnualChildren(), care imi initializeaza copilul cu
toate informatiile necesare pentru output: cu cele de baza specifice
clasei Children.java din pachetul "entities", cu istoricul de scoruri de
cumintenie si cu media scorurilor de cumintenie.

Se distribuie cadourile pentru copii. Parcurg lista annualChildrenList si
calculez bugetul pe care il aloca mosul pentru fiecare copil. In acest sens,
apelez metoda calculateSantaBudgetForChild() din clasa
SantaBudgetCalculator.java (pachetul "calculator") in care am nevoie de:
- bugetul initial al mosului care trebuie folosit la aceasta runda si de
  care fac rost din input;
- suma scorurilor average de la toți copiii din lista annualChildrenList
  (folosesc metoda getAverageSum() din clasa Simulation.java);
- media scorurilor de cumintenie specifica copilului.
		   
Dupa ce am stabilit bugetul calculat, caut cadourile potrivite pentru
copil si ma folosesc in acest sens de clasa SantaGiftsDelivery.java din
pachetul "calculator" pentru a obtine cadourile.

~ Rundele de pe parcursul celor numberOfYears ani ~<br>
Actualizez lista annualChildrenList cu ajutorul metodei
rewriteAnnualChildrenList(). Pentru fiecare an in parte, actualizez varstele
copiilor ramasi. Accesez lista de schimbari ce au loc in fiecare an din input si
apelez metoda updateChildren() pentru a actualiza informatiile despre copii. In
cadrul acestei metode ajutatoare, accesez lista cu actualizari pentru copii,
iterand prin lista cu un obiect de tip ChildUpdate. Pentru a gasi copilul la
care s-au produs schimbari, folosesc metoda findAnnualChildrenById(). Adaug un
nou scor de cumintenie in istoric si noi preferinte de cadouri in lista de
preferinte pe pozitia corecta. Cum in lista de preferinte pentru cadouri exista
duplicate, am grija sa le elimin, trecand preferintele pentru cadouri printr-un
Set, sub forma unei liste simplu inlantuite care nu imi permite sa am elemente
care se repeta.

Inlocuiesc bugetul vechi al mosului cu cel nou, adaug noi cadouri in lista de
cadouri a mosului si noi copii in lista de copii.
Pentru fiecare copil din lista annualChildrenList, calculez averageScore-ul,
folosind strategiile create in pachetul "calculator".

Se distribuie cadourile pentru copii, similar ca la runda 0 si actualizez
output-ul.
