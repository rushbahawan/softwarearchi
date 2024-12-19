package view;

import model.queueofcustomer;
import model.parcelmapp;
import model.log; // Import the log class
import controller.worker;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public class gui {
    private JFrame frame;
    private JTextArea customerQueueText;
    private JTextArea parcelListText;
    private JTextArea currentParcelText; // New text area for current parcel
    private queueofcustomer customerQueue;
    private parcelmapp parcelMap;
    private worker workerInstance; // Store the worker instance

    // Updated constructor to accept worker as a third argument
    public gui(queueofcustomer customerQueue, parcelmapp parcelMap, worker workerInstance) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.workerInstance = workerInstance; // Store the worker instance

        initialize();
    }

    private void initialize() {
        // Frame Initialization
        frame = new JFrame("Depot Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Text Areas for Display
        customerQueueText = new JTextArea("Customer Queue:\n");
        customerQueueText.setEditable(false);

        parcelListText = new JTextArea("Parcel List:\n");
        parcelListText.setEditable(false);

        currentParcelText = new JTextArea("Current Parcel:\nNone"); // Initialize with "None"
        currentParcelText.setEditable(false);

        // Scroll Panes for Text Areas
        JScrollPane customerScrollPane = new JScrollPane(customerQueueText);
        JScrollPane parcelScrollPane = new JScrollPane(parcelListText);
        JScrollPane currentParcelScrollPane = new JScrollPane(currentParcelText);

        // Panels for Data Display
        JPanel dataPanel = new JPanel(new GridLayout(1, 3));
        dataPanel.add(customerScrollPane);
        dataPanel.add(parcelScrollPane);
        dataPanel.add(currentParcelScrollPane);

        // Buttons for Actions
        JButton processCustomerButton = new JButton("Process Customer");
        JButton addCustomerButton = new JButton("Add Customer");
        JButton addParcelButton = new JButton("Add Parcel");
        JButton sortParcelsButton = new JButton("Sort Parcels");

        // Action Listeners for Buttons
        processCustomerButton.addActionListener(e -> processCustomer());
        addCustomerButton.addActionListener(e -> addAllCustomersFromCSV());
        addParcelButton.addActionListener(e -> addParcel());
        sortParcelsButton.addActionListener(e -> sortParcels());

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(processCustomerButton);
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(addParcelButton);
        buttonPanel.add(sortParcelsButton);

        // Add Components to Frame
        frame.add(dataPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the Frame
        frame.setVisible(true);

        // Initial GUI Update
        updateCustomerQueueDisplay();
        updateParcelListDisplay();
    }

    private void processCustomer() {
        Queue<String> queue = customerQueue.getCustomerQueue();
        Map<String, Double> parcels = parcelMap.getParcelMap();

        if (!queue.isEmpty() && !parcels.isEmpty()) {
            String customer = queue.poll(); // Get the first customer
            String parcelId = parcels.keySet().iterator().next(); // Get the first parcel
            double parcelWeight = parcels.remove(parcelId); // Remove the parcel from the map

            // Use worker instance if needed for fee calculation (extend functionality here)

            // Write to the log using Singleton Log class
            log.getInstance().writeLog("Processed Customer: " + customer +
                    ", Parcel ID: " + parcelId +
                    ", Weight: " + parcelWeight);

            // Display Current Parcel Being Processed
            currentParcelText.setText("Current Parcel:\nParcel ID: " + parcelId + "\nWeight: " + parcelWeight);

            // Update the GUI
            updateCustomerQueueDisplay();
            updateParcelListDisplay();

            JOptionPane.showMessageDialog(frame, "Processed Customer: " + customer +
                    "\nParcel ID: " + parcelId +
                    "\nWeight: " + parcelWeight);
        } else {
            JOptionPane.showMessageDialog(frame, "No customers or parcels to process!");
        }
    }

    private void addAllCustomersFromCSV() {
        // Add your implementation to load customers from CSV
    }

    private void addParcel() {
        // Add your implementation to add parcels
    }

    private void sortParcels() {
        // Add your implementation for sorting parcels
    }

    private void updateCustomerQueueDisplay() {
        StringBuilder display = new StringBuilder("Customer Queue:\n");
        for (String customer : customerQueue.getCustomerQueue()) {
            display.append(customer).append("\n");
        }
        customerQueueText.setText(display.toString());
    }

    private void updateParcelListDisplay() {
        StringBuilder display = new StringBuilder("Parcel List:\n");
        for (Map.Entry<String, Double> entry : parcelMap.getParcelMap().entrySet()) {
            display.append("Parcel ID: ").append(entry.getKey())
                    .append(", Weight: ").append(entry.getValue()).append("\n");
        }
        parcelListText.setText(display.toString());
    }
}


//package view;
//
//import model.queueofcustomer;
//import model.parcelmapp;
//import controller.worker;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.*;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class gui {
//    private JFrame frame;
//    private JTextArea customerQueueText;
//    private JTextArea parcelListText;
//    private JTextArea currentParcelText; // New text area for current parcel
//    private queueofcustomer customerQueue;
//    private parcelmapp parcelMap;
//
//    public gui(queueofcustomer customerQueue, parcelmapp parcelMap, worker worker) {
//        this.customerQueue = customerQueue;
//        this.parcelMap = parcelMap;
//
//        initialize();
//    }
//
//    private void initialize() {
//        // Frame Initialization
//        frame = new JFrame("Depot Management System");
//        frame.setSize(900, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//
//        // Text Areas for Display
//        customerQueueText = new JTextArea("Customer Queue:\n");
//        customerQueueText.setEditable(false);
//
//        parcelListText = new JTextArea("Parcel List:\n");
//        parcelListText.setEditable(false);
//
//        currentParcelText = new JTextArea("Current Parcel:\nNone"); // Initialize with "None"
//        currentParcelText.setEditable(false);
//
//        // Scroll Panes for Text Areas
//        JScrollPane customerScrollPane = new JScrollPane(customerQueueText);
//        JScrollPane parcelScrollPane = new JScrollPane(parcelListText);
//        JScrollPane currentParcelScrollPane = new JScrollPane(currentParcelText);
//
//        // Panels for Data Display
//        JPanel dataPanel = new JPanel(new GridLayout(1, 3));
//        dataPanel.add(customerScrollPane);
//        dataPanel.add(parcelScrollPane);
//        dataPanel.add(currentParcelScrollPane);
//
//        // Buttons for Actions
//        JButton processCustomerButton = new JButton("Process Customer");
//        JButton addCustomerButton = new JButton("Add Customer");
//        JButton addParcelButton = new JButton("Add Parcel");
//        JButton saveDataButton = new JButton("Save Data");
//        JButton sortParcelsButton = new JButton("Sort Parcels");
//
//        // Action Listeners for Buttons
//        processCustomerButton.addActionListener(e -> processCustomer());
//        addCustomerButton.addActionListener(e -> addAllCustomersFromCSV());
//        addParcelButton.addActionListener(e -> addParcel());
//        saveDataButton.addActionListener(e -> saveData());
//        sortParcelsButton.addActionListener(e -> sortParcels());
//
//        // Button Panel
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(processCustomerButton);
//        buttonPanel.add(addCustomerButton);
//        buttonPanel.add(addParcelButton);
//        buttonPanel.add(saveDataButton);
//        buttonPanel.add(sortParcelsButton);
//
//        // Add Components to Frame
//        frame.add(dataPanel, BorderLayout.CENTER);
//        frame.add(buttonPanel, BorderLayout.SOUTH);
//
//        // Display the Frame
//        frame.setVisible(true);
//
//        // Initial GUI Update
//        updateCustomerQueueDisplay();
//        updateParcelListDisplay();
//    }
//
//    private void addAllCustomersFromCSV() {
//        try (BufferedReader br = new BufferedReader(new FileReader("src/data/Custs.csv"))) {
//            String line;
//            br.readLine(); // Skip the header
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length > 1) {
//                    String customerName = parts[1].trim();
//                    customerQueue.getCustomerQueue().add(customerName); // Add directly to the queue
//                }
//            }
//            updateCustomerQueueDisplay(); // Update the GUI to show all customers
//            JOptionPane.showMessageDialog(frame, "All customers from Custs.csv have been added.");
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(frame, "Error loading customers from CSV: " + e.getMessage());
//        }
//    }
//
//    private void processCustomer() {
//        Queue<String> queue = customerQueue.getCustomerQueue();
//        Map<String, Double> parcels = parcelMap.getParcelMap();
//
//        if (!queue.isEmpty() && !parcels.isEmpty()) {
//            String customer = queue.poll(); // Get the first customer
//            String parcelId = parcels.keySet().iterator().next(); // Get the first parcel
//            double parcelWeight = parcels.remove(parcelId); // Remove the parcel from the map
//
//            // Log processed data to "src/data/ProcessedData.txt"
//            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter("src/data/ProcessedData.txt", true))) {
//                logWriter.write("Processed Customer: " + customer +
//                        ", Parcel ID: " + parcelId +
//                        ", Weight: " + parcelWeight + "\n");
//            } catch (IOException e) {
//                JOptionPane.showMessageDialog(frame, "Error writing to log file: " + e.getMessage());
//            }
//
//            // Display Current Parcel Being Processed
//            currentParcelText.setText("Current Parcel:\nParcel ID: " + parcelId + "\nWeight: " + parcelWeight);
//
//            // Update the GUI
//            updateCustomerQueueDisplay();
//            updateParcelListDisplay();
//
//            JOptionPane.showMessageDialog(frame, "Processed Customer: " + customer +
//                    "\nParcel ID: " + parcelId +
//                    "\nWeight: " + parcelWeight);
//        } else {
//            JOptionPane.showMessageDialog(frame, "No customers or parcels to process!");
//        }
//    }
//
//    private void addParcel() {
//        String parcelId = JOptionPane.showInputDialog(frame, "Enter Parcel ID:");
//        if (parcelMap.getParcelMap().containsKey(parcelId)) {
//            JOptionPane.showMessageDialog(frame, "Parcel ID already exists!");
//            return;
//        }
//        String weightStr = JOptionPane.showInputDialog(frame, "Enter Parcel Weight (kg):");
//
//        try {
//            double weight = Double.parseDouble(weightStr);
//            parcelMap.getParcelMap().put(parcelId, weight);
//            updateParcelListDisplay();
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(frame, "Invalid weight entered!");
//        }
//    }
//
//    private void saveData() {
//        try (BufferedWriter customerWriter = new BufferedWriter(new FileWriter("src/data/Custs.csv"));
//             BufferedWriter parcelWriter = new BufferedWriter(new FileWriter("src/data/Parcels.csv"))) {
//
//            // Write Customer Queue
//            customerWriter.write("CustomerID,Name\n");
//            int id = 1;
//            for (String customer : customerQueue.getCustomerQueue()) {
//                customerWriter.write(id++ + "," + customer + "\n");
//            }
//
//            // Write Parcel List
//            parcelWriter.write("ParcelID,Weight\n");
//            for (Map.Entry<String, Double> entry : parcelMap.getParcelMap().entrySet()) {
//                parcelWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
//            }
//
//            JOptionPane.showMessageDialog(frame, "Data saved successfully!");
//
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(frame, "Error saving data: " + e.getMessage());
//        }
//    }
//
//    private void sortParcels() {
//        Map<String, Double> sortedParcels = parcelMap.getParcelMap().entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue())
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (e1, e2) -> e1,
//                        LinkedHashMap::new
//                ));
//
//        parcelMap.getParcelMap().clear();
//        parcelMap.getParcelMap().putAll(sortedParcels);
//        updateParcelListDisplay();
//        JOptionPane.showMessageDialog(frame, "Parcels sorted by weight!");
//    }
//
//    private void updateCustomerQueueDisplay() {
//        StringBuilder display = new StringBuilder("Customer Queue:\n");
//        for (String customer : customerQueue.getCustomerQueue()) {
//            display.append(customer).append("\n");
//        }
//        customerQueueText.setText(display.toString());
//    }
//
//    private void updateParcelListDisplay() {
//        StringBuilder display = new StringBuilder("Parcel List:\n");
//        for (Map.Entry<String, Double> entry : parcelMap.getParcelMap().entrySet()) {
//            display.append("Parcel ID: ").append(entry.getKey())
//                    .append(", Weight: ").append(entry.getValue()).append("\n");
//        }
//        parcelListText.setText(display.toString());
//    }
//}
