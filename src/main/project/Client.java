

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

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
    ArrayList <Program> completedPrograms = new ArrayList<>();

    public Client(){
        completedPrograms.add(new Program("Weight Loos Basic",14,"beginner","Weight Loss", null, null, null, 35));
        completedPrograms.add(new Program("Muscle Mastery",28,"advanced","Muscle Building",null,null,null,50));
    }



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

    public String updateValues(String username, String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
        if(isNotInteger(age))
            return ERROR_MESSAGES[ERROR_CODE_AGE_N];

        if(13 > Integer.parseInt(age) || Integer.parseInt(age) > 73)
            return ERROR_MESSAGES[ERROR_CODE_AGE_R];

        if(isNotInteger(weight))
            return ERROR_MESSAGES[ERROR_CODE_WEIGHT_N];

        if(40 > Integer.parseInt(weight) || Integer.parseInt(weight) > 240)
            return ERROR_MESSAGES[ERROR_CODE_WEIGHT_R];

        if(isNotInteger(BMI))
            return ERROR_MESSAGES[ERROR_CODE_BMI_N];

        if(10 > Integer.parseInt(BMI) || Integer.parseInt(BMI) > 70)
            return ERROR_MESSAGES[ERROR_CODE_BMI_R];

        if(isNotInteger(goalBMI))
            return ERROR_MESSAGES[ERROR_CODE_GOAL_BMI_N];

        if(10 > Integer.parseInt(goalBMI) || Integer.parseInt(goalBMI) > 70)
            return ERROR_MESSAGES[ERROR_CODE_GOAL_BMI_R];

        if(isNotInteger(goalWeight))
            return ERROR_MESSAGES[ERROR_CODE_GOAL_WEIGHT_N];

        if(40 > Integer.parseInt(goalWeight) || Integer.parseInt(goalWeight) > 240)
            return ERROR_MESSAGES[ERROR_CODE_GOAL_WEIGHT_R];
        updateToFile(username, age, weight, BMI, goalBMI, goalWeight, preferences, restrictions);
        return ERROR_MESSAGES[SUCCESS_CODE];
    }

    public static boolean isNotInteger(String string) {
        return !string.matches("-?\\d+");
    }

    public static void updateToFile(String username, String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
        Path path = Paths.get("src/main/resources/clients.txt");
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if(array[0].equals(username)){
                    curLine = username + "," + getPassword(username) + "," + age + "," + weight + "," + BMI + "," + goalBMI + "," + goalWeight + "," + preferences + "," + restrictions;
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

    public static boolean wasUpdated(String username, String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
        try {
            String string = username + "," + getPassword(username) + "," + age + "," + weight + "," + BMI + "," + goalBMI + "," + goalWeight + "," + preferences + "," + restrictions;
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                if(curLine.equals(string))
                    return true;
            }
        } catch (FileNotFoundException e){

        }
        return false;
    }
}
