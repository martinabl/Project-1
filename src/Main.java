import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlantListManager manager = new PlantListManager();

        //1.načtení seznamu květin ze souboru kvetiny.txt//

        System.out.println("Načítání ze souboru kvetiny.txt:");
        manager.loadFromFile("C:\\Users\\kokot\\IdeaProjects\\Project-1\\src\\kvetiny.txt");

        //2.výpis informací o zálivce pro všechny květiny//

        System.out.println("\nInformace o zálivce:");
        for (Plant plant : manager.getPlantListCopy()) {
            System.out.println(plant.getWateringInfo());
        }

        //3.přidání nové květiny//

        System.out.println("\nPřidávání nové květiny:");
        try {
            manager.addPlant(new Plant("Orchidej", "Bílá orchidej", LocalDate.now(), LocalDate.now(), 10));
        } catch (PlantException e) {
            System.err.println("Chyba při přidávání rostliny: " + e.getMessage());
        }

        //4.přidání 10 rostlin "Tulipán na prodej 1" až "Tulipán na prodej 10"
                for (int i = 1; i <= 10; i++) {
                    try {
                        manager.addPlant(new Plant("Tulipán na prodej " + i, "K prodeji", LocalDate.now(), LocalDate.now(), 14));
                    } catch (PlantException e) {
                        System.err.println("Chyba při přidávání tulipánů: " + e.getMessage());
                    }
                }

        //5.odebrání květiny na třetí pozici //
        System.out.println("\nOdebírání květiny na třetí pozici:");
        manager.removePlant(2);

        //6.uložení seznamu do nového souboru//

        String newFileName = "kvetiny-aktualizovane.txt";
        System.out.println("\nUkládání seznamu do souboru " + newFileName);
        manager.saveToFile(newFileName);

        //7.opětovné načtení ze souboru//

        System.out.println("\nOpětovné načtení ze souboru " + newFileName);
        manager.loadFromFile(newFileName);

        //8.výpis seznamu rostlin po opětovném načtení//

        System.out.println("\nSeznam květin po načtení:");
        for (Plant plant : manager.getPlantListCopy()) {
            System.out.println(plant);
        }

        //9.seřazení rostlin podle různých kritérií//

        System.out.println("\nŘazení podle názvu:");
        manager.sortPlantsByName();
        for (Plant plant : manager.getPlantListCopy()) {
            System.out.println(plant);
        }

        System.out.println("\nŘazení podle data poslední zálivky:");
        manager.sortPlantsByWatering();
        for (Plant plant : manager.getPlantListCopy()) {
            System.out.println(plant);
        }
    }


    }