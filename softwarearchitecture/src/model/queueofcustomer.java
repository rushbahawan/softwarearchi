package model;
// libraries
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class queueofcustomer {
    private Queue<String> customerQueue;
// creating linklist
    public queueofcustomer() {
        this.customerQueue = new LinkedList<>();
    }
// load data from csv
    public void loadCustomers(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String customerName = parts[1].trim();
                customerQueue.add(customerName); // add cust name to queue
            }
            System.out.println("Customers loaded successfully.");
        } catch (IOException e) {
            // error handling
            System.err.println("Error reading Custs.csv: " + e.getMessage());
        }
    }
// get method
    public Queue<String> getCustomerQueue() {
        return customerQueue;
    }
}

