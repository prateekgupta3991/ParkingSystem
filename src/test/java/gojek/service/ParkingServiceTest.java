package gojek.service;

import gojek.constants.InfoType;
import junit.framework.TestCase;

/**
 * Created by prateekgupta on 08/10/17.
 */
public class ParkingServiceTest extends TestCase {

    public void testParkingService()
    {
        ParkingService parkingService = new ParkingServiceImpl();
        assertEquals(parkingService.createParkingLot(4), "Created a parking lot with 4 slots");

        assertEquals(parkingService.issueTicket("KA-01-HH-1234", "White"), "Allocated slot number: 1");
        assertEquals(parkingService.issueTicket("KA-01-HH-9999", "White"), "Allocated slot number: 2");
        assertEquals(parkingService.issueTicket("KA-01-BB-0001", "Black"), "Allocated slot number: 3");
        assertEquals(parkingService.issueTicket("KA-01-HH-7777", "Red"), "Allocated slot number: 4");

        assertEquals(parkingService.leaveParking(3), "Slot number 3 is free");
        assertEquals(parkingService.status(), "Slot No.\tRegistration No\tColour \n" +
                "1\tKA-01-HH-1234\tWhite \n" +
                "2\tKA-01-HH-9999\tWhite \n" +
                "4\tKA-01-HH-7777\tRed");

        assertEquals(parkingService.issueTicket("KA-01-P-333", "White"), "Allocated slot number: 3");
        assertEquals(parkingService.issueTicket("DL-12-AA-9999", "White"), "Sorry, parking lot is full");

        assertEquals(parkingService.getCarsRegNumOrSlotWithColor(InfoType.REG_NUM_OF_CARS_WITH_COLOR,"White"), "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333");
        assertEquals(parkingService.getCarsRegNumOrSlotWithColor(InfoType.PARKING_SLOT_OF_CARS_WITH_COLOR, "White"), "1, 2, 3");
        assertEquals(parkingService.getSlotForCarWithRegNum("KA-01-HH-9999"), "2");
    }
}
