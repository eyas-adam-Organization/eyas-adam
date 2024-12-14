

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Clients {


    static String successLoginMessage = "LOGIN SUCCESSFUL: Welcome";
    static String failedUsernameLoginMessage = "LOGIN UNSUCCESSFUL: Username not registered , try another registered username, or restart the program and sign up!";
    static String failedPasswordLoginMessage = "LOGIN UNSUCCESSFUL: Username registered but password wrong! check your password and try again";

    static String successSignupMessage = "SIGNUP SUCCESSFUL: Welcome";
    static String failedUsernameSignupMessage = "SIGNUP UNSUCCESSFUL: Username already used, try another unique username";
    static String failedPasswordSignupMessage = "SIGNUP UNSUCCESSFUL: Password should be at least 8 characters long";

    static int clientLoginCode = 0;
    static int clientProfileCode = 1;
    static int clientSignupCode = 2;


    public String activeUser;
    public int currentMenu;

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


    public String clientSignUp(String username, String password){
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
        activeUser = username;
        currentMenu = clientProfileCode;
        updateMenu();
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
}
