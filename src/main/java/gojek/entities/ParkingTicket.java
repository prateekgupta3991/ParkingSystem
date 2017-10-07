package gojek.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prateekgupta on 07/10/17.
 */
public class ParkingTicket {

    private Map<Vehicle, ParkingSlot> vehicleParkingSlotMap;

    public ParkingTicket() {
        vehicleParkingSlotMap = new HashMap<>();
    }

    public ParkingTicket(Map<Vehicle, ParkingSlot> vehicleParkingSlotMap) {
        this.vehicleParkingSlotMap = vehicleParkingSlotMap;
    }

    public Map<Vehicle, ParkingSlot> getVehicleParkingSlotMap() {
        return vehicleParkingSlotMap;
    }

    public void setVehicleParkingSlotMap(Map<Vehicle, ParkingSlot> vehicleParkingSlotMap) {
        this.vehicleParkingSlotMap = vehicleParkingSlotMap;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "vehicleParkingSlotMap=" + vehicleParkingSlotMap +
                '}';
    }
}
