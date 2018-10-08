package park;

import java.util.HashMap;
import java.util.Map;

public class ParkLot {
    private final int capacity;
    private final Map<ParkTicket,Car> parkTicketCarHashMap;

    public ParkLot(int capacity) {
        parkTicketCarHashMap = new HashMap<ParkTicket, Car>();
        this.capacity = capacity;
    }

    public ParkTicket park(Car car) {
        if (parkTicketCarHashMap.size() >= this.capacity){
            throw new IndexOutOfBoundsException("停车场满了！");
        }
        ParkTicket ticket = new ParkTicket();
        parkTicketCarHashMap.put(ticket, car);
        return ticket;
    }

    public Car pickup(ParkTicket parkTicket) {
        return parkTicketCarHashMap.remove(parkTicket);
    }
    public int getParkLotCapacity(){
        return capacity;
    }

    public boolean isFull() {
        return parkTicketCarHashMap.size() == this.capacity;
    }

    public int getParkLotSize(){
        return parkTicketCarHashMap.size();
    }

    public int getParkLotRoom(){
        return capacity - parkTicketCarHashMap.size();
    }
    public double getParkLotVacancy() {
        return ((double) getParkLotRoom()) / capacity;
    }
}
