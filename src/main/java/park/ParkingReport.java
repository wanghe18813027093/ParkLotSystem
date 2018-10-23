package park;

public class ParkingReport {
    private StringBuilder content = new StringBuilder();
    public StringBuilder getReportContent(){
        return content;
    }
    public void printReport(){
        System.out.print(content.toString());
    }
}
