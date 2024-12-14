

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    String name;
    int age;
    int weight;
    int BMI;



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
}
