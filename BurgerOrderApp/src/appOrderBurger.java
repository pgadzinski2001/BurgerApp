// Patryk Gadziński - 74978 - INLS2
// Implementuje interfejs "List", który pozwala go używać jako lista
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// inicjuje i uruchamia zamawianie burgerów poprzez utworzenie obiektu BurgerControllerOrder i wywołanie odpowiednich metod.
public class appOrderBurger {
    public static void main(String[] args) {
        controllerOrder controllerOrder = new BurgerControllerOrder();
        controllerOrder.processOrder();
    }
}

// Abstrakcyjna klasa bazowa dla burgerów - bez implementacji w klasie lub w interfejsie
abstract class Burger {
    // Deklaracja prywatnych pól klasy "Burger"
    private final String name;
    private final double price;

    // Definiuje konstruktor dla klasy "Burger"
    public Burger(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Definiuje metodę w klasie, by zwracała wartość pola "name"
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Metoda abstrakcyjna do pobierania opisu burgera.
    public abstract String getDescp();
}

// Klasa burgera classicBurger dziedzicząca po Burger
class classicBurger extends Burger {
    public classicBurger() {
        super("Klasyczny Burger", 20.99);
    }

    // Metoda zwraca opis do classicBurger
    @Override
    public String getDescp() {
        return "Grilowany kotlet wołowy z sałatą, pomidorem oraz karmelizowaną cebulą.";
    }
}

// Klasa burgera Cheeseburger dziedzicząca po Burger
class Cheeseburger extends Burger {
    public Cheeseburger() {
        super("Cheeseburger", 22.99); // typy tu występują ponieważ klasa CheeseBurger dziedziczy po Burger
    }

    // Metoda zwraca opis do cheeseBurger
    @Override
    public String getDescp() {
        return "Burger klasyczny z dodatkiem topionego sera cheddar";
    }
}

// Klasa burgera WegeBurger dziedzicząca po Burger
class WegeBurger extends Burger {
    public WegeBurger() {
        super("Wege Burger", 24.99);
    }

    @Override
    public String getDescp() {
        return "Grilowany kotlet wegetariański z sałatą, pomidorem oraz karmelizowaną cebulą.";
    }
}

// Interfejs dla kontrolera zamówienia
interface controllerOrder {
    void processOrder(); // Metoda do obsługi procesu zamawiania
}

// Klasa implementująca interfejs controllerOrder - Obsługuje zamówienia na burgery
class BurgerControllerOrder implements controllerOrder {
    private final List<Burger> burgers;
    private double finalPrice;

    public BurgerControllerOrder() {
        burgers = new ArrayList<>();
        finalPrice = 0;
    }

    //Metoda obsługująca proces składania zamówienia
    @Override
    public void processOrder() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBurger(new classicBurger());
                    break;
                case 2:
                    addBurger(new Cheeseburger());
                    break;
                case 3:
                    addBurger(new WegeBurger());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        } while (choice != 0);

        //Wyświetla zamówienie użytkownika oraz łączną kwotę zamówienia.
        System.out.println("Twoje zamówienie:");
        for (Burger burger : burgers) {
            System.out.println(burger.getName() + " - " + burger.getDescp() + " " + burger.getPrice() + " zł");
        }
        System.out.println("Łączna cena: " + getLastPrice() + " zł");
    }

    //Dodaje burger do zamówienia
    public void addBurger(Burger burger) {
        burgers.add(burger);
        finalPrice += burger.getPrice();
    }

    // Zwraca ostatnią łączną kwotę zamówienia.
    public double getLastPrice() {
        return finalPrice;
    }

    // Wyświetla menu które jest widoczne dla uzytkownika.
    public void showMenu() {
        System.out.println("MENU:");
        System.out.println("1. Klasyczny Burger");
        System.out.println("2. Cheeseburger");
        System.out.println("3. Wege Burger");
        System.out.println("0. Zakończ zamówienie");
        System.out.println("Wybierz numer, aby dodać burger do zamówienia:");
    }
}



