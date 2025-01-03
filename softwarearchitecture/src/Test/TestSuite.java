package Test;
//libraries
import model.queueofcustomer;
import model.parcelmapp;
import model.parcel;
import controller.worker;

public class TestSuite {
    public static void main(String[] args) {
        // Test the queueofcustomer class
        queueofcustomer customerQueue = new queueofcustomer();
        customerQueue.getCustomerQueue().add("Alice");
        customerQueue.getCustomerQueue().add("Bob");
        customerQueue.getCustomerQueue().add("Charlie");
        System.out.println("Customer Queue Test:");
        System.out.println(customerQueue.getCustomerQueue());

        // Test the parcelmapp class
        parcelmapp parcelMap = new parcelmapp();
        parcelMap.getParcelMap().put("P001", new parcel("P001", 12.5)); // Add parcel object
        parcelMap.getParcelMap().put("P002", new parcel("P002", 8.0));  // Add parcel object
        parcelMap.getParcelMap().put("P003", new parcel("P003", 15.3)); // Add parcel object
        System.out.println("Parcel Map Test:");
        System.out.println(parcelMap);

        // Test the worker class
        worker workerInstance = new worker();
        double fee1 = workerInstance.calculateFee(12.5);
        double fee2 = workerInstance.calculateFee(8.0);
        double fee3 = workerInstance.calculateFee(15.3);
        System.out.println("Worker Test:");
        System.out.println("Fee for parcel P001: " + fee1);
        System.out.println("Fee for parcel P002: " + fee2);
        System.out.println("Fee for parcel P003: " + fee3);

        // Test the getDaysInDepot method in the parcel class
        parcel testParcel = new parcel("P004", 20.0);
        System.out.println("Days in Depot Test:");
        System.out.println("Parcel P004 Days in Depot: " + testParcel.getDaysInDepot());
    }
}
