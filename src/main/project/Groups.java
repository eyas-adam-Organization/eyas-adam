import io.cucumber.java.be.I;
import io.cucumber.java.sl.In;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Groups {
    private ArrayList<Group> groups;
    private Instructors instructors;
    Clients allClients;

    public Groups() throws IOException {
        groups=new ArrayList<>();


     // printt();
    }
       public void loadFromFile(Instructors instructors,Clients allClients) throws FileNotFoundException {
           this.instructors= instructors;
           this.allClients=allClients;
        Scanner scanner=new Scanner((new File("src/main/resources/groups.txt")));
        String curLine = "";
        String groupInfoTemp ="";
        String clientsTemp="";
        String tipTemp;
        String[]groupInfo;

        while (scanner.hasNextLine()){
            if(!curLine.equals("group"))curLine=scanner.nextLine();
            if(curLine.equals("group")){
                if(scanner.hasNextLine())groupInfoTemp=scanner.nextLine();
                if(scanner.hasNextLine())clientsTemp=scanner.nextLine();
                if(scanner.hasNextLine()){
                    ArrayList<String> tips=new ArrayList<>();
                    ArrayList<Client> clients=new ArrayList<>();
                    while (scanner.hasNextLine()){
                        if((curLine=scanner.nextLine()).equals("group"))break;
                        else if((curLine.split(":")[0].equals("tip"))){
                            tipTemp=curLine.split(":")[1];
                            tips.add(tipTemp);
                        }
                        else break;
                    }
                    String [] clientsArray=clientsTemp.split(",");

                    for(String clientID:clientsArray){
                        if(UniversalMethods.isInteger(clientID)) clients.add(allClients.searchForClient(Integer.parseInt(clientID)));
                    }
                    groupInfo=groupInfoTemp.split(",");
                    int instructorID=Integer.parseInt(groupInfo[1]);
                    Group group=new Group(Integer.parseInt(groupInfo[0]),instructors.searchForInstructor(instructorID),Integer.parseInt(groupInfo[2]),groupInfo[3],clients,tips);
                    groups.add(group);
                }
            }
        }
    }
    public Group SearchForGroup(int ID) throws IOException {
        loadFromFile(instructors,allClients);
          for(Group group:groups){
             if(group.getGroupID()==ID)return group;
        }
        return null;
    }

    public String CreateGroup(String groupID,String instructorID,String time,String schedules) throws IOException {
        if (!UniversalMethods.isInteger(groupID))return "wrong data";
        if(this.SearchForGroup(Integer.parseInt(groupID))!=null)return "this group already exist";
        if (!UniversalMethods.isInteger(instructorID))return "wrong data";
        if(instructors.searchForInstructor(Integer.parseInt(instructorID))==null)return "this instructor does not exist";
        if(!UniversalMethods.isInteger(time))return "wrong data";
        if(Integer.parseInt(time)<0||Integer.parseInt(time)>24)return  "wrong data";
        if (!(schedules.equals("online")||schedules.equals("in-person")))return "wrong data";
        Group group=new Group(Integer.parseInt(groupID),instructors.searchForInstructor(Integer.parseInt(instructorID)),Integer.parseInt(time),schedules,new ArrayList<>(),new ArrayList<>());
        groups.add(group);
        writeToGroupsFile(group);
        return "a new group has been created";
    }
    public void writeToGroupsFile(Group group) throws IOException {
        Scanner scanner=new Scanner(new File("src/main/resources/groups.txt"));
        String curLine = "";
        String groupInfoTemp ="";
        String clientsTemp="";
        String tipTemp;
        String[]groupInfo;
        ArrayList<Group> toWriteGroups=new ArrayList<>();

        while (scanner.hasNextLine()){
            if(!curLine.equals("group"))curLine=scanner.nextLine();
            if(curLine.equals("group")){
                if(scanner.hasNextLine())groupInfoTemp=scanner.nextLine();
                if(scanner.hasNextLine())clientsTemp=scanner.nextLine();
                if(scanner.hasNextLine()){
                    ArrayList<String> tips=new ArrayList<>();
                    ArrayList<Client> clients=new ArrayList<>();
                    while (scanner.hasNextLine()){
                        if((curLine=scanner.nextLine()).equals("group"))break;
                        else if((curLine.split(":")[0].equals("tip"))){
                            tipTemp=curLine.split(":")[1];
                            tips.add(tipTemp);
                        }
                        else break;
                    }
                    String [] clientsArray=clientsTemp.split(",");
                    for(String clientID:clientsArray)if(UniversalMethods.isInteger(clientID))clients.add(allClients.searchForClient(Integer.parseInt(clientID)));
                    groupInfo=groupInfoTemp.split(",");
                    int instructorID=Integer.parseInt(groupInfo[1]);
                    Group groupTemp=new Group(Integer.parseInt(groupInfo[0]),instructors.searchForInstructor(instructorID),Integer.parseInt(groupInfo[2]),groupInfo[3],clients,tips);
//                     if(group.getGroupID()!=groupTemp.getGroupID())
                       toWriteGroups.add(groupTemp);
                }
            }
        }

        for(Group group1:toWriteGroups){
            if(group1.getGroupID()==group.getGroupID()){
                toWriteGroups.remove(group1);
                break;
            }
        }
        toWriteGroups.add(group);

//        for(Group group1:toWriteGroups){
//            System.out.println(group1.getGroupID()+" "+group1.getClients(instructors,allClients).size());
//            for(Client client:group1.getClients(instructors,allClients))System.out.print(client.getID());
//            System.out.println(" ");
//            System.out.println(" ");
//
//        }
        try(BufferedWriter writer=new BufferedWriter(new FileWriter("src/main/resources/groups.txt"))){
            for(Group groupTemp:toWriteGroups){

                writer.write("group");
                writer.newLine();
                groupInfoTemp=groupTemp.getGroupID()+","+groupTemp.getInstructor(instructors,allClients).getInstructorID()+","+groupTemp.getTime(instructors,allClients)+","+group.getSchedules(instructors,allClients);
                writer.write(groupInfoTemp);
                writer.newLine();
                clientsTemp="";
                for (Client client:groupTemp.getClients(instructors,allClients))clientsTemp +=client.getID()+",";
                if(!clientsTemp.isEmpty()) {
                    clientsTemp = clientsTemp.substring(0, clientsTemp.length() - 1);
                    writer.write(clientsTemp);
                }
                else writer.newLine();
                writer.newLine();
                for(String tip:groupTemp.getInstructions(instructors,allClients)){
                    writer.write("tip:"+tip);
                    writer.newLine();
                }
            }
        }

    }
    public void printt() throws IOException {
        for(Group group:groups){
            System.out.println(group.getGroupID()+" "+ group.getInstructor(instructors,allClients).getInstructorID()+" "+group.getTime(instructors,allClients)+" "+group.getSchedules(instructors,allClients));
            for(Client client:group.getClients(instructors,allClients))System.out.print(client.getID()+",");
            System.out.println();
            for (String tip:group.getInstructions(instructors,allClients))System.out.println(tip);
            System.out.println();
        }
    }
    public String AssignClientsToAGroup(String clintID ,String groupID) throws IOException {
        Group group;
        if(!UniversalMethods.isInteger(groupID))return "this group does not exist";
        if(!UniversalMethods.isInteger(clintID))return "this client does not exist";
        if((group=this.SearchForGroup(Integer.parseInt(groupID)))==null)return "this group does not exist";
        if(allClients.searchForClient(Integer.parseInt(clintID))==null)return "this client does not exist";
        for(Group group1:groups){
            if(group1.getGroupID()!=Integer.parseInt(groupID)){for(Client client: group1.getClients(instructors,allClients))if(client.getID()==Integer.parseInt(clintID))return "the client is assign to another group";}

            else if(group1.getGroupID()==Integer.parseInt(groupID)){for(Client client: group1.getClients(instructors,allClients))if(client.getID()==Integer.parseInt(clintID))return "the client is already in the group";}
        }
        group.addClients(Integer.parseInt(clintID));
        writeToGroupsFile(group);
        return "the client have been add to the group";

    }

}
