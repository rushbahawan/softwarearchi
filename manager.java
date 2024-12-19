package controller;

import model.queueofcustomer;
import model.parcelmapp;
import view.gui;

public class manager {
    public static void main(String[] args) {
        // Initialize core components
        queueofcustomer customerQueue = new queueofcustomer();
        parcelmapp parcelMap = new parcelmapp();

        // Load data from CSV files
        customerQueue.loadCustomers("src/data/Custs.csv");
        parcelMap.loadParcels("src/data/Parcels.csv");

        // Launch the GUI
        new gui(customerQueue, parcelMap, new worker());
    }
}

//package controller;
//
//import model.queueofcustomer;
//import model.parcelmapp;
//import view.gui;
//
//public class manager {
//    public static void main(String[] args) {
//        // Initialize core components
//        queueofcustomer customerQueue = new queueofcustomer();
//        parcelmapp parcelMap = new parcelmapp();
//
//        // Load data from CSV files
//        customerQueue.loadCustomers("src/data/Custs.csv");
//        parcelMap.loadParcels("src/data/Parcels.csv");
//
//        // Launch the GUI
//        new gui(customerQueue, parcelMap, new worker());
//    }
//}
