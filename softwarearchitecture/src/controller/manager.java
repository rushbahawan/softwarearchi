package controller;
//importing libraries
import model.queueofcustomer;
import model.parcelmapp;
import view.gui;

public class manager {
    public static void main(String[] args) {
        // Initialization
        queueofcustomer customerQueue = new queueofcustomer();
        parcelmapp parcelMap = new parcelmapp();

        // Loading data from csv files
        customerQueue.loadCustomers("src/data/Custs.csv");
        parcelMap.loadParcels("src/data/Parcels.csv");

        // GUI launch
        new gui(customerQueue, parcelMap, new worker());
    }
}

