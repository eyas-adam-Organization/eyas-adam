import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Group {
    private int groupID;
    private Instructor instructor;
    private ArrayList<Client> clients;
    private ArrayList<String> instructions;
    private int time;
    private String schedules;
    private Clients allClients;
    public Group(int groupID,Instructor instructor,int time,String schedules,ArrayList<Client> clients,ArrayList<String> instructions){
        this.groupID=groupID;
        this.time=time;
        this.schedules=schedules;
        this.instructor=instructor;
        this.instructions=instructions;
        this.clients=clients;
    }
    private void SearchForClientInProgress(int id,Instructors instructors,Clients allClients) throws IOException {
        Scanner scanner = new Scanner(new File("src/main/resources/groups.txt"));
        String curLine = "";
        String groupInfoTemp ="";
        String clientsTemp="";
        String tipTemp;
        String[]groupInfo;
        this.allClients=allClients;

        while (scanner.hasNextLine()) {
            curLine = scanner.nextLine();
            while (scanner.hasNextLine()){
                if(!curLine.equals("group"))curLine=scanner.nextLine();
                if(curLine.equals("group")){
                    if(scanner.hasNextLine())groupInfoTemp=scanner.nextLine();
                    if(scanner.hasNextLine())clientsTemp=scanner.nextLine();
                    if(scanner.hasNextLine()){
                        ArrayList<String> tips=new ArrayList<>();
                         while (scanner.hasNextLine()){
                            if((curLine=scanner.nextLine()).equals("group"))break;
                            else if((curLine.split(":")[0].equals("tip"))){
                                tipTemp=curLine.split(":")[1];
                                tips.add(tipTemp);
                            }
                            else break;
                        }
                        groupInfo=groupInfoTemp.split(",");
                         if(Integer.parseInt(groupInfo[0])==groupID) {
                             int instructorID = Integer.parseInt(groupInfo[1]);
                             String[] clientsArray = clientsTemp.split(",");
                             this.clients.clear();
                             for(String clientID:clientsArray){
                                 if(UniversalMethods.isInteger(clientID))clients.add(allClients.searchForClient(Integer.parseInt(clientID)));
                             }
                             this.time=Integer.parseInt(groupInfo[2]);
                             this.schedules=groupInfo[3];
                             this.instructor=instructors.searchForInstructor(Integer.parseInt(groupInfo[1]));
                             instructions=tips;

                         }
                    }
                }
            }

        }

    }


    public int getGroupID(){return groupID;}
    public Instructor getInstructor(Instructors instructors,Clients clients) throws IOException {
        SearchForClientInProgress(this.groupID,instructors,clients);
     return instructor;
    }
    public int getTime(Instructors instructors,Clients clients) throws IOException {
        SearchForClientInProgress(this.groupID,instructors,clients);
        return time;

    }
    public String getSchedules(Instructors instructors,Clients clients) throws IOException {
        SearchForClientInProgress(this.groupID, instructors, clients);
        return schedules;

    }
    public ArrayList<String> getInstructions(Instructors instructors,Clients clients) throws IOException {
        SearchForClientInProgress(this.groupID, instructors, clients);
        return instructions;

    }
    public ArrayList<Client> getClients(Instructors instructors,Clients clients) throws IOException {
        SearchForClientInProgress(this.groupID, instructors, clients);
        return this.clients;

    }
    public void addClients(int clientID){
        Client client=allClients.searchForClient(clientID);
        if(client!=null)clients.add(client);

    }






}
