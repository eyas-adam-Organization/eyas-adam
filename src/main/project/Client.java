import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    String username;


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

    public static int DID_NOT_COMPLETE_PROGRAM_MESSAGE = 0;
    public static int RATING_NOT_NUMERICAL_MESSAGE = 1;
    public static int RATING_OUTSIDE_RANGE_MESSAGE = 2;
    public static int REVIEW_BAD_MESSAGE = 3;
    public static int REVIEW_OKAY_MESSAGE = 4;
    public static int REVIEW_GOOD_MESSAGE = 5;

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

    public static String[] REVIEW_REPLY_MESSAGES = {
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
                    "                Thanks for the good review and we hope that you'll continue to enjoy our programs"
    };


    public Client(String username){
        this.username = username;
    }

    public String updateValues(String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
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
        updateToFile(age, weight, BMI, goalBMI, goalWeight, preferences, restrictions);
        return ERROR_MESSAGES[SUCCESS_CODE];
    }

    public static boolean isNotInteger(String string) {
        return !string.matches("-?\\d+");
    }

    public void updateToFile(String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
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

    public boolean wasUpdated(String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions){
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

//    public int writeReview(String username, String program, String rating, String review, String Suggestion){
//
//    }

    public ArrayList<String> getPersonalInfo(){
        ArrayList <String> info = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/clients.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if(array[0].equals(username)){
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
                if (array[1].equals(username)) {
                    info.addAll(Arrays.asList(array).subList(3, 6));
                }
            }
        } catch (FileNotFoundException e) {

        }
        return info;
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

    public String writeReview(String program, String rating, String review, String suggestion){
        if(this.UserDidNotCompleteProgram(program))
            return REVIEW_REPLY_MESSAGES[DID_NOT_COMPLETE_PROGRAM_MESSAGE];
        if(isNotInteger(rating))
            return REVIEW_REPLY_MESSAGES[RATING_NOT_NUMERICAL_MESSAGE];
        if(Integer.parseInt(rating) > 10 || Integer.parseInt(rating) < 0)
            return REVIEW_REPLY_MESSAGES[RATING_OUTSIDE_RANGE_MESSAGE];
        if(Integer.parseInt(rating) >= 0 && Integer.parseInt(rating) <= 3)
            return REVIEW_REPLY_MESSAGES[REVIEW_BAD_MESSAGE];
        if(Integer.parseInt(rating) > 3 && Integer.parseInt(rating) <= 7)
            return REVIEW_REPLY_MESSAGES[REVIEW_OKAY_MESSAGE];

        return REVIEW_REPLY_MESSAGES[REVIEW_GOOD_MESSAGE];
    }

    public boolean UserDidNotCompleteProgram(String program){
        try{
        Scanner scanner = new Scanner(new File("src/main/resources/programs_clients.txt"));
        String curLine;
        while (scanner.hasNextLine()) {
            curLine = scanner.nextLine();
            String[] array = curLine.split(",");
            if(array[0].equals("1") && array[1].equals(username) && array[2].equalsIgnoreCase(program)){
                return false;
            }
        }
    } catch (FileNotFoundException e){

    }
        return true;
    }
}
