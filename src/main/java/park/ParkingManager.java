package park;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParkingManager extends ParkingBoy {
    private final Map<String, ParkingBoy> parkingBoyNameMap;

    public ParkingManager(ParkLot... parkLots) {
        super(parkLots);
        parkingBoyNameMap = new HashMap<String, ParkingBoy>();
    }

    //需求没有要求，假设ParkingManager的存车策略和ParkingBoy相同
    @Override
    public ParkTicket park(Car car) {
        for (ParkLot parkLot : parkLots) {
            if (!parkLot.isFull()) {
                return parkLot.park(car);
            }
        }
        throw new IndexOutOfBoundsException("ParkingManager的停车场满了！");
    }

    public void addBoy(String boyName, ParkingBoy parkingBoy) {
        parkingBoyNameMap.put(boyName, parkingBoy);
    }

    public ParkTicket orderBoyToPark(String name, Car car) {
        return parkingBoyNameMap.get(name).park(car);
    }

    public int getParkedCarNumberSum() {
        int parkedCarNumberSum = getParkedCarNumber();
        Iterator iterator = parkingBoyNameMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            ParkingBoy boy = (ParkingBoy) entry.getValue();
            parkedCarNumberSum += boy.getParkedCarNumber();
        }
        return parkedCarNumberSum;
    }

    public int getParkLotsCapacitySum() {
        int parkLotsCapacitySum = getParkLotsCapacity();
        Iterator iterator = parkingBoyNameMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            ParkingBoy boy = (ParkingBoy) entry.getValue();
            parkLotsCapacitySum += boy.getParkLotsCapacity();
        }
        return parkLotsCapacitySum;
    }

    @Override
    public void printParkPlotsCondition() {
        System.out.print("M " + getParkedCarNumberSum() + " " + getParkLotsCapacitySum() + "\n");
        for (ParkLot parkLot : parkLots) {
            System.out.print(" P " + parkLot.getParkLotSize() + " " + parkLot.getParkLotCapacity() + "\n");
        }
    }

    public void reportToDirector(){
        printParkPlotsCondition();
        Iterator iterator = parkingBoyNameMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            ParkingBoy boy = (ParkingBoy) entry.getValue();
            boy.printParkPlotsCondition();
        }
    }
}
