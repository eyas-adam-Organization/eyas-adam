public class ReporTemp {
    private String programTitle;
    private String programPrice;
    private String numOfClients;
    private int clientID;
    private String completion;
    private String attendance;
    public ReporTemp(String programTitle,String programPrice,String numOfClients){
        this.programTitle=programTitle;
        this.programPrice=programPrice;
        this.numOfClients=numOfClients;
        this.clientID=0;
        this.completion="";
        this.attendance="";
    }
    public ReporTemp(int clientID,String completion,String attendance){
        this.clientID=clientID;
        this.completion=completion;
        this.attendance=attendance;
        this.programTitle="";
        this.programPrice="";
        this.numOfClients= "";
    }
    public String getProgramTitle(){
        return programTitle;
    }

    public String getAttendance() {
        return attendance;
    }

    public String getCompletion() {
        return completion;
    }

    public String getNumOfClients() {
        return numOfClients;
    }

    public int getClientID() {
        return clientID;
    }

    public String getProgramPrice() {
        return programPrice;
    }
}
