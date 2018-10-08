package park;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingManagerTest {
    @Test
    public void should_park_into_first_parkingLot_when_first_parkingLot_is_not_full() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        ParkingManager parkingManage = new ParkingManager(parkLot1, parkLot2);

        //when
        ParkTicket ticket = parkingManage.park(new Car());

        //then
        assertTrue(parkLot1.isFull());
        assertFalse(parkLot2.isFull());

    }

    @Test
    public void should_park_into_second_parkingLot_when_first_parkingLot_is_full() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        ParkingManager parkingManage = new ParkingManager(parkLot1, parkLot2);

        //when
        ParkTicket ticket1 = parkingManage.park(new Car());
        ParkTicket ticket2 = parkingManage.park(new Car());

        //then
        assertTrue(parkLot1.isFull());
        assertTrue(parkLot2.isFull());
    }

    @Test
    public void should_throw_exception_when_parkingLots_is_full() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        ParkingManager parkingManage = new ParkingManager(parkLot1, parkLot2);

        //when
        ParkTicket ticket1 = parkingManage.park(new Car());
        ParkTicket ticket2 = parkingManage.park(new Car());

        //then
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            parkingManage.park(new Car());
        });
        assertEquals("ParkingManager的停车场满了！", exception.getMessage());

    }

    @Test
    public void should_get_a_car_when_has_a_ticket() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        ParkingManager parkingManage = new ParkingManager(parkLot1, parkLot2);

        //when
        Car car = new Car();
        ParkTicket ticket = parkingManage.park(car);

        //then
        assertThat(parkingManage.pickup(ticket)).isEqualTo(car);

    }

    @Test
    public void should_not_get_a_car_when_has_a_invalid_ticket() {
        //given
        ParkLot parkLot = new ParkLot(1);
        ParkingManager parkingManage = new ParkingManager(parkLot);

        //when
        ParkTicket ticket = parkingManage.park(new Car());

        //then
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            parkingManage.pickup(new ParkTicket());
        });
        assertEquals("ticket无效！", exception.getMessage());
    }

    @Test
    public void parkingBoy_should_park_a_car() {
        //given
        ParkLot parkingManagerParkLot = new ParkLot(1);
        ParkLot parkingBoyParkLot1 = new ParkLot(1);
        ParkLot parkingBoyParkLot2 = new ParkLot(1);
        ParkingManager parkingManage = new ParkingManager(parkingManagerParkLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingBoyParkLot1, parkingBoyParkLot2);
        parkingManage.addBoy("parkingBoy", parkingBoy);

        //when
        ParkTicket ticket = parkingManage.orderBoyToPark("parkingBoy", new Car());

        //then
        assertTrue(parkingBoyParkLot1.isFull());
        assertFalse(parkingBoyParkLot2.isFull());
        assertFalse(parkingManagerParkLot.isFull());

    }

    @Test
    public void smartParkingBoy_should_park_a_car() {
        //given
        ParkLot parkingManagerParkLot = new ParkLot(1);
        ParkLot smartParkingBoyParkLot1 = new ParkLot(1);
        ParkLot smartParkingBoyParkLot2 = new ParkLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(smartParkingBoyParkLot1, smartParkingBoyParkLot2);
        ParkingManager parkingManager = new ParkingManager(parkingManagerParkLot);
        parkingManager.addBoy("smartParkingBoy", smartParkingBoy);

        //when
        parkingManager.orderBoyToPark("smartParkingBoy", new Car());

        //then
        assertFalse(smartParkingBoyParkLot1.isFull());
        assertThat(smartParkingBoyParkLot2.getParkLotSize()).isEqualTo(1);
        assertFalse(parkingManagerParkLot.isFull());

    }

    @Test
    public void superParkingBoy_should_park_a_car() {
        //given
        ParkLot parkingManagerParkLot = new ParkLot(3);
        ParkLot superParkingBoyParkLot1 = new ParkLot(1);
        ParkLot superParkingBoyParkLot2 = new ParkLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(superParkingBoyParkLot1, superParkingBoyParkLot2);
        ParkingManager parkingManager = new ParkingManager(parkingManagerParkLot);
        parkingManager.addBoy("superParkingBoy", superParkingBoy);

        //when
        parkingManager.orderBoyToPark("superParkingBoy", new Car());
        parkingManager.orderBoyToPark("superParkingBoy", new Car());

        //then
        assertThat(superParkingBoyParkLot1.getParkLotSize()).isEqualTo(1);
        assertTrue(superParkingBoyParkLot2.isFull());
        assertFalse(parkingManagerParkLot.isFull());

    }

    @Test
    public void should_get_a_report_table() {
        //given
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
        parkingManager.reportToDirector();

    }
}
