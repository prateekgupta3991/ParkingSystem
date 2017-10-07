package gojek.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prateekgupta on 07/10/17.
 */
public class ParkingTicket {

    private Map<ParkingSlot, Vehicle> parkingSlotVehicleMap;

    public ParkingTicket() {
        parkingSlotVehicleMap = new HashMap<>();
    }

    public ParkingTicket(Map<ParkingSlot, Vehicle> parkingSlotVehicleMap) {
        this.parkingSlotVehicleMap = parkingSlotVehicleMap;
    }

    public Map<ParkingSlot, Vehicle> getParkingSlotVehicleMap() {
        return parkingSlotVehicleMap;
    }

    public void setParkingSlotVehicleMap(Map<ParkingSlot, Vehicle> parkingSlotVehicleMap) {
        this.parkingSlotVehicleMap = parkingSlotVehicleMap;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "parkingSlotVehicleMap=" + parkingSlotVehicleMap +
                '}';
    }

    public Boolean removeVehicleFromParking(ParkingSlot slotId) {

        if (!parkingSlotVehicleMap.containsKey(slotId))
            return false;
        parkingSlotVehicleMap.remove(slotId);
        return true;
    }
}
