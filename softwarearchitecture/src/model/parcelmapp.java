package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class parcelmapp {
    private Map<String, parcel> parcelMap; // Map of Parcel ID to Parcel object
// parcelmapp as hashmap
    public parcelmapp() {
        parcelMap = new HashMap<>();
    }
// retriveing map of parcels
    public Map<String, parcel> getParcelMap() {
        return parcelMap;
    }
    // add parcel to map
    public void addParcel(String parcelId, double weight) {
        parcel newParcel = new parcel(parcelId, weight);
        parcelMap.put(parcelId, newParcel);
    }
    // remove parcel from map by id
    public parcel removeParcel(String parcelId) {
        return parcelMap.remove(parcelId);
    }
    //get parcel by id
    public parcel getParcel(String parcelId) {
        return parcelMap.get(parcelId);
    }

    // load parcels from a CSV file
    public void loadParcels(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String parcelId = parts[0].trim();
                    double weight = Double.parseDouble(parts[1].trim());
                    addParcel(parcelId, weight); // Add parcel to the map
                }
            }
            System.out.println("Parcels loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading parcels from CSV: " + e.getMessage());// error handling
        } catch (NumberFormatException e) {
            System.err.println("Invalid data format in CSV: " + e.getMessage());
        }
    }
    //tosring method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Parcels:\n");
        for (Map.Entry<String, parcel> entry : parcelMap.entrySet()) {
            sb.append("Parcel ID: ").append(entry.getKey())
                    .append(", Weight: ").append(entry.getValue().getWeight()).append("\n");
        }
        return sb.toString();
    }
}
