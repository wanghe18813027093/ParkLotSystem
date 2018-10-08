package park;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SmartParkingBoyTest {
    @Test
    public void should_park_into_largest_room_parkingLot() {
        //given
        ParkLot parkLot1 = new ParkLot(2);
        ParkLot parkLot2 = new ParkLot(1);
        SmartParkingBoy boy = new SmartParkingBoy(parkLot1, parkLot2);

        //when
        boy.park(new Car());

        //then
        assertThat(parkLot1.getParkLotSize()).isEqualTo(1);
    }

    @Test
    public void should_throw_exception_when_parkingLots_is_full() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkLot1, parkLot2);

        //when
        ParkTicket ticket1 = smartParkingBoy.park(new Car());
        ParkTicket ticket2 = smartParkingBoy.park(new Car());

        //then
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            smartParkingBoy.park(new Car());
        });
        assertEquals("SmartParkingBoy的停车场满了！", exception.getMessage());

    }

    @Test
    public void should_get_a_car_when_has_a_ticket() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkLot1, parkLot2);

        //when
        Car parkedCar = new Car();
        ParkTicket ticket = smartParkingBoy.park(parkedCar);

        //then
        assertThat(smartParkingBoy.pickup(ticket)).isEqualTo(parkedCar);

    }

    @Test
    public void should_not_get_a_car_when_has_a_invalid_ticket(){
        //given
        ParkLot parkLot = new ParkLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkLot);

        //when
        ParkTicket ticket = smartParkingBoy.park(new Car());

        //then
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, ()->{
            smartParkingBoy.pickup(new ParkTicket());
        });
        assertEquals("ticket无效！",exception.getMessage());

    }
}
