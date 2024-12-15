

import io.cucumber.datatable.internal.difflib.StringUtills;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Clients {


    static String successLoginMessage = "LOGIN SUCCESSFUL: Welcome";
    static String failedUsernameLoginMessage = "LOGIN UNSUCCESSFUL: Username not registered , try another registered username, or restart the program and sign up!";
    static String failedPasswordLoginMessage = "LOGIN UNSUCCESSFUL: Username registered but password wrong! check your password and try again";

    static String successSignupMessage = "SIGNUP SUCCESSFUL: Welcome";
    static String failedUsernameSignupMessage = "SIGNUP UNSUCCESSFUL: Username already used, try another unique username";
    static String failedPasswordSignupMessage = "SIGNUP UNSUCCESSFUL: Password should be at least 8 characters long";
    static String failedProgramSignupMessage = "SIGNUP UNSUCCESSFUL: Program name chosen is not registered, please chose a registered program name";

    static int clientLoginCode = 0;
    static int clientProfileCode = 1;
    static int clientSignupCode = 2;


    public String activeUser;
    public int currentMenu;



    public ArrayList<Program> getPrograms(String levelFilter, String focusFilter) throws FileNotFoundException {
        ArrayList<Program> programs = getAllPrograms();
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
        return programs;
    }

    public ArrayList<Program> getAllPrograms() throws FileNotFoundException {
        ArrayList<Program> programs = new ArrayList<>();
        File file = new File("src/main/resources/programs.txt");
        Scanner scanner = new Scanner(file);
        String curLine;
        while (scanner.hasNextLine()){
            curLine = scanner.nextLine();
            String [] array =  curLine.split(",");
            Program program = new Program(array);
            programs.add(program);
        }
        return programs;
    }





    public String clientLogin(String username, String password){
        if(!userExists(username)){
            currentMenu = clientLoginCode;
            updateMenu();
            return failedUsernameLoginMessage;
        }
        if(!checkPassword(username, password)){
            currentMenu = clientLoginCode;
            updateMenu();
            return failedPasswordLoginMessage;
        }
        activeUser = username;
        currentMenu = clientProfileCode;
        updateMenu();
        return successLoginMessage;
    }


    public String clientSignUp(String username, String password, String program){
        if (userExists(username)){
            currentMenu = clientSignupCode;
            updateMenu();
            return failedUsernameSignupMessage;
        }
        if (passwordInvalid(password)){
            currentMenu = clientSignupCode;
            updateMenu();
            return failedPasswordSignupMessage;
        }
        if (!programRegistered(program)){
            currentMenu = clientSignupCode;
            updateMenu();
            return failedProgramSignupMessage;
        }
        activeUser = username;
        currentMenu = clientProfileCode;
        updateMenu();
        addClientToProgram(username, program);
        //addUserToFile(username, password);
        return successSignupMessage;
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

    public Client getClientByUsername(String username){
        return new Client(username);
    }

}
