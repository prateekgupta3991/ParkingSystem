package gojek.entities;

/**
 * Created by prateekgupta on 07/10/17.
 */
public class Vehicle {

    private String registrationNumber;
    private String color;
    private Integer parkingSlot;

    public Vehicle() {
    }

    public Vehicle(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public Vehicle(String registrationNumber, String color, Integer parkingSlot) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.parkingSlot = parkingSlot;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(Integer parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", parkingSlot=" + parkingSlot +
                '}';
    }
}
