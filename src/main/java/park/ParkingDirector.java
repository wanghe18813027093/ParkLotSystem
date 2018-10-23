package park;

public class ParkingDirector {
    protected void getReportFromManager(ParkingManager parkingManager){
        parkingManager.reportToDirector().printReport();
    }
}
