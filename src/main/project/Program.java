import java.io.File;
import java.io.FileNotFoundException;
 
import java.lang.reflect.Array;
 
import java.util.ArrayList;
import java.util.Scanner;

public class Program implements Cloneable{
    String title;
    int duration;
    ArrayList <String> reviews = new ArrayList<>();
    int rating = 0;


    public String getFocus() {
        return focus;
    }

    String level;
    String focus;
    File video;
    File image;
    File documents;
    int price;
    private ArrayList<Client> clientsForThisProgram=new ArrayList<>();
    Program(String title,  String level, String goal , int duration, File video,  File image,  File documents, int Price){
        this.title = title;
        this.duration = duration;
        this.level = level;
        this.focus = goal;
        this.video = video;
        this.image = image;
        this.documents = documents;
        price = Price;

    }
    ArrayList<Client> getClients(Clients clients,Programs programs) throws FileNotFoundException {
        clientsForThisProgram.clear();
        for(Client client:clients.getClients())if(client.getProgram(programs)!=null&&client.getProgram(programs).getTitle().equals(this.getTitle()))clientsForThisProgram.add(client);
        return clientsForThisProgram;
    }





    Program(String title,  int duration,  String level,  String goal, int Price){
        this.title = title;
        this.duration = duration;
        this.level = level;
        this.focus = goal;
        this.video = null;
        this.image = null;
        this.documents = null;
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
    public String getTitle(){return title;}
    public String getGoal(){return focus;}
    public int getDuration(){return duration;}
    public String getLevel(){return level;}
    public File getVideo(){return video;}
    public File getImage(){return image;}
    public File getDocuments(){return documents;}
    public int getPrice(){return price;}


    public void setDuration(int duration){this.duration=duration;}
    public void setGoal(String goal){this.focus=goal;}
    public void setLevel(String level){this.level=level;}

    public void setVideo(File video){this.video=video;}
    public void setImage(File image){this.image=image;}
    public void setDocuments(File documents){this.documents=documents;}
    public void setPrice(int price){this.price=price;}
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Calls the Object's clone method
    }

    @Override
    public String toString() {
        return "Program{" + "title='" + title + '\'' + '}';
    }
    @Override
    public boolean equals(Object program1){
        if(this==program1)return true;
        if(program1==null||getClass()!=program1.getClass())return false;
        Program OtherProgram=(Program) program1;
        if(!OtherProgram.getTitle().equals(this.title))return false;
        if(!OtherProgram.getLevel().equals(this.level))return false;
        if(!OtherProgram.getGoal().equals(this.focus))return false;
        if(OtherProgram.getDuration()!=this.getDuration())return false;
        if(OtherProgram.getVideo()==null&&this.getVideo()!=null)return false;
        if(OtherProgram.getVideo()!=null&&this.getVideo()==null)return false;
        if(OtherProgram.getVideo()!=null&&this.getVideo()!=null&&!OtherProgram.getVideo().getPath().equals(this.getVideo().getPath()))return false;
        if(OtherProgram.getImage()==null&&this.getImage()!=null)return false;
        if(OtherProgram.getImage()!=null&&this.getImage()==null)return false;
        if(OtherProgram.getImage()!=null&&this.getImage()!=null&&!OtherProgram.getImage().getPath().equals(this.getImage().getPath()))return false;
        if(OtherProgram.getDocuments()==null&&this.getDocuments()!=null)return false;
        if(OtherProgram.getDocuments()!=null&&this.getDocuments()==null)return false;
        if(OtherProgram.getDocuments()!=null&&this.getDocuments()!=null&&!OtherProgram.getDocuments().getPath().equals(this.getDocuments().getPath()))return false;
        if(OtherProgram.getPrice()!=this.price)return false;
        return true;
    }


 
}
