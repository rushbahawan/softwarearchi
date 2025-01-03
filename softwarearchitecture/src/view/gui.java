package view;

// libraries
import model.queueofcustomer;
import model.parcelmapp;
import model.parcel;
import model.log; // Import the log class
import controller.worker;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    // Initialise GUI models
    public gui(queueofcustomer customerQueue, parcelmapp parcelMap, worker workerInstance) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.workerInstance = workerInstance; // Store the worker instance

        initialize();
    }

    // GUI components
    private void initialize() {
        // Frame
        frame = new JFrame("Depot Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Text areas
        customerQueueText = new JTextArea("Customer Queue:\n");
        customerQueueText.setEditable(false);

        parcelListText = new JTextArea("Parcel List:\n");
        parcelListText.setEditable(false);

        currentParcelText = new JTextArea("Current Parcel:\nNone"); // Initialize with "None"
        currentParcelText.setEditable(false);

        // Scrolling
        JScrollPane customerScrollPane = new JScrollPane(customerQueueText);
        JScrollPane parcelScrollPane = new JScrollPane(parcelListText);
        JScrollPane currentParcelScrollPane = new JScrollPane(currentParcelText);

        // Data Display panels
        JPanel dataPanel = new JPanel(new GridLayout(1, 3));
        dataPanel.add(customerScrollPane);
        dataPanel.add(parcelScrollPane);
        dataPanel.add(currentParcelScrollPane);

        // Buttons
        JButton processCustomerButton = new JButton("Process Customer");
        JButton addCustomerButton = new JButton("Add Customer");
        JButton addParcelButton = new JButton("Add Parcel");
        JButton sortParcelsButton = new JButton("Sort Parcels");
        JButton loadParcelsButton = new JButton("Load Parcels");
        processCustomerButton.addActionListener(e -> processCustomer());
        addCustomerButton.addActionListener(e -> addAllCustomersFromCSV());
        addParcelButton.addActionListener(e -> addParcel());
        sortParcelsButton.addActionListener(e -> sortParcels());
        loadParcelsButton.addActionListener(e -> loadParcelsFromCSV());

        // Navigation bar
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(processCustomerButton);
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(addParcelButton);
        buttonPanel.add(sortParcelsButton);
        buttonPanel.add(loadParcelsButton);

        // Frame components
        frame.add(dataPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        // Add initial data
        updateCustomerQueueDisplay();
        updateParcelListDisplay();
    }

    // Process customer and assign parcel
    private void processCustomer() {
        Queue<String> queue = customerQueue.getCustomerQueue();
        Map<String, parcel> parcels = parcelMap.getParcelMap();
        if (!queue.isEmpty() && !parcels.isEmpty()) {
            String customer = queue.poll(); // Get the first customer
            String parcelId = parcels.keySet().iterator().next(); // Get the first parcel
            parcel parcel = parcels.remove(parcelId); // Remove the parcel from the map

            // Calculate the number of days in the depot
            long daysInDepot = parcel.getDaysInDepot();

            // Use worker instance for fee calculation
            double fee = workerInstance.calculateFee(parcel.getWeight());
            log.getInstance().writeLog("Fee calculated for Customer: " + customer + ", Fee: " + fee);

            // Write to the log using Singleton Log class
            log.getInstance().writeLog("Processed Customer: " + customer +
                    ", Parcel ID: " + parcelId +
                    ", Weight: " + parcel.getWeight() +
                    ", Days in Depot: " + daysInDepot);

            // Display current parcel being processed
            currentParcelText.setText("Current Parcel:\nParcel ID: " + parcelId +
                    "\nWeight: " + parcel.getWeight() +
                    "\nDays in Depot: " + daysInDepot);

            // Update the GUI
            updateCustomerQueueDisplay();
            updateParcelListDisplay();

            // Confirmation message
            JOptionPane.showMessageDialog(frame, "Processed Customer: " + customer +
                    "\nParcel ID: " + parcelId +
                    "\nWeight: " + parcel.getWeight() +
                    "\nDays in Depot: " + daysInDepot +
                    "\nFee: " + fee);
        } else {
            JOptionPane.showMessageDialog(frame, "No customers or parcels to process!");
        }
    }

    // Load customers from CSV
    private void addAllCustomersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/Custs.csv"))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    customerQueue.getCustomerQueue().add(parts[1].trim());
                }
            }
            JOptionPane.showMessageDialog(frame, "All customers from CSV added to the queue!");
            updateCustomerQueueDisplay();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading customers from CSV: " + e.getMessage());
        }
    }

    // Load parcels from CSV
    private void loadParcelsFromCSV() {
        String filePath = JOptionPane.showInputDialog(frame, "Enter the file path for parcels CSV:");
        if (filePath != null && !filePath.isEmpty()) {
            parcelMap.loadParcels(filePath);
            updateParcelListDisplay();
            JOptionPane.showMessageDialog(frame, "Parcels loaded successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid file path!");
        }
    }

    // Add parcel
    private void addParcel() {
        String parcelId = JOptionPane.showInputDialog(frame, "Enter Parcel ID:");
        String weightStr = JOptionPane.showInputDialog(frame, "Enter Parcel Weight (kg):");
        try {
            double weight = Double.parseDouble(weightStr);
            parcel newParcel = new parcel(parcelId, weight); // Create a parcel object
            parcelMap.getParcelMap().put(parcelId, newParcel); // Add the parcel
            JOptionPane.showMessageDialog(frame, "Parcel added successfully!");
            updateParcelListDisplay();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid weight entered!");
        }
    }

    // Sort parcels
    private void sortParcels() {
        Map<String, parcel> sortedParcels = parcelMap.getParcelMap().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingDouble(parcel::getWeight)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        parcelMap.getParcelMap().clear();
        parcelMap.getParcelMap().putAll(sortedParcels);
        JOptionPane.showMessageDialog(frame, "Parcels sorted by weight!");
        updateParcelListDisplay();
    }

    // Update customer queue display
    private void updateCustomerQueueDisplay() {
        StringBuilder display = new StringBuilder("Customer Queue:\n");
        for (String customer : customerQueue.getCustomerQueue()) {
            display.append(customer).append("\n");
        }
        customerQueueText.setText(display.toString());
    }

    // Update parcel list display
    private void updateParcelListDisplay() {
        StringBuilder display = new StringBuilder("Parcel List:\n");
        for (Map.Entry<String, parcel> entry : parcelMap.getParcelMap().entrySet()) {
            parcel parcel = entry.getValue();
            display.append("Parcel ID: ").append(parcel.getParcelId())
                    .append(", Weight: ").append(parcel.getWeight())
                    .append(", Days in Depot: ").append(parcel.getDaysInDepot()).append("\n");
        }
        parcelListText.setText(display.toString());
    }
}
