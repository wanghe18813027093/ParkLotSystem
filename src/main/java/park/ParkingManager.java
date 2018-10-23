package park;

import java.util.*;

public class ParkingManager {
    private final Map<String, ParkingBoy> parkingBoyNameMap;
    private List<ParkLot> parkLots;
    public ParkingManager(ParkLot... parkLots) {
        this.parkLots = Arrays.asList(parkLots);
        parkingBoyNameMap = new HashMap<String, ParkingBoy>();
    }

    //需求没有要求，假设ParkingManager的存车策略和ParkingBoy相同
    protected ParkTicket park(Car car) {
        for (ParkLot parkLot : parkLots) {
            if (!parkLot.isFull()) {
                return parkLot.park(car);
            }
        }
        throw new IndexOutOfBoundsException("ParkingManager的停车场满了！");
    }
    protected Car pickup(ParkTicket ticket) {
        for (ParkLot parkLot : parkLots) {
            Car car = parkLot.pickup(ticket);
            if (car != null) {
                return car;
            }
        }
        throw new IndexOutOfBoundsException("ticket无效！");
    }

    protected void addBoy(String boyName, ParkingBoy parkingBoy) {
        parkingBoyNameMap.put(boyName, parkingBoy);
    }

    protected ParkTicket orderBoyToPark(String name, Car car) {
        return parkingBoyNameMap.get(name).park(car);
    }

    private int getParkedCarNumberSum() {
        int parkedCarNumberSum = getParkedCarNumber();
        Iterator iterator = parkingBoyNameMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            ParkingBoy boy = (ParkingBoy) entry.getValue();
            parkedCarNumberSum += boy.getParkedCarNumber();
        }
        return parkedCarNumberSum;
    }
    private int getParkedCarNumber() {
        int parkLotsCarNumber = 0;
        for (ParkLot parkLot : parkLots) {
            parkLotsCarNumber += parkLot.getParkLotSize();
        }
        return parkLotsCarNumber;

    }
    private int getParkLotsCapacitySum() {
        int parkLotsCapacitySum = getParkLotsCapacity();
        Iterator iterator = parkingBoyNameMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            ParkingBoy boy = (ParkingBoy) entry.getValue();
            parkLotsCapacitySum += boy.getParkLotsCapacity();
        }
        return parkLotsCapacitySum;
    }
    private int getParkLotsCapacity() {
        int parkLotsCapacity = 0;
        for (ParkLot parkLot : parkLots) {
            parkLotsCapacity += parkLot.getParkLotCapacity();
        }
        return parkLotsCapacity;
    }
    private void fillParkingReport(ParkingReport parkingReport) {
        parkingReport.getReportContent().append("M " + getParkedCarNumberSum() + " " + getParkLotsCapacitySum() + "\n");
        for (ParkLot parkLot : parkLots) {
            parkingReport.getReportContent().append(" P " + parkLot.getParkLotSize() + " " + parkLot.getParkLotCapacity() + "\n");
        }
    }
    protected ParkingReport reportToDirector(){
        ParkingReport parkingReport = new ParkingReport();
        fillParkingReport(parkingReport);
        Iterator iterator = parkingBoyNameMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            ParkingBoy boy = (ParkingBoy) entry.getValue();
            boy.fillParkingReport(parkingReport);
        }
        return parkingReport;
    }
}
