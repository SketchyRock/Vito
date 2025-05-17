import java.util.Scanner;

public class Main {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        // if a file can be created then app asks user to set admin password (first time users) 
        if (FileManager.canCreateFile()){
            Login.createAdminPassword(); //creates admin pass
            Login.setAdminPasswordFromFile(); //sets that pass to var
        }
        else{
            Login.setAdminPasswordFromFile(); //sets variable of adminPassword to the admin password read from the file if not a first time user
        }
            if(Login.adminLoginAttempt()){ //Login.adminLoginAttempt()
                FileManager.createFile();
                PasswordBeef.mainLoop();
            }
        
        SCANNER.close();
    }
}
