package gojek.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prateekgupta on 07/10/17.
 */
public class ParkingSpace {

    private Map<Integer, ParkingSlot> idToSlotMap;

    public ParkingSpace() {
        idToSlotMap = new HashMap<>();
    }

    public ParkingSpace(Map<Integer, ParkingSlot> idToSlotMap) {
        this.idToSlotMap = idToSlotMap;
    }

    public void createParking(Integer parkingSize) {

        for(int id = 0; id < parkingSize; id++) {
            ParkingSlot slot = new ParkingSlot(id,true);
            idToSlotMap.put(id, slot);
        }
    }

    public Map<Integer, ParkingSlot> getIdToSlotMap() {
        return idToSlotMap;
    }

    public void setIdToSlotMap(Map<Integer, ParkingSlot> idToSlotMap) {
        this.idToSlotMap = idToSlotMap;
    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "idToSlotMap=" + idToSlotMap +
                '}';
    }
}
