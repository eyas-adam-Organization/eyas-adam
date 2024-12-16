import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    String title;
    int duration;
    ArrayList <String> reviews = new ArrayList<>();
    int rating = 0;

    public String getLevel() {
        return level;
    }

    public String getFocus() {
        return focus;
    }

    String level;
    String focus;
    File video;
    File image;
    File documents;
    int price;
    Program(String title,  int duration,  String level,  String goal,  File video,  File image,  File documents, int Price){
        this.title = title;
        this.duration = duration;
        this.level = level;
        this.focus = goal;
        this.video = video;
        this.image = image;
        this.documents = documents;
        price = Price;

    }

    Program(String[] program) {
        this.title = program[0];
        this.duration = Integer.parseInt(program[1]);
        this.level = program[2];
        this.focus = program[3];
        this.video = new File(program[4]);
        this.image = new File(program[5]);
        this.documents = new File(program[6]);
        this.price = Integer.parseInt(program[7]);
    }

}
