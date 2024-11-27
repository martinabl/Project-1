import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    //vytvoření tří konstrukturů//

    //1.konstruktor//

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException{
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //2.konstruktor//

    public Plant(String name) throws PlantException {
        this.name = name;
        this.notes = "";
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = 0;
    }

    //3.konstruktor//

    public Plant(String name, int defaultFrequency) throws PlantException {
        this.name = name;
        this.notes = "";
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = defaultFrequency;
    }

    //Getter and setter//


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? "" : notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        if (planted == null || planted.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Planted date cannot be in the future or null");
        }
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    // Metoda setWatering, která zkontroluje datum poslední zálivky
    public void settWatering(LocalDate Watering) throws PlantException {
        if (watering == null || watering.isBefore(planted)) {
            throw new PlantException("Last watering date cannot be before the planted date.");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Watering frequency must be greater than zero.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    // Pomocná metoda pro výpočet další zálivky
    public LocalDate nextWateringDate() {
        return watering.plusDays(frequencyOfWatering);

    }

// Metoda toString pro přehledný výpis informací
@Override
public String toString() {
    return "Plant{" +
            "name='" + name + '\'' +
            ", notes='" + notes + '\'' +
            ", planted=" + planted +
            ", lastWatering=" + watering +
            ", wateringFrequencyDays=" + frequencyOfWatering +
            '}';
}

//metoda getWateringInfo()//

    public String getWateringInfo() {
        return "Plant name:" + name +
                "Last Watering:" + watering +
                "Next recommended watering:" + nextWateringDate();
    }

//metoda doWateringNow()//

public void doWateringNow() {
        this.watering = LocalDate.now();
}


}




