package park;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    public void should_park_into_first_parkingLot_when_first_parkingLot_is_not_full() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(parkLot1, parkLot2);

        //when
        ParkTicket ticket = parkingBoy.park(new Car());

        //then
        assertTrue(parkLot1.isFull());
        assertFalse(parkLot2.isFull());
    }

    @Test
    public void should_park_into_second_parkingLot_when_first_parkingLot_is_full() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(parkLot1, parkLot2);

        //when
        ParkTicket ticket1 = parkingBoy.park(new Car());
        ParkTicket ticket2 = parkingBoy.park(new Car());

        //then
        assertTrue(parkLot1.isFull());
        assertTrue(parkLot2.isFull());
    }

    @Test
    public void should_throw_exception_when_parkingLots_is_full() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkLot1, parkLot2);

        //when
        ParkTicket ticket1 = parkingBoy.park(new Car());
        ParkTicket ticket2 = parkingBoy.park(new Car());

        //then
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            parkingBoy.park(new Car());
        });
        assertEquals("ParkingBoy的停车场满了！", exception.getMessage());
    }

    @Test
    public void should_get_a_car_when_has_a_ticket() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkLot1, parkLot2);

        //when
        Car car = new Car();
        ParkTicket ticket = parkingBoy.park(car);

        //then
        assertThat(parkingBoy.pickup(ticket)).isEqualTo(car);
    }

    @Test
    public void should_not_get_a_car_when_has_a_invalid_ticket(){
        //given
        ParkLot parkLot = new ParkLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkLot);

        //when
        ParkTicket ticket = parkingBoy.park(new Car());

        //then
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, ()->{
           parkingBoy.pickup(new ParkTicket());
        });
        assertEquals("ticket无效！",exception.getMessage());
    }

}