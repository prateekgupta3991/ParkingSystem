package gojek.Main;

import gojek.constants.InfoType;
import gojek.service.ParkingService;
import gojek.service.ParkingServiceImpl;

import java.io.*;

/**
 * Driver program
 */
public class App {
    public static void main(String[] args) throws IOException {

        ParkingService parkingService = new ParkingServiceImpl();
        BufferedReader br = null;
        if(args.length == 1) {
            File file = new File(args[0]);
            br = new BufferedReader(new FileReader(file));
        } else if(args.length == 0) {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        while (true) {
            String command = br.readLine();
            if(command != null) {
                String[] commandTokens = command.split(" ");
                if (commandTokens[0].equalsIgnoreCase("create_parking_lot")) {
                    System.out.println(parkingService.createParkingLot(Integer.parseInt(commandTokens[1])));
                } else if (commandTokens[0].equalsIgnoreCase("park")) {
                    System.out.println(parkingService.issueTicket(commandTokens[1], commandTokens[2]));
                } else if (commandTokens[0].equalsIgnoreCase("leave")) {
                    System.out.println(parkingService.leaveParking(Integer.parseInt(commandTokens[1])));
                } else if (commandTokens[0].equalsIgnoreCase("status")) {
                    System.out.println(parkingService.status());
                } else if (commandTokens[0].equalsIgnoreCase("registration_numbers_for_cars_with_colour")) {
                    System.out.println(parkingService.getCarsRegNumOrSlotWithColor(InfoType.REG_NUM_OF_CARS_WITH_COLOR, commandTokens[1]));
                } else if (commandTokens[0].equalsIgnoreCase("slot_numbers_for_cars_with_colour")) {
                    System.out.println(parkingService.getCarsRegNumOrSlotWithColor(InfoType.PARKING_SLOT_OF_CARS_WITH_COLOR, commandTokens[1]));
                } else if (commandTokens[0].equalsIgnoreCase("slot_number_for_registration_number")) {
                    System.out.println(parkingService.getSlotForCarWithRegNum(commandTokens[1]));
                }
            }
        }
    }
}
