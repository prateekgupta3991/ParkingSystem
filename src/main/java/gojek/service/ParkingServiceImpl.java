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

        Vehicle vehicle = new Vehicle(regNum, color);
        ParkingSlot availableParkingSlot = parkingSpace.getIdToSlotMap().get(parkingSpace.getLeastSlotFromQueue());
        parkingTicket.getVehicleParkingSlotMap().put(vehicle, availableParkingSlot);

        return "Allocated slot number: " + availableParkingSlot.getSlotId();
    }

}
