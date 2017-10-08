package gojek.entities;

import java.util.*;

/**
 * Created by prateekgupta on 07/10/17.
 *
 * Details about all the parking slot.
 */
public class ParkingSpace {

    private Map<Integer, ParkingSlot> idToSlotMap;
    private PriorityQueue<Integer> slotQueue;

    public ParkingSpace() {
        idToSlotMap = new LinkedHashMap<>();
        slotQueue = new PriorityQueue<>();
    }

    public ParkingSpace(Map<Integer, ParkingSlot> idToSlotMap) {
        this.idToSlotMap = idToSlotMap;
    }

    public String createParking(Integer parkingSize) {

        for (int id = 1; id <= parkingSize; id++) {
            ParkingSlot slot = new ParkingSlot(id, true);
            idToSlotMap.put(id, slot);
            slotQueue.add(id);
        }
        return "Created a parking lot with " + parkingSize + " slots";
    }

    public Map<Integer, ParkingSlot> getIdToSlotMap() {
        return idToSlotMap;
    }

    public void setIdToSlotMap(Map<Integer, ParkingSlot> idToSlotMap) {
        this.idToSlotMap = idToSlotMap;
    }

    public PriorityQueue<Integer> getSlotQueue() {
        return slotQueue;
    }

    public void setSlotQueue(PriorityQueue<Integer> slotQueue) {
        this.slotQueue = slotQueue;
    }

    public Integer getLeastSlotFromQueue() {
        return slotQueue.remove();
    }

    public void addSlotToQueue(Integer slotId) {
        slotQueue.add(slotId);
    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "idToSlotMap=" + idToSlotMap +
                '}';
    }
}
