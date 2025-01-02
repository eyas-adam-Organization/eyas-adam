
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instructor {
    private String InstructorName;
    private String InstructorPass;
    private int instructorID;

    public Instructor(int instructorID){
        this.instructorID=instructorID;
    }
    public int getInstructorID(){return instructorID;}


}

