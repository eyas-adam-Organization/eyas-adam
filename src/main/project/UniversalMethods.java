import java.io.File;
import java.io.IOException;
import java.io.*;


public class UniversalMethods {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void getFile(String path){
        File originalFile = new File(path);
        File tempFile = new File("src/main/resources/tempfile.txt");

        try {
            // Step 1: Copy contents from original file to temp file
            copyFile(originalFile, tempFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void returnFile(String path){
        File originalFile = new File(path);
        File tempFile = new File("src/main/resources/tempfile.txt");

        try {
            // Step 1: Copy contents from original file to temp file
            copyFile(tempFile, originalFile);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void copyFile(File source, File destination) throws IOException {
        // Create input and output streams
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024]; // Buffer to hold chunks of data
            int length;

            // Read from source and write to destination
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }


}
 
