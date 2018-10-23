package park;

import java.util.Arrays;
import java.util.List;
/*
 * parkingBoy按照顺序存车
 */
public class ParkingBoy {
    protected List<ParkLot> parkLots;

    public ParkingBoy(ParkLot... parkLots) {
        this.parkLots = Arrays.asList(parkLots);
    }

    protected ParkTicket park(Car car) {
        return findParkLotToPark().park(car);
    }

    protected ParkLot findParkLotToPark(){
        for (ParkLot parkLot : parkLots) {
            if (!parkLot.isFull()) {
                return parkLot;
            }
        }
        throw new IndexOutOfBoundsException("ParkingBoy的停车场满了！");
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

    protected boolean allParkLotsIsFull() {
        for (ParkLot parkLot : parkLots) {
            if (!parkLot.isFull()) {
                return false;
            }
        }
        return true;
    }

    protected int getParkedCarNumber() {
        int parkLotsCarNumber = 0;
        for (ParkLot parkLot : parkLots) {
            parkLotsCarNumber += parkLot.getParkLotSize();
        }
        return parkLotsCarNumber;

    }

    protected int getParkLotsCapacity() {
        int parkLotsCapacity = 0;
        for (ParkLot parkLot : parkLots) {
            parkLotsCapacity += parkLot.getParkLotCapacity();
        }
        return parkLotsCapacity;
    }

    //每个boy都在ParkingReport中填写自己的停车情况
    protected void fillParkingReport(ParkingReport parkingReport) {
        parkingReport.getReportContent().append(" B " + getParkedCarNumber() + " " + getParkLotsCapacity() + "\n");
        for (ParkLot parkLot : parkLots) {
            parkingReport.getReportContent().append("   P " + parkLot.getParkLotSize() + " " + parkLot.getParkLotCapacity() + "\n");
        }
    }
}
