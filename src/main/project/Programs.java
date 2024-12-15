import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Programs {
    public static boolean doesProgramExist(String program){
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/programs.txt"));
            String curLine;
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                String[] array = curLine.split(",");
                if (array[0].equalsIgnoreCase(program)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return false;
    }

}
