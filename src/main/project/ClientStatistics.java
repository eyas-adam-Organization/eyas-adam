public class ClientStatistics {
    private int clientID;
    private int completion;
    private int attendance;
    public ClientStatistics(int clientID,int completion,int attendance){
        this.attendance=attendance;
        this.completion=completion;
        this.clientID=clientID;
    }
    public int getClientID(){return  clientID;}
    public int getCompletion(){return completion;}
    public int getAttendance(){return attendance;}
}
