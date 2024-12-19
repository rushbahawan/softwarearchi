import model.queueofcustomer;
import model.parcelmapp;
import controller.worker;
import view.gui;

import javax.swing.*;
import java.util.Map;

public class TestSuite {

    public static void main(String[] args) {
        System.out.println("Running Test Suite...");

        // Step 1: Test queueofcustomer functionality
        testQueueOfCustomer();

        // Step 2: Test parcelmapp functionality
        testParcelMap();

        // Step 3: Test GUI integration and functionality
        testGUI();

        System.out.println("Test Suite Completed.");
    }

    private static void testQueueOfCustomer() {
        System.out.println("\n--- Testing queueofcustomer ---");

        // Initialize queueofcustomer
        queueofcustomer customerQueue = new queueofcustomer();

        // Test adding customers
        customerQueue.getCustomerQueue().add("Alice");
        customerQueue.getCustomerQueue().add("Bob");
        customerQueue.getCustomerQueue().add("Charlie");
        System.out.println("Customers added to queue: Alice, Bob, Charlie");

        // Test removing a customer
        String removedCustomer = customerQueue.getCustomerQueue().poll();
        System.out.println("Removed customer: " + removedCustomer);

        // Display remaining customers
        System.out.println("Remaining customers in queue: " + customerQueue.getCustomerQueue());
    }

    private static void testParcelMap() {
        System.out.println("\n--- Testing parcelmapp ---");

        // Initialize parcelmapp
        parcelmapp parcelMap = new parcelmapp();

        // Test adding parcels
        parcelMap.getParcelMap().put("P001", 12.5);
        parcelMap.getParcelMap().put("P002", 8.0);
        parcelMap.getParcelMap().put("P003", 15.3);
        System.out.println("Parcels added: P001 (12.5kg), P002 (8.0kg), P003 (15.3kg)");

        // Test removing a parcel
        Double removedParcelWeight = parcelMap.getParcelMap().remove("P002");
        System.out.println("Removed parcel: P002, Weight: " + removedParcelWeight);

        // Display remaining parcels
        for (Map.Entry<String, Double> entry : parcelMap.getParcelMap().entrySet()) {
            System.out.println("Parcel ID: " + entry.getKey() + ", Weight: " + entry.getValue());
        }
    }

    private static void testGUI() {
        System.out.println("\n--- Testing GUI Integration ---");

        // Initialize required components for GUI
        queueofcustomer customerQueue = new queueofcustomer();
        parcelmapp parcelMap = new parcelmapp();
        worker workerInstance = new worker();

        // Add sample data for testing
        customerQueue.getCustomerQueue().add("Alice");
        customerQueue.getCustomerQueue().add("Bob");
        customerQueue.getCustomerQueue().add("Charlie");

        parcelMap.getParcelMap().put("P001", 10.5);
        parcelMap.getParcelMap().put("P002", 7.3);

        // Launch GUI
        System.out.println("Launching GUI...");
        SwingUtilities.invokeLater(() -> new gui(customerQueue, parcelMap, workerInstance));
    }
}
