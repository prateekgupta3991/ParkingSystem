package gojek.service;

import gojek.constants.InfoType;
import gojek.entities.ParkingSlot;
import gojek.entities.ParkingSpace;
import gojek.entities.ParkingTicket;
import gojek.entities.Vehicle;

import java.util.Map;

/**
 * Created by prateekgupta on 07/10/17.
 *
 * createParkingLot - for creating the parking lot.
 * issueTicket -  when a new vehicle arrives for parking.
 * leaveParking - for the exit of vehicle.
 * status - current status of parking slot.
 * getCarsRegNumOrSlotWithColor - gets the registration number of car or its
 *                                  corresponding parking slot based on color.
 * getSlotForCarWithRegNum - gets the parking slot of a car with a specific registration number.
 */
public class ParkingServiceImpl implements ParkingService {

    private ParkingSpace parkingSpace;

    private ParkingTicket parkingTicket;

    public ParkingServiceImpl() {
        this.parkingSpace = new ParkingSpace();
        this.parkingTicket = new ParkingTicket();
    }

    @Override
    public String createParkingLot(Integer lotSize) {
        return parkingSpace.createParking(lotSize);
    }

    @Override
    public String issueTicket(String regNum, String color) {

        String doesParkingExists = validateParkingSpace();
        if(doesParkingExists != null)
            return doesParkingExists;

        if (parkingSpace.getSlotQueue().size() < 1)
            return "Sorry, parking lot is full";
        Vehicle vehicle = new Vehicle(regNum, color);
        ParkingSlot availableParkingSlot = parkingSpace.getIdToSlotMap().get(parkingSpace.getLeastSlotFromQueue());
        availableParkingSlot.setVacant(false);
        parkingTicket.getParkingSlotVehicleMap().put(availableParkingSlot, vehicle);

        return "Allocated slot number: " + availableParkingSlot.getSlotId();
    }

    @Override
    public String leaveParking(Integer slotId) {

        String doesParkingExists = validateParkingSpace();
        if(doesParkingExists != null)
            return doesParkingExists;

        ParkingSlot parkingSlot = parkingSpace.getIdToSlotMap().get(slotId);
        Boolean isSlotEmpty = parkingTicket.removeVehicleFromParking(parkingSlot);
        if (isSlotEmpty == false) {
            return "Slot was already empty";
        }
        parkingSpace.addSlotToQueue(slotId);

        return "Slot number " + slotId + " is free";
    }

    @Override
    public String status() {

        String doesParkingExists = validateParkingSpace();
        if(doesParkingExists != null)
            return doesParkingExists;

        String status = getParkingDetails(InfoType.STATUS, null);
        return status;
    }

    @Override
    public String getCarsRegNumOrSlotWithColor(InfoType infoType, String color) {

        String doesParkingExists = validateParkingSpace();
        if(doesParkingExists != null)
            return doesParkingExists;

        String cars = getParkingDetails(infoType, color);
        return cars;
    }

    @Override
    public String getSlotForCarWithRegNum(String regNum) {

        String doesParkingExists = validateParkingSpace();
        if(doesParkingExists != null)
            return doesParkingExists;

        String slots = getParkingDetails(InfoType.PARKING_SLOT_OF_CAR, regNum);
        return slots;
    }

    private String getParkingDetails(InfoType infoType, String miscInfo) {

        Map<ParkingSlot, Vehicle> parkingSlotVehicleMap = parkingTicket.getParkingSlotVehicleMap();
        if(infoType != InfoType.STATUS) {
            if (parkingSlotVehicleMap.size() == 0)
                return "Not found";
        }

        StringBuilder status = new StringBuilder();
        if (infoType == InfoType.STATUS) {
            status.append("Slot No.\tRegistration No\tColour \n");
        }

        for (Map.Entry<Integer, ParkingSlot> vehicleParkingSlotEntry : parkingSpace.getIdToSlotMap().entrySet()) {
            if (parkingSlotVehicleMap.containsKey(vehicleParkingSlotEntry.getValue()) == true) {
                if (infoType == InfoType.STATUS) {
                    Vehicle vehicle = parkingSlotVehicleMap.get(vehicleParkingSlotEntry.getValue());
                    status.append(vehicleParkingSlotEntry.getKey() + "\t" + vehicle.getRegistrationNumber() + "\t" + vehicle.getColor() + " \n");

                } else if (infoType == InfoType.REG_NUM_OF_CARS_WITH_COLOR || infoType == InfoType.PARKING_SLOT_OF_CARS_WITH_COLOR) {
                    if (parkingSlotVehicleMap.get(vehicleParkingSlotEntry.getValue()).getColor().equalsIgnoreCase(miscInfo)) {
                        if (infoType == InfoType.REG_NUM_OF_CARS_WITH_COLOR) {
                            Vehicle vehicle = parkingSlotVehicleMap.get(vehicleParkingSlotEntry.getValue());
                            status.append(vehicle.getRegistrationNumber() + ", ");
                        } else {
                            status.append(vehicleParkingSlotEntry.getKey() + ", ");
                        }
                    }

                } else if (infoType == InfoType.PARKING_SLOT_OF_CAR) {
                    if (parkingSlotVehicleMap.get(vehicleParkingSlotEntry.getValue()).getRegistrationNumber().equalsIgnoreCase(miscInfo) ||
                            parkingSlotVehicleMap.get(vehicleParkingSlotEntry.getValue()).getColor().equalsIgnoreCase(miscInfo)) {
                        status.append(vehicleParkingSlotEntry.getKey() + ", ");
                        break;
                    }
                }
            }
        }

        String response = "Not found";
        if (status.length() > 0)
            response = status.substring(0, status.length() - 2);

        return response;
    }

    public String validateParkingSpace() {
        if(parkingSpace.getIdToSlotMap().size() < 1)
            return "No Parking lot created";
        return null;
    }
}
