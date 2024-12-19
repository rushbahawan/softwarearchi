package model;

import java.io.*;
import java.util.HashMap;

public class parcelmapp {
    private HashMap<String, Double> parcelMap;

    public parcelmapp() {
        this.parcelMap = new HashMap<>();
    }

    public void loadParcels(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    System.err.println("Invalid data in row: " + line + " (Insufficient columns)");
                    continue;
                }
                String parcelId = parts[0].trim();
                try {
                    // Validate ParcelID (allow non-numeric IDs)
                    if (!parcelId.matches("[A-Za-z0-9]+")) {
                        System.err.println("Invalid ParcelID format in row: " + line);
                        continue;
                    }

                    // Parse weight
                    double weight = Double.parseDouble(parts[1].trim());
                    parcelMap.put(parcelId, weight);

                } catch (NumberFormatException e) {
                    System.err.println("Invalid weight in row: " + line);
                }
            }
            System.out.println("Parcels loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading Parcels.csv: " + e.getMessage());
        }
    }

    public HashMap<String, Double> getParcelMap() {
        return parcelMap;
    }
}


//package model;
//
//import java.io.*;
//import java.util.ArrayList;
//
//public class parcelmapp {
//    private ArrayList<String> parcelList = new ArrayList<>();
//
//    public void loadParcels(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            br.readLine(); // Skip header
//            while ((line = br.readLine()) != null) {
//                parcelList.add(line);
//            }
//            System.out.println("Parcels loaded successfully.");
//        } catch (IOException e) {
//            System.err.println("Error loading parcels: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public String toString() {
//        return String.join("\n", parcelList);
//    }
//}
