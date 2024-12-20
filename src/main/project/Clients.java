

import io.cucumber.java.be.I;
import io.cucumber.java.sl.In;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Clients {
    String activeUser = null;
     private ArrayList<Client>clients;
     private Programs programs;
    public String clientSignUp(String username, String password) throws FileNotFoundException {
        if (userExists(username))
            return "LOGIN UNSUCCESSFUL: Username already used, try another unique username";
        if (passwordInvalid(password))
            return "LOGIN UNSUCCESSFUL: Password should be at least 8 characters long";
        this.activeUser = username;
        return "LOGIN UNSUCCESSFUL: Welcome " + username;
    }

    public static boolean userExists(String username) throws FileNotFoundException {
        boolean exists = false;
        Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
        String curLine;
        while (scanner.hasNextLine()){
            curLine = scanner.nextLine();
            String [] array =  curLine.split(",");
            if(array[0].equals(username))
                exists = true;
        }
        return exists;
    }
    public Clients() throws IOException {
        clients=new ArrayList<>();
        programs=new Programs();

        Scanner scanner= new Scanner(new File("src/main/resources/clients_with_progress.txt"));
        String curLine;
        while (scanner.hasNextLine()){
            curLine=scanner.nextLine();
            String[]array=curLine.split(",");
            String programTitil;
            if(UniversalMethods.isInteger(array[0])){
                Client client=new Client(Integer.parseInt(array[0]),"---");
                client.setAttendanceRecord(client.getAttendanceRecord());
                client.setCompletionRate(client.getCompletionRate());
                client.setMotivationalReminder(client.getMotivationalReminder());
                programTitil=array[5];
                Program program=programs.searchForProgram(programTitil);
                client.setProgram(program);
                client.setNotification(client.getNotification());


                clients.add(client);
            }

        }
    }
    public Programs getPrograms(){return programs;}


    public static boolean passwordInvalid(String password){
        return !(password.length() >= 8);
    }

    public String getActiveUser(){
        return this.activeUser;
    }

    public String MonitorClientProgress(String id) throws FileNotFoundException {
        if(!UniversalMethods.isInteger(id))return "Invalid client ID format";
        else {
            int ID= Integer.parseInt(id);
            int CompletionRate = -2;
            int AttendanceRecord=-2;

            for(Client client:clients){
                if(client.getID()==ID){
                    CompletionRate=client.getCompletionRate();
                    AttendanceRecord=client.getAttendanceRecord();
                    break;
                }
            }
            if(CompletionRate==-1||AttendanceRecord==-1)return"Progress data is incomplete for this client";
            else if(CompletionRate==-2||AttendanceRecord==-2)return"this client does not exist";
            else {
                return CompletionRate+","+AttendanceRecord;
            }

        }

    }

    public String EditTheProgressOfAClient(String typeStr , String valueStr,String id) throws IOException {

        if (!UniversalMethods.isInteger(id)) return "Invalid client ID format";
        else {
            int ID = Integer.parseInt(id);
            Client client = null;
            for (Client temp : clients) {
                if (temp.getID() == ID) {
                    client = temp;
                    break;
                }
            }
            if (client == null) return "This client does not exist";

            if (!UniversalMethods.isInteger(typeStr)) return "invalid type";
            else {
                int type = Integer.parseInt(typeStr);
                if (type > 3 || type < 1) return "invalid type";
                if (type == 1 || type == 2) {
                    if (!UniversalMethods.isInteger(valueStr)) return "invalid data must be a positive number";
                    else {
                        int value = Integer.parseInt(valueStr);
                        if (value < 0) return "invalid data must be a positive number";
                        else {
                            if (type == 1) client.setNumOfDaysAttended(value);
                            else client.setNumOfDaysMessed(value);
                            return "Progress updated successfully";
                        }
                    }

                }
                if (!UniversalMethods.isInteger(valueStr)) return "invalid data must be between 0 and 100";
                else {
                    int value = Integer.parseInt(valueStr);
                    if (value < 0 || value > 100) return "invalid data must be between 0 and 100";
                    else {
                        client.setCompletionRate(value);
                        return "Progress updated successfully";
                    }

                }
            }
        }
    }
    private void writeToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/clients_with_progress.txt"))) {
            for(Client client:clients){
                String clientToFile=client.getID()+","+client.getNumOfDaysAttended()+","+client.getNumOfDaysMessed()+","+client.getCompletionRate()+","+client.getMotivationalReminder();
                writer.write(clientToFile);
                writer.newLine();
            }
        }

        }

    public String SendMotivationalReminder(String limit) throws IOException {
        if(!UniversalMethods.isInteger(limit))return "wrong format of limit";
        if(Integer.parseInt(limit)<0||Integer.parseInt(limit)>100)return "wrong format of limit";

        for(Client client:clients){
            int Limit=Integer.parseInt(limit);
             if(client.getCompletionRate() <= Limit){
                client.setMotivationalReminder("reserve");
            }
            else client.setMotivationalReminder("no reserve");
        }
        return "Motivational Reminder has been sent";

    }
    public ArrayList<Client>getClients(){return clients;}
    public Client searchForClient(int ID){
        for (Client client:clients)if(client.getID()==ID)return client;
        return null;
    }


    public String NotifyClients(Program programBefore , Program programAfter) throws IOException {
        if (programAfter == null && programBefore != null) {
            boolean flag = false;
            for (Client client : clients) {
                if (client.getProgramTitle().equals(programBefore.getTitle())) {
                    client.setNotification("remove");
                    if (!flag) flag = true;
                }
            }
            if (!flag) return "No clients are enrolled in this program";
            return "The notification has been sent";
        } else if (programAfter != null && programBefore !=null) {
             if (programAfter.equals(programBefore)) return "No change to this program";
            else {
                boolean flag = false;
                for (Client client : clients) {
                     if (client.getProgramTitle().equals(programBefore.getTitle())) {
                         client.setNotification("update");
                         if (!flag) flag = true;
                    }
                }
                if (!flag) return "No clients are enrolled in this program";
                return "The notification has been sent";

                }
             }
        else return "this program does not exist";
         }
         public String AnnounceNewProgramsOrSpecialOffers(String announcementType,String announcement) throws IOException {
        if(announcementType.equals("new program")){
             if(programs.searchForProgram(announcement)==null) {
                 return "this program does not exist";
            }
            else {
                for(Client client:clients) {
                    client.setAnnouncement(announcementType + ":" + announcement);
                 }
                return "Announcement has been sent";
            }
        }
        else if(announcementType.equals("new offer")){
            for(Client client:clients)client.setAnnouncement(announcementType+":"+announcement);
            return "Announcement has been sent";
        }
        else return "wrong Announcement";

    }



}
