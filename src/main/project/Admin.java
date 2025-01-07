
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;



public class Admin {

    public static int UPDATE_NO_USER_CODE = 0;
    public static int UPDATE_PASSWORD_SHORT_CODE = 1;
    public static int UPDATE_SUCCESS_CODE = 2;

    public static String DELETE_SUCCESS_MESSAGE = "DELETE SUCCESS: The user was deleted successfully";
    public static String DELETE_FAILED_MESSAGE = "DELETE FAILED: The username provided does not exists";

    public static String [] UPDATE_MESSAGES = {
            "UPDATE FAILED: User name does not exist",
            "UPDATE FAILED: Password entered is very short, should be more than 7 characters",
            "UPDATE SUCCESS: Password was updated successfully"
    };



    public String updateToClient(String username, String password){
        if(!Clients.userExists(username))
            return UPDATE_MESSAGES[UPDATE_NO_USER_CODE];
        if(password.length() <= 7)
            return UPDATE_MESSAGES[UPDATE_PASSWORD_SHORT_CODE];
        changePassword(username, password, true);
        return UPDATE_MESSAGES[UPDATE_SUCCESS_CODE];
    }

    public String updateToInstructor(String username, String password){
        if(!Instructors.instructorExists(username))
            return UPDATE_MESSAGES[UPDATE_NO_USER_CODE];
        if(password.length() <= 7)
            return UPDATE_MESSAGES[UPDATE_PASSWORD_SHORT_CODE];
        changePassword(username, password, true);
        return UPDATE_MESSAGES[UPDATE_SUCCESS_CODE];
    }

    public void changePassword(String username, String password, boolean isClient){
        try {
            String path = "src/main/resources/" + (isClient ? "clients.txt": "instructors.txt");
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(new File(path));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[0].equals(username)){
                    curLine = array[0] + "," + password + curLine.substring(array[0].length() + array[1].length() + 1);
                }
                stringBuilder.append(curLine).append("\n");
            }
            Files.write(Paths.get(path), stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteClient(String username){
        if(!Clients.userExists(username))
            return DELETE_FAILED_MESSAGE;
        deleteUserFromFile(username, true);
        return DELETE_SUCCESS_MESSAGE;
    }
    public String deleteInstructor(String username){
        if(!Instructors.instructorExists(username))
            return DELETE_FAILED_MESSAGE;
        deleteUserFromFile(username, false);
        return DELETE_SUCCESS_MESSAGE;
    }


    public void deleteUserFromFile(String username, Boolean isClient){
        try {
            String path = "src/main/resources/" + (isClient ? "clients.txt" : "instructors.txt");
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(new File(path));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[0].equals(username)){
                    Files.write(Paths.get("src/main/resources/test_archive.txt"), curLine.getBytes(StandardCharsets.UTF_8));
                    continue;
                }
                stringBuilder.append(curLine).append("\n");
            }
            Files.write(Paths.get(path), stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUserBackFromArchive(boolean isClient){
        try {
            String path = "src/main/resources/" + (isClient ? "clients.txt" : "instructors.txt");
            String string = new Scanner(new File("src/main/resources/test_archive.txt")).nextLine() + "\n";
            Files.write(Paths.get(path), string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveApprovedToFile(String type, String data, String instructor){
        try {
            String string = instructor + "," + type + "," + data + "\n";
            Files.write(Paths.get("src/main/resources/articles_tips_recipes.txt"), string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean approvedSaved(String type, String data, String instructor) {
        boolean exists = false;

        try {
            String string = instructor + "," + type + "," + data;
            Scanner scanner = new Scanner(new File("src/main/resources/articles_tips_recipes.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                if (curLine.equals(string))
                    exists = true;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return exists;
    }
    public void GenerateReports(Clients clients,Programs programs,int type, String path) throws FileNotFoundException {
        programs.getPrograms().sort(Comparator.comparing(Program::getTitle));
        clients.getClients().sort(Comparator.comparing(Client::getID));
        if(type==0)GenerateProgressReports(  clients,  programs, path);
        else if(type==1)GenerateRevenueReports(  clients,  programs, path);
    }

    private void GenerateProgressReports(Clients clients,Programs programs,String path) throws FileNotFoundException {




    }




    private void GenerateRevenueReports(Clients clients,Programs programs, String path){




    }
    public String ViewTheMostPopularProgramsByEnrollment(String type,ArrayList<ProgramData>  programsToView,Clients clients, Programs programs){
        if(!UniversalMethods.isInteger(String.valueOf(type)))return "wrong type";
        else {
            int Type=Integer.parseInt(type);
            if(Type<0||Type>1)return "wrong type";
            if(Type==0){
                programsToView.clear();
                ArrayList<Program> programsTemp=new ArrayList<>(programs.getPrograms());
                programsTemp.sort(Comparator.comparingInt((Program p)-> {
                    try {
                        return getClients(clients, programs,p).size();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).reversed().thenComparing(Program::getTitle));
                for(Program program:programsTemp) {
                    try {
                        programsToView.add(new ProgramData(program.getTitle(),getClients(clients,programs,program).size()));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                return "viewed successfully";
            }
            programsToView.clear();
            ArrayList<Program> programsTemp=new ArrayList<>(programs.getPrograms());
            programsTemp.sort(Comparator.comparingInt(Program::getPrice).reversed().thenComparing(Program::getTitle));
            for(Program program:programsTemp)programsToView.add(new ProgramData(program.getTitle(),program.getPrice()));
            return "viewed successfully";
        }

    }
    public ProgramStatistics ViewStatisticsOnAProgram(String programTitle ,Clients clients,Programs programs) throws FileNotFoundException {
        String message;
        ArrayList<ClientStatistics> clientStatistics;
        Program program=programs.searchForProgram(programTitle);
        if(program==null){
            message="this program does not exist";
            clientStatistics=new ArrayList<>();
            return new ProgramStatistics(clientStatistics,message);
        }
        else{
            ArrayList<Client> clientsForTheProgram;
            if((clientsForTheProgram= getClients(clients, programs , program)).isEmpty()){
                message="this program does not have any clients";
                clientStatistics=new ArrayList<>();
                return new ProgramStatistics(clientStatistics,message);
            }
            clientStatistics=new ArrayList<>();
            int clientID;
            int attendance;
            int completion;
            message="there is an error in the details of client";
            for(Client client:clientsForTheProgram){
                clientID=client.getID();
                attendance=client.getAttendanceRecord();
                completion=client.getCompletionRate();
                if(attendance<0 || completion<0)message+=" "+clientID;
                else  clientStatistics.add(new ClientStatistics(clientID,completion,attendance));
            }
            if(message.equals("there is an error in the details of client"))message="the of "+program.getTitle()+" is viewed";
            return new ProgramStatistics(clientStatistics,message);
        }
    }
    public static ArrayList<Client>  getClients(Clients clients,Programs programs,Program program) throws FileNotFoundException {
        program.clientsForThisProgram.clear();
        for(Client client:clients.getClients())if(client.getProgram(programs)!=null&&client.getProgram(programs).getTitle().equals(program.getTitle()))program.clientsForThisProgram.add(client);
        return program.clientsForThisProgram;
    }



}
