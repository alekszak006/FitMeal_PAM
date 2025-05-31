# FitMeal – Planer Posiłków i Licznik Kalorii

## Opis projektu
FitMeal to aplikacja mobilna stworzona w Kotlinie na platformę Android, służąca do planowania posiłków i liczenia kalorii. Umożliwia użytkownikowi dodawanie produktów, tworzenie planów posiłków oraz podgląd wartości odżywczych (kalorie, białko, tłuszcze, węglowodany).

Aplikacja posiada intuicyjny interfejs, wykorzystuje lokalną bazę danych do przechowywania produktów i planów oraz pozwala na łatwą nawigację pomiędzy ekranami.

---

## Podział obowiązków

Projekt realizowany przez dwie osoby:

| Osoba          | Zakres odpowiedzialności                                         |
|----------------|------------------------------------------------------------------|
| Aleks          | - Implementacja UI: ekrany dodawania produktów, listy produktów  |
|                | - Logika dodawania i edycji produktów                            |
|                | - Obsługa lokalnej bazy danych (DAO, encje)                      |
| Bartek         | - Ekrany planera posiłków i podsumowania                         |
|                | - Zarządzanie nawigacją i interakcją pomiędzy ekranami           |
|                | - Sumowanie wartości odżywczych i wyświetlanie statystyk         |

---

## Schemat nawigacji

1. **Ekran główny (MainActivity)**  
   - Przyciski do:  
     - Dodawanie produktu  
     - Lista produktów  
     - Planer posiłków

2. **Ekran dodawania produktu (AddProductFragment)**  
   - Formularz do wpisania nazwy, kalorii, białka, tłuszczów i węglowodanów  
   - Przycisk zapisania produktu do bazy

3. **Lista produktów (ProductListFragment)**  
   - Wyświetlanie listy dodanych produktów  
   - Możliwość wyboru lub edycji produktu

4. **Planer posiłków (MealPlannerFragment)**  
   - Możliwość wyboru produktów do posiłków  
   - Wyświetlanie sumarycznych wartości odżywczych dla zaplanowanych posiłków

---

## Baza danych

Do przechowywania danych wykorzystywana jest lokalna baza SQLite za pomocą Room.

### Główne encje:

- **Product**  
  - `id` (Int, primary key)  
  - `name` (String) – nazwa produktu  
  - `calories` (Int)  
  - `protein` (Float)  
  - `carbs` (Float)  
  - `fat` (Float)

- **MealPlan** (opcjonalnie, jeśli jest)  
  - `id` (Int, primary key)  
  - `name` (String) – nazwa planu  
  - lista produktów powiązana z planem

### Diagram bazy danych
+------------------+
| Product |
+------------------+
| id : Int (PK) |
| name : String |
| calories : Int |
| protein : Float |
| carbs : Float |
| fat : Float |
+------------------+

+------------------+
| MealPlan |
+------------------+
| id : Int (PK) |
| name : String |
+------------------+

(MealPlan) --- (n:m) --- (Product)
Relacja wiele do wielu (posiłki mają wiele produktów, produkty mogą być w wielu posiłkach)


