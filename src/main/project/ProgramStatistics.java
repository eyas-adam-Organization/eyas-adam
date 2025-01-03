import java.util.ArrayList;

public class ProgramStatistics {
     private ArrayList<ClientStatistics> clientStatistics;
     private String message;
     public ProgramStatistics(ArrayList<ClientStatistics> clientStatistics,String message){
          this.clientStatistics=clientStatistics;
          this.message=message;
     }
     public ArrayList<ClientStatistics> getClientStatistics(){return clientStatistics;}
     public String getMessage(){return message;}
}
