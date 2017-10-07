package gojek.service;

import gojek.constants.InfoType;
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

        String status = getParkingDetails(InfoType.STATUS, null);
        return status;
    }

    @Override
    public String getCarsWithColor(String color) {

        String cars = getParkingDetails(InfoType.STATUS, color);
        return cars;
    }

    @Override
    public String getSlotForCar(String regNum) {

        String slots = getParkingDetails(InfoType.PARKING_SLOT_OF_CAR, regNum);
        return slots;
    }

    private String getParkingDetails(InfoType infoType, String miscInfo) {

        Map<ParkingSlot, Vehicle> parkingSlotVehicleMap = parkingTicket.getParkingSlotVehicleMap();
        if(parkingSlotVehicleMap.size() == 0 )
            return "Not found";

        StringBuilder status = new StringBuilder();

        if(infoType == InfoType.STATUS) {
            status.append("Slot No.\tRegistration No\tColour\n");
        }

        for (Map.Entry<Integer, ParkingSlot> vehicleParkingSlotEntry : parkingSpace.getIdToSlotMap().entrySet()) {
            if (parkingSlotVehicleMap.containsKey(vehicleParkingSlotEntry) == true) {
                if(infoType == InfoType.STATUS) {
                    Vehicle vehicle = parkingSlotVehicleMap.get(vehicleParkingSlotEntry);
                    status.append(vehicleParkingSlotEntry.getKey() + "\t" + vehicle.getRegistrationNumber() + "\t" + vehicle.getColor() + "\n");

                } else if(infoType == InfoType.CARS_WITH_COLOR) {
                    if(parkingSlotVehicleMap.get(vehicleParkingSlotEntry).getColor().equalsIgnoreCase(miscInfo)) {
                        Vehicle vehicle = parkingSlotVehicleMap.get(vehicleParkingSlotEntry);
                        status.append(vehicle.getRegistrationNumber()+",");
                    }

                } else if(infoType == InfoType.PARKING_SLOT_OF_CAR) {
                    if(parkingSlotVehicleMap.get(vehicleParkingSlotEntry).getRegistrationNumber().equalsIgnoreCase(miscInfo)) {
                        status.append(vehicleParkingSlotEntry.getKey()+",");
                        break;
                    }
                }
            }
        }

        String response = "Not found";
        if(status.length() > 0)
            response = status.substring(0, status.length() - 1);

        return response;
    }
}
