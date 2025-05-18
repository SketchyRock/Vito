import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class FileManager {

    //name that created file is always given
    private final static String fileName = "Data.txt";
    private final static String tempFileName = "Temp_Data.txt";

    //getter for Data.txt file name
    public static String getDataFileName() {
        return fileName;
    }

    //getter for Temp_Data.txt file name
    public static String getTempDataFileName() {
        return tempFileName;
    }

    //returns true if a file can be created, false if it already exists
    public static boolean canCreateFile(){
        File file = new File(FileManager.getDataFileName());
        return !file.exists();
    }

    // creates file named Data.txt
    public static void createFile(){
        File file = new File(fileName);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Class FileManager - Method creatFile: Error occured");
            }
        }
    }

    //adds credentials user wants to Data.txt
    public static void addOption(){

            System.out.println("Service Name: ");
            String service = Main.SCANNER.nextLine();

            System.out.println("Username: ");
            String username = Main.SCANNER.nextLine();

            System.out.println("Password: ");
            String password = Main.SCANNER.nextLine();

            try(FileWriter writer = new FileWriter(FileManager.getDataFileName(), true)){
                writer.write(PasswordBeef.encode(service) + "," + PasswordBeef.encode(username) + "," + PasswordBeef.encode(password));
                writer.write("\n");
                System.out.println("credentials saved");

            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
        }

        public static void addOptionAdminPassword(){

            System.out.println("New Password: ");
            String service = Main.SCANNER.nextLine();

            try(FileWriter writer = new FileWriter(FileManager.getDataFileName(), true)){
                writer.write(PasswordBeef.encode(service) + ",");
                writer.write("\n");
                System.out.println("credentials saved");

            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
        }

        //recieves users desired "service" the want credentials to and returns the service, username and password
    public static void viewOption(){

        System.out.println("Name of Service: ");
        String serviceRequest = Main.SCANNER.nextLine();
        boolean found = false;

        try(Scanner obj = new Scanner(new File(FileManager.getDataFileName()))){
            while(obj.hasNextLine()){
                String currentLine = obj.nextLine();
                if (currentLine.isEmpty()) continue;

                String[] section = currentLine.split(",");
                if(section.length == 3){
                String service = section[0];
                String username = section[1];
                String password = section[2];
                if (PasswordBeef.decode(service).equalsIgnoreCase(serviceRequest)) {
                    System.out.println("=== Credential Found ===");
                    System.out.println("Service:  " + PasswordBeef.decode(service));
                    System.out.println("Username: " + PasswordBeef.decode(username));
                    System.out.println("Password: " + PasswordBeef.decode(password));
                    found = true;
                    break;
                }
                }
            }
            if (!found) {
                    System.out.println("No credential found for: " + serviceRequest);
                }
        } catch (IOException e) {
            System.out.println("Error reading credentials file");
        }
    }


    public static void deleteOption(){
    File originalFile = new File(FileManager.getDataFileName());
    File tempFile = new File(FileManager.getTempDataFileName());

        System.out.println("Name of Service: ");
        String serviceRequest = Main.SCANNER.nextLine();
        boolean deleted = false;

        try(
            Scanner obj = new Scanner(new File(FileManager.getDataFileName()));
            FileWriter writer = new FileWriter(tempFileName);
            ){
            while(obj.hasNextLine()){
                String currentLine = obj.nextLine();

                if (currentLine.isEmpty()) continue;

                String[] section = currentLine.split(",");

                if(section.length == 3 && PasswordBeef.decode(section[0]).trim().equalsIgnoreCase(serviceRequest)){
                    deleted = true;
                    continue;
                }

                writer.write(currentLine + "\n");
            }
            writer.flush();

        } catch (IOException e) {
            System.out.println("Error deleting file content");
        }
        if (originalFile.delete()) {
        if (tempFile.renameTo(originalFile)) {
            if (deleted) {
                System.out.println("Credential for '" + serviceRequest + "' deleted.");
            } else {
                System.out.println("No credential found for: " + serviceRequest);
            }
        } else {
            System.out.println("Error Could not rename temp file.");
        }
    } else {
        System.out.println("Error Could not delete original file.");
    }
    }

    public static void deleteOptionAdminPassword(String specificServiceRequest){
    File originalFile = new File(FileManager.getDataFileName());
    File tempFile = new File(FileManager.getTempDataFileName());

        String serviceRequest = specificServiceRequest;
        boolean deleted = false;

        try(
            Scanner obj = new Scanner(new File(FileManager.getDataFileName()));
            FileWriter writer = new FileWriter(tempFileName);
            ){
            while(obj.hasNextLine()){
                String currentLine = obj.nextLine();

                if (currentLine.isEmpty()) continue;

                String[] section = currentLine.split(",");

                if(section.length == 1 && PasswordBeef.decode(section[0]).equals(serviceRequest)){
                    deleted = true;
                    continue;
                }

                writer.write(currentLine + "\n");
            }
            writer.flush();

        } catch (IOException e) {
            System.out.println("Error deleting file content");
        }
        if (originalFile.delete()) {
        if (tempFile.renameTo(originalFile)) {
            if (deleted) {
                System.out.println("Credential for '" + serviceRequest + "' deleted.");
            } else {
                System.out.println("No credential found for: " + serviceRequest);
            }
        } else {
            System.out.println("Error Could not rename temp file.");
        }
    } else {
        System.out.println("Error Could not delete original file.");
    }
    }

    public static void changeAdminPassword(){
        System.out.println("Enter Current Admin Password: ");
        String adminPasswordInput = Main.SCANNER.nextLine();
        while(!adminPasswordInput.equals(Login.getAdminPassword())){
                System.out.println("incorrect");
                System.out.println("Enter Current Admin Password: ");
                adminPasswordInput = Main.SCANNER.nextLine();
        }
        FileManager.deleteOptionAdminPassword(adminPasswordInput);
        FileManager.addOptionAdminPassword();
    }



}
