package park;
/*
 * SuperParkingBoy在空置率最高的停车场停车
 */
public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(ParkLot... parkLot) {
        super(parkLot);
    }

    @Override
    public ParkTicket park(Car car) {
        if (allParkLotsIsFull()){
            throw new IndexOutOfBoundsException("SuperParkingBoy的停车场满了！");
        }
        ParkLot largestVacancyParkLot = parkLots.get(0);
        for (ParkLot parkLot : parkLots){
            if (parkLot.getParkLotVacancy() > largestVacancyParkLot.getParkLotVacancy()){
                largestVacancyParkLot = parkLot;
            }
        }
        return largestVacancyParkLot.park(car);
    }
}
