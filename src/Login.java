import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Login {

    private static String adminPassword;

    //setter for var adminPassword
    public static void setAdminPassword(String adminPassword){
        Login.adminPassword = adminPassword;
    }

    //getter for var adminPassword
    public static String getAdminPassword(){
        return Login.adminPassword;
    }

    //sets adminPassword var to what is read in the Data.txt file
    public static void setAdminPasswordFromFile() {
        try(Scanner obj = new Scanner(new File(FileManager.getDataFileName()))){
            while(obj.hasNextLine()){
                String currentLine = obj.nextLine();
                if (currentLine.isEmpty()) continue;
                String[] section = currentLine.split("=");
                if(section.length == 1){
                String password = section[0];
                Login.setAdminPassword(password);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading and writing from Data.txt file");
        }
    }

    //creates admin password from user input
    public static void createAdminPassword(){
        System.out.println("Set Admin Password: ");
                String adminPasswordInput = Main.SCANNER.nextLine();

                try(FileWriter writer = new FileWriter(FileManager.getDataFileName(), true)){
                writer.write(adminPasswordInput + "=");
                writer.write("\n");
                System.out.println("credentials saved");
                }
                catch (IOException e){
                    System.out.println("Error creating admin password");
                }
    }

    //checks if user input correct admin password, failed login attempt loop if input is incorrect
    public static boolean adminLoginAttempt (){
            System.out.println("Login: ");
            String input = Main.SCANNER.nextLine();
            
            while(!input.equals(adminPassword)){
                System.out.println("incorrect");
                System.out.println("Login: ");
                input = Main.SCANNER.nextLine();
            }
        return (true);

    }

}
