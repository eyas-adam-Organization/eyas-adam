

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Clients {
    String activeUser = null;

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

    public static boolean passwordInvalid(String password){
        return !(password.length() >= 8);
    }

    public String getActiveUser(){
        return this.activeUser;
    }

}
