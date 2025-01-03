

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import io.cucumber.java.be.I;
import io.cucumber.java.sl.In;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Clients {

    public static int DID_NOT_COMPLETE_PROGRAM_MESSAGE = 0;
    public static int RATING_NOT_NUMERICAL_MESSAGE = 1;
    public static int RATING_OUTSIDE_RANGE_MESSAGE = 2;
    public static int REVIEW_BAD_MESSAGE = 3;
    public static int REVIEW_OKAY_MESSAGE = 4;
    public static int REVIEW_GOOD_MESSAGE = 5;
    public static int REVIEW_INVALID_INPUT_MESSAGE = 6;

    static int CLIENT_SIGNUP_CODE = 0;
    static int CLIENT_LOGIN_CODE = 1;
    static int CLIENT_PROFILE_CODE = 2;
    static int CLIENT_INFO_CODE = 3;
    static int CLIENT_INFO_CHANGE_CODE = 4;
    static int CLIENT_PROGRAMS_CODE = 5;
    static int CLIENT_REVIEW_CODE = 6;
    static int CLIENT_PROGRAMS_JOIN_CODE = 7;

    public static int SUCCESS_CODE = 0;
    public static int ERROR_CODE_AGE_N = 1;
    public static int ERROR_CODE_AGE_R = 2;
    public static int ERROR_CODE_WEIGHT_N = 3;
    public static int ERROR_CODE_WEIGHT_R = 4;
    public static int ERROR_CODE_BMI_N = 5;
    public static int ERROR_CODE_BMI_R = 6;
    public static int ERROR_CODE_GOAL_BMI_N = 7;
    public static int ERROR_CODE_GOAL_BMI_R = 8;
    public static int ERROR_CODE_GOAL_WEIGHT_N = 9;
    public static int ERROR_CODE_GOAL_WEIGHT_R = 10;

    public static int AGE_CODE = 0;
    public static int WEIGHT_CODE = 1;
    public static int BMI_CODE = 2;
    public static int GOAL_BMI_CODE = 3;
    public static int GOAL_WEIGHT_CODE = 4;
    public static int PREFERENCES_CODE = 5;
    public static int RESTRICTIONS_CODE = 6;

    public static int RATING_CODE = 0;
    public static int REVIEW_CODE = 1;
    public static int SUGGESTION_CODE = 2;

    public static int ENROLLMENT_FAIL_PROGRAM_DOES_NOT_EXIST = 0;
    public static int ENROLLMENT_FAIL_ALREADY_IN_PROGRAM = 1;
    public static int ENROLLMENT_SUCCESS = 2;

    static String SUCCESS_LOGIN_MESSAGE = "LOGIN SUCCESSFUL: Welcome";
    static String FAILED_LOGON_USERNAME_MESSAGE = "LOGIN UNSUCCESSFUL: Username not registered , try another registered username, or restart the program and sign up!";
    static String FAILED_LOGIN_PASSWORD_MESSAGE = "LOGIN UNSUCCESSFUL: Username registered but password wrong! check your password and try again";

    static String SUCCESS_SIGNUP_MESSAGE = "SIGNUP SUCCESSFUL: Welcome";
    static String FAILED_SIGNUP_USERNAME_MESSAGE = "SIGNUP UNSUCCESSFUL: Username already used, try another unique username";
    static String FAILED_SIGNUP_PASSWORD_MESSAGE = "SIGNUP UNSUCCESSFUL: Password should be at least 8 characters long";
    static String FAILED_SIGNUP_PROGRAM_MESSAGE = "SIGNUP UNSUCCESSFUL: Program name chosen is not registered, please chose a registered program name";


    public static String [] ENROLLMENT_REPLY_MESSAGES = {
            "ENROLLMENT UNSUCCESSFUL: A program with that name does not exist",
            "ENROLLMENT UNSUCCESSFUL: You are already in the program!",
            "ENROLLMENT SUCCESSFUL: You are now enrolled in the program"
    };

    public static String [] REVIEW_REPLY_MESSAGES = {
            "REVIEW WAS NOT ACCEPTED: You did not complete this program yet, you can review it once you have completed it.",
            "REVIEW WAS NOT ACCEPTED: Please enter a numerical value only for the rating.",
            "REVIEW WAS NOT ACCEPTED: Please enter a value within [0 - 10] for rating.",
            "REVIEW ACCEPTED: We apologize the the program didn't give you satisfaction.\n" +
                    "                Your suggestion will be considered to try improve our programs.\n" +
                    "                Thanks for choosing our services and we hope to satisfy you next time",
            "REVIEW ACCEPTED: Thanks for reviewing our program!\n" +
                    "                It seems that you didn't get the best experience from our program, we apologize.\n" +
                    "                Your suggestion will be considered to try improve our programs." ,
            "REVIEW ACCEPTED: Thanks for reviewing our program!\n" +
                    "                Thanks for the good review and we hope that you'll continue to enjoy our programs",
            "INPUT INVALID: Going back to main profile"
    };

    public static String [] ERROR_MESSAGES = {
            "UPDATE SUCCESS: Data updated successfully",
            "UPDATE FAILED: the value of age should be a numerical value",
            "UPDATE FAILED: the value of age should be within the range [13 - 73]",
            "UPDATE FAILED: the value of weight should be a numerical value",
            "UPDATE FAILED: the value of weight should be within the range [40KG - 240KG]",
            "UPDATE FAILED: the value of BMI should be a numerical value",
            "UPDATE FAILED: the value of BMI should be within the range [10 - 70]",
            "UPDATE FAILED: the value of goal BMI should be a numerical value",
            "UPDATE FAILED: the value of goal BMI should be within the range [10 - 70]",
            "UPDATE FAILED: the value of goal weight should be a numerical value",
            "UPDATE FAILED: the value of goal weight should be within the range [40KG - 240KG]",
    };

    static String [] CLIENT_PROFILE_SEND_TEXTS = {
            "sign up",
            "sign out",
            "main menu",
            "view personal info",
            "change personal info",
            "view programs",
            "write a review",
            "join a program"
    };

    public String activeClient;
    public int currentMenu;

    public void setActiveClient(String username){
        activeClient = username;
    }

    public void sendFromMainMenu (String input){
        if (input.equalsIgnoreCase(CLIENT_PROFILE_SEND_TEXTS[1]) || input.equals("1")){
            currentMenu = 1;
            activeClient = null;
        }
        else if (input.equalsIgnoreCase(CLIENT_PROFILE_SEND_TEXTS[3]) || input.equals("3"))
            currentMenu = 3;
        else if (input.equalsIgnoreCase(CLIENT_PROFILE_SEND_TEXTS[4]) || input.equals("4"))
            currentMenu = 4;
        else if (input.equalsIgnoreCase(CLIENT_PROFILE_SEND_TEXTS[5]) || input.equals("5"))
            currentMenu = 5;
        else if (input.equalsIgnoreCase(CLIENT_PROFILE_SEND_TEXTS[6]) || input.equals("6"))
            currentMenu = 6;
        else if (input.equalsIgnoreCase(CLIENT_PROFILE_SEND_TEXTS[7]) || input.equals("7"))
            currentMenu = 7;
        else
            currentMenu = 2;
        updateMenu();
 

 
     }

    public String writeReview(String program, String rating, String review, String suggestion, boolean goBack){
        if(goBack){
            currentMenu = CLIENT_PROFILE_CODE;
            updateMenu();
            return REVIEW_REPLY_MESSAGES[REVIEW_INVALID_INPUT_MESSAGE];
        }
        if(this.UserDidNotCompleteProgram(program)){
            currentMenu = CLIENT_REVIEW_CODE;
            updateMenu();
            return REVIEW_REPLY_MESSAGES[DID_NOT_COMPLETE_PROGRAM_MESSAGE];
        }
        if(isNotInteger(rating)){
            currentMenu = CLIENT_REVIEW_CODE;
            updateMenu();
            return REVIEW_REPLY_MESSAGES[RATING_NOT_NUMERICAL_MESSAGE];
        }
        if(Integer.parseInt(rating) > 10 || Integer.parseInt(rating) < 0){
            currentMenu = CLIENT_REVIEW_CODE;
            updateMenu();
            return REVIEW_REPLY_MESSAGES[RATING_OUTSIDE_RANGE_MESSAGE];
        }
        if(Integer.parseInt(rating) >= 0 && Integer.parseInt(rating) <= 3){
            currentMenu = CLIENT_PROFILE_CODE;
            writeReviewToFile(program, rating, review, suggestion);
            updateMenu();
            return REVIEW_REPLY_MESSAGES[REVIEW_BAD_MESSAGE];
        }
        if(Integer.parseInt(rating) > 3 && Integer.parseInt(rating) <= 7){
            currentMenu = CLIENT_PROFILE_CODE;
            writeReviewToFile(program, rating, review, suggestion);
            updateMenu();
            return REVIEW_REPLY_MESSAGES[REVIEW_OKAY_MESSAGE];
        }
        currentMenu = CLIENT_PROFILE_CODE;
        writeReviewToFile(program, rating, review, suggestion);
        updateMenu();
        return REVIEW_REPLY_MESSAGES[REVIEW_GOOD_MESSAGE];
    }

    public ArrayList<String> getPersonalInfo(String username){
        ArrayList<String> info = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",", 7);
                if(array[0].equals(username)){
                    for(int i = 1; i < 7; i++){
                        info.add(array[i]);
                    }
                }
            }
        } catch (FileNotFoundException e){

        }
        return info;
    }


    public boolean UserDidNotCompleteProgram(String program){
        try{
            Scanner scanner = new Scanner(new File("src/main/resources/programs_clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if(array[0].equals("1") && array[1].equals(activeClient) && array[2].equalsIgnoreCase(program)){
                    return false;
                }
            }
        } catch (FileNotFoundException e){

        }
        return true;
    }

    public static boolean isNotInteger(String string) {
        return !string.matches("-?\\d+");
    }

    public void writeReviewToFile(String program, String rating, String review, String suggestion){

        try{
            StringBuilder builder = new StringBuilder();
            Scanner scanner = new Scanner(new File("src/main/resources/programs_clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if(array[0].equals("1") && array[1].equals(activeClient) && array[2].equalsIgnoreCase(program)){
                    curLine = "1," + activeClient + "," + program + "," + rating + "," + review + "," + suggestion;
                }
                builder.append(curLine).append("\n");
            }
            Files.write(Paths.get("src/main/resources/programs_clients.txt"), builder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String updateValues(String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
        currentMenu = CLIENT_INFO_CHANGE_CODE;
        if(isNotInteger(age)){
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_AGE_N];
        }

        if(13 > Integer.parseInt(age) || Integer.parseInt(age) > 73) {
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_AGE_R];
        }

        if(isNotInteger(weight)){

            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_WEIGHT_N];
        }

        if(40 > Integer.parseInt(weight) || Integer.parseInt(weight) > 240) {
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_WEIGHT_R];
        }

        if(isNotInteger(BMI)) {
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_BMI_N];
        }

        if(10 > Integer.parseInt(BMI) || Integer.parseInt(BMI) > 70){
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_BMI_R];
        }

        if(isNotInteger(goalBMI)){
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_GOAL_BMI_N];
        }

        if(10 > Integer.parseInt(goalBMI) || Integer.parseInt(goalBMI) > 70){
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_GOAL_BMI_R];
        }

        if(isNotInteger(goalWeight)){
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_GOAL_WEIGHT_N];
        }

        if(40 > Integer.parseInt(goalWeight) || Integer.parseInt(goalWeight) > 240){
            updateMenu();
            return ERROR_MESSAGES[ERROR_CODE_GOAL_WEIGHT_R];
        }
        updateToFile(age, weight, BMI, goalBMI, goalWeight, preferences, restrictions);
        currentMenu = CLIENT_PROFILE_CODE;
        updateMenu();
        return ERROR_MESSAGES[SUCCESS_CODE];
    }

//    public boolean wasUpdated(String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
//        try {
//            String string = activeClient + "," + getPassword(activeClient) + "," + age + "," + weight + "," + BMI + "," + goalBMI + "," + goalWeight + "," + preferences + "," + restrictions;
//            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
//            String curLine;
//            while (scanner.hasNextLine()) {
//                curLine = scanner.nextLine();
//                if(curLine.equals(string))
//                    return true;
//            }
//        } catch (FileNotFoundException e){
//
//        }
//        return false;
//    }

    public void updateToFile(String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
        Path path = Paths.get("src/main/resources/clients.txt");
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if(array[0].equals(activeClient)){
                    curLine = activeClient + "," + getPassword(activeClient) + "," + age + "," + weight + "," + BMI + "," + goalBMI + "," + goalWeight + "," + preferences + "," + restrictions;
                }
                stringBuilder.append(curLine).append("\n");
            }
            Files.write(path, stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPassword(String username){
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if(array[0].equals(username))
                    return array[1];
            }
        } catch (FileNotFoundException e){

        }
        return null;
    }

    public ArrayList<Program> getPrograms(String levelFilter, String focusFilter, boolean goBack){
        if(goBack){
            currentMenu = CLIENT_PROFILE_CODE;
            updateMenu();
            return null;
        }
        ArrayList<Program> programs = getAllPrograms();
        currentMenu = CLIENT_PROGRAMS_CODE;
        if(levelFilter == null && focusFilter == null){
            updateMenu();
            return programs;
        }
        ArrayList<Program> tempList = new ArrayList<>();

        if(levelFilter != null){
            for(Program program: programs){
                if(levelFilter.equalsIgnoreCase(program.getLevel())){
                    tempList.add(program);
                }
            }
            programs = new ArrayList<>(tempList);
        }
        if (focusFilter != null){
            tempList = new ArrayList<>();
            for(Program program: programs){
                if(focusFilter.equalsIgnoreCase(program.getFocus())){
                    tempList.add(program);
                }
            }
            programs = new ArrayList<>(tempList);
        }
        updateMenu();
        return programs;
    }

    public ArrayList<Program> getAllPrograms(){
        ArrayList<Program> programs = new ArrayList<>();
        try {
            File file = new File("src/main/resources/programs.txt");
            Scanner scanner = new Scanner(file);
            String curLine;
            while (scanner.hasNextLine()){
                curLine = scanner.nextLine();
                String [] array =  curLine.split(",");
                Program program = new Program(array);
                programs.add(program);
            }
        } catch (FileNotFoundException e){

        }
        return programs;
    }

    public String clientLogin(String username, String password){
        if(!userExists(username)){
            currentMenu = CLIENT_LOGIN_CODE;
            updateMenu();
            return FAILED_LOGON_USERNAME_MESSAGE;
        }
        if(!checkPassword(username, password)){
            currentMenu = CLIENT_LOGIN_CODE;
            updateMenu();
            return FAILED_LOGIN_PASSWORD_MESSAGE;
        }
        activeClient = username;
        currentMenu = CLIENT_PROFILE_CODE;
        updateMenu();
        return SUCCESS_LOGIN_MESSAGE;
    }

    public String clientSignUp(String username, String password, String program){
        if (userExists(username)){
            currentMenu = CLIENT_SIGNUP_CODE;
            updateMenu();
            return FAILED_SIGNUP_USERNAME_MESSAGE;
        }
        if (passwordInvalid(password)){
            currentMenu = CLIENT_SIGNUP_CODE;
            updateMenu();
            return FAILED_SIGNUP_PASSWORD_MESSAGE;
        }
        if (!programRegistered(program)){
            currentMenu = CLIENT_SIGNUP_CODE;
            updateMenu();
            return FAILED_SIGNUP_PROGRAM_MESSAGE;
        }
        activeClient = username;
        currentMenu = CLIENT_PROFILE_CODE;
        updateMenu();
        addClientToProgram(username, program);
        //addUserToFile(username, password);
        return SUCCESS_SIGNUP_MESSAGE;
    }

    public static boolean userExists(String username) {
        boolean exists = false;
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[0].equals(username))
                    exists = true;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return exists;
    }

     private ArrayList<Client> clients;
     private Programs programs;
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


    public static boolean checkPassword(String username, String password){
        boolean passwordCorrect = false;
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[0].equals(username))
                    if(array[1].equals(password)){
                        passwordCorrect = true;
                    }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return passwordCorrect;
    }

    public static boolean passwordInvalid(String password){
        return !(password.length() >= 8);
    }

    private boolean programRegistered(String program) {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/programs.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[0].equals(program)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return false;
    }

    public static void addUserToFile(String username, String password){
        try {
            String string = username + "," + password + "," + "None" + "," + "None" + "," + "None" + "," + "None" + "\n";
            Files.write(Paths.get("src/main/resources/clients.txt"), string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void updateMenu(){

    }

    public static void addClientToProgram(String username, String program){
        try {
            String string = "0," + username + "," + program + "\n";
            Files.write(Paths.get("src/main/resources/programs_clients.txt"), string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void removeClientFromProgram(String username, String program){
        StringBuilder string = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/programs_clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[1].equals(username) && array[2].equalsIgnoreCase(program)){
                    continue;
                }
                string.append(curLine);
                string.append("\n");
            }
            Files.write(Paths.get("src/main/resources/programs_clients.txt"), string.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isClientInProgram(String username, String program){
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/programs_clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[1].equals(username) && array[2].equalsIgnoreCase(program)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return false;
    }

     public static boolean didClientNotCompleteProgram(String username, String program){
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/programs_clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[0].equals("0") && array[1].equals(username) && array[2].equalsIgnoreCase(program)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<String> getPersonalInfo(){
        ArrayList <String> info = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if(array[0].equals(activeClient)){
                    info.addAll(Arrays.asList(array).subList(2, 9));
                }
            }
        } catch (FileNotFoundException e){

        }
        return info;
    }

    public ArrayList<String> getReview() {
        ArrayList<String> info = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/programs_clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[1].equals(activeClient)) {
                    info.addAll(Arrays.asList(array).subList(3, 6));
                }
            }
        } catch (FileNotFoundException e) {

        }
        return info;
    }

    public String enrollInProgram(String program){
        if(!Programs.doesProgramExist(program)){
            currentMenu = CLIENT_PROGRAMS_JOIN_CODE;
            updateMenu();
            return ENROLLMENT_REPLY_MESSAGES[ENROLLMENT_FAIL_PROGRAM_DOES_NOT_EXIST];
        }
        if(Clients.didClientNotCompleteProgram(activeClient, program)){
            currentMenu = CLIENT_PROGRAMS_JOIN_CODE;
            updateMenu();
            return ENROLLMENT_REPLY_MESSAGES[ENROLLMENT_FAIL_ALREADY_IN_PROGRAM];
        }
        try {
            String string = "0," + activeClient + "," + program + "\n";
            Files.write(Paths.get("src/main/resources/programs_clients.txt"), string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e){

        }
        currentMenu = CLIENT_PROFILE_CODE;
        updateMenu();
        return ENROLLMENT_REPLY_MESSAGES[ENROLLMENT_SUCCESS];
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
