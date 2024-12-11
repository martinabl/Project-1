import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlantListManager {
    private List<Plant> plants;

    //konstruktor//

    public PlantListManager() {
        plants = new ArrayList<>();
    }

    //přidání nové květiny//

    public void addPlant(Plant plant) {
        plants.add(plant);
        sortPlantsByName();
    }

    //získání květiny na zadaném indexu//

    public Plant getPlant(int index) {
        if ( (index < 0 || index >= plants.size())) {
            throw new IndexOutOfBoundsException("Invalid index for plant list");
        }
        return plants.get(index);
    }

    //odebrání květiny ze seznamu//

    public void removePlant(int index) {
        if (index < 0 || index >= plants.size()) {
            throw new IndexOutOfBoundsException("Invalid index for plant list");
        }
        plants.remove(index);
    }

    //získání kopie seznamu květin//


    public List<Plant> getPlantListCopy() {
        return new ArrayList<>(plants);
    }

    //vrací seznam rostlin, které je třeba zalít//


    public List<Plant> getPlantsNeedingWater() {
        return plants.stream()
                .filter(plant -> plant.getWatering().plusDays(plant.getFrequencyOfWatering()).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    // Metoda pro export seznamu rostlin do souboru

    public void exportToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(plants);
        }
    }

    // Metoda pro import seznamu rostlin ze souboru

    public void importFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            plants = (List<Plant>) ois.readObject();
        }
    }

    // Řazení podle názvu rostlin (výchozí řazení)
    public void sortPlantsByName() {
        plants.sort(Comparator.comparing(Plant::getName));
    }

    // Řazení podle data poslední zálivky
    public void sortPlantsByWatering() {
        plants.sort(Comparator.comparing(Plant::getWatering));
    }

    public void loadFromFile(String fileName) {
        plants.clear(); // Při načítání nového seznamu vyprázdnit
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                try {
                    String name = data[0];
                    String notes = data[1].isEmpty() ? "" : data[1];
                    int frequency = Integer.parseInt(data[2]);
                    LocalDate planted = LocalDate.parse(data[3]);
                    LocalDate watering = LocalDate.parse(data[4]);
                    Plant plant = new Plant(name, notes, planted, watering, frequency);
                    plants.add(plant);
                } catch (NumberFormatException e) {
                    System.err.println("Chybná frekvence zálivky v souboru: " + line);
                } catch (Exception e) {
                    System.err.println("Chybný formát dat v souboru: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Chyba při načítání souboru: " + fileName);
        }
    }

    // Uložení seznamu květin do souboru

    public void saveToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Plant plant : plants) {
                bw.write(String.format("%s\t%s\t%d\t%s\t%s%n",
                        plant.getName(),
                        plant.getNotes(),
                        plant.getFrequencyOfWatering(),
                        plant.getPlanted(),
                        plant.getWatering()));
            }
        } catch (IOException e) {
            System.err.println("Chyba při ukládání do souboru: " + fileName);
        }
    }
}

