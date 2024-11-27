import java.time.LocalDate;
import java.util.ArrayList;
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
}
