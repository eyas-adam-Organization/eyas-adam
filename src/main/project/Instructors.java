import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instructors {
    private ArrayList<Instructor> instructors;
    public Instructors() throws FileNotFoundException {
        Scanner scanner=new Scanner(new File("src/main/resources/instructors.txt"));
        instructors=new ArrayList<>();
        String curLine;
        while (scanner.hasNextLine()){
            curLine=scanner.nextLine();
            String[]array=curLine.split(",");
             if(UniversalMethods.isInteger(array[0])){
                 Instructor instructor=new Instructor(Integer.parseInt(array[0]));
                 instructors.add(instructor);
             }
        }
    }
    public Instructor searchForInstructor(int ID){
        for (Instructor instructor:instructors)if(instructor.getInstructorID()==ID)return instructor;
        return null;

    }
}
