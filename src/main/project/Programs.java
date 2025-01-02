 import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import io.cucumber.java.be.I;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

 
    private ArrayList<Program> programs;
    public Programs() throws FileNotFoundException, MalformedURLException {
        programs=new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/programsss.txt"));
        String curLine;
        while (scanner.hasNextLine()){
            curLine = scanner.nextLine();
            String [] array =  curLine.split(",");
            File video;
            File image;
            File doc;


                if (array[4].equals("null")) {
                      video = null;
                } else {
                     video=new File(array[4]);
            }
                if (array[5].equals("null")) {
                    image = null;
                } else {
                     image=new File(array[5]);
                }
                if (array[6].equals("null")) {
                    doc = null;
                } else {
                     doc=new File(array[6]);
                }
                Program program=new Program( array[0] , array[1],array[2],Integer.parseInt(array[3]),video,image,doc,Integer.parseInt(array[7]));

                programs.add(program);
        }

    }
    public String CreateProgram(String title,  String level, String goal , String duration, String video,  String image,  String documents, String Price)  {
        for(Program program:programs){
            if(program.getTitle().equals(title))return "the program is already added";
        }
       ArrayList<String> errorsInFiles=CheckPaths(video,image,documents);
         if(!errorsInFiles.isEmpty()){
             String errorMessage="";
             for(String error:errorsInFiles)errorMessage=errorMessage+error+" & ";
             return errorMessage.substring(0,errorMessage.length()-3);
         }

        Program program=new Program(title , level ,goal,Integer.parseInt(duration),video.equals("null")?null:new File(video),image.equals("null")?null:new File(image),documents.equals("null")?null:new File(documents), Integer.parseInt(Price));
        String programToFile=title+","+level+","+goal+","+duration+","+video+","+image+","+documents+","+Price;
         try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/programsss.txt", true))) {
            writer.write(programToFile);
            writer.newLine(); // Add a new line for better formatting
         } catch (IOException e) {
            e.printStackTrace();
        }
         programs.add(program);
         return "the program has been add successfully";
    }
    public String UpdateProgram(String title,  String level, String goal , String duration, String video,  String image,  String documents, String price)  {
        boolean flag=false;
        Program toUpdatPrograme = null;
        for(Program program:programs){
            if (program.getTitle().equals(title)) {
                flag = true;
                toUpdatPrograme=program;
                break;
            }
        }
        if(!flag)return "the program does not exist";
        ArrayList<String> errorsInFiles=CheckPaths(video,image,documents);
        if(!errorsInFiles.isEmpty()){
            String errorMessage="";
            for(String error:errorsInFiles)errorMessage=errorMessage+error+" & ";
            return errorMessage.substring(0,errorMessage.length()-3);
        }
        toUpdatPrograme.setDuration(Integer.parseInt(duration));
        toUpdatPrograme.setGoal(goal);
        toUpdatPrograme.setPrice(Integer.parseInt(price));
        toUpdatPrograme.setLevel(level);
        toUpdatPrograme.setVideo(video.equals("null")?null:new File(video));
        toUpdatPrograme.setImage(image.equals("null")?null:new File(image));
        toUpdatPrograme.setDocuments(documents.equals("null")?null:new File(documents));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/programsss.txt"))) {
            String videoPath;
            String docPath;
            String imagePath;
            for(Program program:programs){
                if(program.getVideo()==null)videoPath="null";
                else videoPath=program.getVideo().getPath();
                if(program.getImage()==null)imagePath="null";
                else imagePath=program.getImage().getPath();
                if(program.getDocuments()==null)docPath="null";
                else docPath=program.getDocuments().getPath();

                String programToFile=program.getTitle()+","+program.getLevel()+","+program.getGoal()+","+program.getDuration()+","+videoPath+","+imagePath+","+docPath+","+program.getPrice();
                writer.write(programToFile);
                writer.newLine(); // Add a new line for better formatting
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "the program has been updated successfully";
    }

    public String DeleteProgram(String title)  {
        boolean flag=false;
        Program toDeletePrograme = null;
        for(Program program:programs){
            if (program.getTitle().equals(title)) {
                flag = true;
                toDeletePrograme=program;
                break;
            }
        }
        if(!flag)return "the program does not exist";
        programs.remove(toDeletePrograme);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/programsss.txt"))) {
            String videoPath;
            String docPath;
            String imagePath;
            for(Program program:programs){
                if(program.getVideo()==null)videoPath="null";
                else videoPath=program.getVideo().getPath();
                if(program.getImage()==null)imagePath="null";
                else imagePath=program.getImage().getPath();
                if(program.getDocuments()==null)docPath="null";
                else docPath=program.getDocuments().getPath();

                String programToFile=program.getTitle()+","+program.getLevel()+","+program.getGoal()+","+program.getDuration()+","+videoPath+","+imagePath+","+docPath+","+program.getPrice();
                writer.write(programToFile);
                writer.newLine(); // Add a new line for better formatting
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "the program has been removed successfully";
    }
    private ArrayList<String> CheckPaths(String video,  String image,  String documents){
        ArrayList<String>errorsInFiles=new ArrayList<>();
        boolean videoFlag = false;
        boolean imageFlag = false;
        boolean docFlag = false;
        try {
            // Check if the path exists and points to a file
            videoFlag=Files.exists(Paths.get(video)) && Files.isRegularFile(Paths.get(video));
        } catch (Exception e) {
            // Catch exceptions (e.g., invalid paths)
            errorsInFiles.add("video/wrong file");
        }
        if(videoFlag){
            String extension=video.split("\\.")[1];
            if(!(extension.equals("mp4")||extension.equals("webm")))errorsInFiles.add("video/wrong extension");
        }
        else if(!errorsInFiles.contains("video/wrong file")) errorsInFiles.add("video/wrong file");
        if(video.equals("null"))errorsInFiles.remove(0);



        try {
            // Check if the path exists and points to a file
            imageFlag=Files.exists(Paths.get(image)) && Files.isRegularFile(Paths.get(image));
        } catch (Exception e) {
            // Catch exceptions (e.g., invalid paths)
            errorsInFiles.add("image/wrong file");
        }
        if(imageFlag){
            String extension=image.split("\\.")[1];
            if(!(extension.equals("png")||extension.equals("jpeg")||extension.equals("jpg")))errorsInFiles.add("image/wrong extension");
        }
        else if(!errorsInFiles.contains("image/wrong file"))  errorsInFiles.add("image/wrong file");
        if(image.equals("null"))errorsInFiles.remove(errorsInFiles.size()-1);



        try {
            // Check if the path exists and points to a file
            docFlag=Files.exists(Paths.get(documents)) && Files.isRegularFile(Paths.get(documents));

        } catch (Exception e) {
            // Catch exceptions (e.g., invalid paths)
            errorsInFiles.add("doc/wrong file");
        }
        if(!docFlag && !errorsInFiles.contains("doc/wrong file"))errorsInFiles.add("doc/wrong file");
        if(documents.equals("null"))errorsInFiles.remove(errorsInFiles.size()-1);

        return errorsInFiles;

    }
    public Program searchForProgram(String title){
        for (Program program:programs)if(program.getTitle().equals(title))return program;
        return null;
    }
    public ArrayList<Program> getPrograms(){return  programs;}


 
}
