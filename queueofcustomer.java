package model;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class queueofcustomer {
    private Queue<String> customerQueue;

    public queueofcustomer() {
        this.customerQueue = new LinkedList<>();
    }

    public void loadCustomers(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String customerName = parts[1].trim();
                customerQueue.add(customerName);
            }
            System.out.println("Customers loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading Custs.csv: " + e.getMessage());
        }
    }

    public Queue<String> getCustomerQueue() {
        return customerQueue;
    }
}

//package model;
//
//import java.io.*;
//import java.util.ArrayList;
//
//public class queueofcustomer {
//    private ArrayList<String> customerList = new ArrayList<>();
//
//    public void loadCustomers(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            br.readLine(); // Skip header
//            while ((line = br.readLine()) != null) {
//                customerList.add(line);
//            }
//            System.out.println("Customers loaded successfully.");
//        } catch (IOException e) {
//            System.err.println("Error loading customers: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public String toString() {
//        return String.join("\n", customerList);
//    }
//}
