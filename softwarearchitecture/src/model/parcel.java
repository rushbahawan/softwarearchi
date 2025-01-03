package model;
//libraries
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class parcel {
    //variables
    private String parcelId;
    private double weight;
    private LocalDate arrivalDate; // Date when the parcel was added
    // add new parcel
    public parcel(String parcelId, double weight) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.arrivalDate = LocalDate.now(); // Set the arrival date to today when the parcel is created
    }
    //get parcelid
    public String getParcelId() {
        return parcelId;
    }
    // get parcel weight
    public double getWeight() {
        return weight;
    }
    // set or update weight
    public void setWeight(double weight) {
        this.weight = weight;
    }
    // get arrival date
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    // update arrival date
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    //calculate the number of days
    public long getDaysInDepot() {
        return ChronoUnit.DAYS.between(arrivalDate, LocalDate.now());
    }
}

