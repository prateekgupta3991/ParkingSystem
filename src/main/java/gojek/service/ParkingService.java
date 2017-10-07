package gojek.service;

/**
 * Created by prateekgupta on 07/10/17.
 */
public interface ParkingService {

    String createParkingLot(Integer lotSize);

    String issueTicket(String regNum, String color);

    String leaveParking(Integer slotId);

    String status();

    String getCarsWithColor(String color);

    String getSlotForCar(String regNum);
}
