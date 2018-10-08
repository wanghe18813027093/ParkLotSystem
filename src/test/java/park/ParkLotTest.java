package park;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class ParkLotTest {

    @Test
    public void should_part_success_when_parkingLot_not_full() {
        //given
        ParkLot parkLot = new ParkLot(1);

        //when
        ParkTicket ticket = parkLot.park(new Car());

        //then
        assertNotNull(ticket);

    }

    @Test
    public void should_part_failed_when_parkingLot_is_full() {
        //given
        ParkLot parkLot = new ParkLot(1);

        //when
        parkLot.park(new Car());
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            parkLot.park(new Car());
        });

        //then
        assertEquals("停车场满了！", exception.getMessage());

    }

    @Test
    public void should_get_indicated_card_when_pick_up_with_a_ticket() {
        //given
        ParkLot parkLot = new ParkLot(2);
        Car car1 = new Car();
        Car car2 = new Car();

        //when
        ParkTicket parkTicket1 = parkLot.park(car1);
        ParkTicket parkTicket2 = parkLot.park(car2);
        Car pickedCar = parkLot.pickup(parkTicket2);

        //then
        assertEquals(pickedCar, car2);

    }

    @Test
    public void should_park_one_car_when_pick_up_one_car_from_full_parkingLot() {
        //given
        ParkLot parkLot = new ParkLot(2);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkTicket parkTicket1 = parkLot.park(car1);
        ParkTicket parkTicket2 = parkLot.park(car2);
        parkLot.pickup(parkTicket2);

        //when
        ParkTicket ticket3 = parkLot.park(new Car());

        //then
        assertNotNull(ticket3);
    }

    @Test
    public void should_not_be_full_after_init_parking_lot() {
        //given
        ParkLot parkLot = new ParkLot(1);

        //when

        //then
        assertFalse(parkLot.isFull());

    }
}
