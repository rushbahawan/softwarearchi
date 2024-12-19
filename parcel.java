package model;

public class parcel {
    private int parcelID;
    private String dimensions;
    private float weight;
    private int daysInDepot;

    public parcel(int parcelID, String dimensions, float weight, int daysInDepot) {
        this.parcelID = parcelID;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
    }

    public int getParcelID() {
        return parcelID;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Parcel ID: " + parcelID + ", Dimensions: " + dimensions + ", Weight: " + weight + ", Days in Depot: " + daysInDepot;
    }
}
