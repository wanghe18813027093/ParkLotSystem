package park;

import org.junit.jupiter.api.Test;

public class ParkingDirectorTest {
    @Test
    public void should_print_a_report_table() {
        //given
        ParkingDirector parkingDirector = new ParkingDirector();
        ParkLot parkingBoyParkLot1 = new ParkLot(3);
        ParkLot parkingBoyParkLot2 = new ParkLot(2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingBoyParkLot1, parkingBoyParkLot2);
        ParkLot smartParkingBoyParkLot = new ParkLot(5);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(smartParkingBoyParkLot);
        ParkLot parkingManagerParkLot = new ParkLot(10);
        ParkingManager parkingManager = new ParkingManager(parkingManagerParkLot);
        parkingManager.addBoy("superParkingBoy", superParkingBoy);
        parkingManager.addBoy("smartParkingBoy", smartParkingBoy);

        //when
        ParkTicket ticket = superParkingBoy.park(new Car());
        superParkingBoy.park(new Car());
        superParkingBoy.pickup(ticket);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        parkingManager.park(new Car());
        parkingManager.park(new Car());

        //then
        parkingDirector.getReportFromManager(parkingManager);
    }
}
