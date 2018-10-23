package park;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperParkingBoyTest extends ParkingBoy {
    @Test
    public void should_park_into_largest_vacancy_rate_parkingLot() {
        //given
        ParkLot parkLot1 = new ParkLot(3);
        ParkLot parkLot2 = new ParkLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkLot1, parkLot2);
        Car car1 = new Car();
        Car car2 = new Car();

        //when
        ParkTicket ticket1 = superParkingBoy.park(car1);
        ParkTicket ticket2 = superParkingBoy.park(car2);

        //then
        assertThat(parkLot1.getParkLotSize()).isEqualTo(1);
        assertThat(parkLot2.getParkLotSize()).isEqualTo(1);

    }

    @Test
    public void should_park_in_order_when_vacancy_rate_is_same(){
        //given
        ParkLot parkLot1 = new ParkLot(3);
        ParkLot parkLot2 = new ParkLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkLot1, parkLot2);
        Car car1 = new Car();
        Car car2 = new Car();

        //when
        ParkTicket ticket1 = superParkingBoy.park(car1);

        //then
        assertThat(parkLot1.getParkLotSize()).isEqualTo(1);
        assertThat(parkLot2.getParkLotSize()).isEqualTo(0);

    }

    @Test
    public void should_throw_exception_when_parkingLots_is_full() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkLot1,parkLot2);

        //when
        ParkTicket ticket1 = superParkingBoy.park(new Car());
        ParkTicket ticket2 = superParkingBoy.park(new Car());

        //then
        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()-> {
            superParkingBoy.park(new Car());
        });
        assertEquals("SuperParkingBoy的停车场满了！", exception.getMessage());

    }

    @Test
    public void should_get_a_car_when_has_a_ticket() {
        //given
        ParkLot parkLot1 = new ParkLot(1);
        ParkLot parkLot2 = new ParkLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkLot1, parkLot2);

        //when
        Car car = new Car();
        ParkTicket ticket = superParkingBoy.park(car);

        //then
        assertThat(superParkingBoy.pickup(ticket)).isEqualTo(car);

    }
    @Test
    public void should_not_get_a_car_when_has_a_invalid_ticket(){
        //given
        ParkLot parkLot = new ParkLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkLot);

        //when
        ParkTicket ticket = superParkingBoy.park(new Car());

        //then
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, ()->{
            superParkingBoy.pickup(new ParkTicket());
        });
        assertEquals("ticket无效！",exception.getMessage());

    }

}
