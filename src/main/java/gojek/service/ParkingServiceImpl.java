package gojek.service;

import gojek.entities.ParkingSlot;
import gojek.entities.ParkingSpace;
import gojek.entities.ParkingTicket;
import gojek.entities.Vehicle;

import java.util.Map;

/**
 * Created by prateekgupta on 07/10/17.
 */
public class ParkingServiceImpl implements ParkingService {

    private ParkingSpace parkingSpace;

    private ParkingTicket parkingTicket;

    public ParkingServiceImpl(ParkingSpace parkingSpace, ParkingTicket parkingTicket) {
        this.parkingSpace = parkingSpace;
        this.parkingTicket = parkingTicket;
    }

    @Override
    public String createParkingLot(Integer lotSize) {
        return parkingSpace.createParking(lotSize);
    }

    @Override
    public String issueTicket(String regNum, String color) {

        if(parkingSpace.getLeastSlotFromQueue() == null)
            return "Sorry, parking lot is full";
        Vehicle vehicle = new Vehicle(regNum, color);
        ParkingSlot availableParkingSlot = parkingSpace.getIdToSlotMap().get(parkingSpace.getLeastSlotFromQueue());
        parkingTicket.getParkingSlotVehicleMap().put(availableParkingSlot, vehicle);

        return "Allocated slot number: " + availableParkingSlot.getSlotId();
    }

    @Override
    public String leaveParking(Integer slotId) {

        ParkingSlot parkingSlot = parkingSpace.getIdToSlotMap().get(slotId);
        Boolean isSlotEmpty = parkingTicket.removeVehicleFromParking(parkingSlot);
        if(isSlotEmpty == false) {
            return "Slot was already empty";
        }
        parkingSpace.getIdToSlotMap().remove(slotId);
        parkingSpace.addSlotToQueue(slotId);

        return "Slot number " + slotId + " is free";
    }

    @Override
    public String status() {

        Map<ParkingSlot, Vehicle> parkingSlotVehicleMap = parkingTicket.getParkingSlotVehicleMap();
        StringBuilder status = new StringBuilder("Slot No.\tRegistration No\tColour\n");
        for (Map.Entry<Integer, ParkingSlot> vehicleParkingSlotEntry : parkingSpace.getIdToSlotMap().entrySet()) {
            if(parkingSlotVehicleMap.containsKey(vehicleParkingSlotEntry) == true) {
                Vehicle vehicle = parkingSlotVehicleMap.get(vehicleParkingSlotEntry);
                status.append(vehicleParkingSlotEntry.getKey()+"\t"+vehicle.getRegistrationNumber()+"\t"+vehicle.getColor()+"\n");
            }
        }
        return status.toString();
    }
}
