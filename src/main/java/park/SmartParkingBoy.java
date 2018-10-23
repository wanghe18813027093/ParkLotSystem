package park;
/*
 * SmartParkingBoy在空位最多的停车场存车
 */
public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkLot... parkLot) {
        super(parkLot);
    }
    @Override
    protected ParkLot findParkLotToPark(){
        if (allParkLotsIsFull()){
            throw new IndexOutOfBoundsException("SmartParkingBoy的停车场满了！");
        }
        ParkLot largestRoomParkLot = parkLots.get(0);
        for (ParkLot parkLot : parkLots){
            if (parkLot.getParkLotRoom() > largestRoomParkLot.getParkLotRoom()){
                largestRoomParkLot = parkLot;
            }
        }
        return largestRoomParkLot;
    }
}
