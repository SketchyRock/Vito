import java.util.Scanner;

public class Main {

    /* a global scanner is used and basically never closes */
    public static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        /*checks if data file exists, 
        if not a admin password is created by user*/ 
        if (FileManager.canCreateFile()){
            Login.createAdminPassword(); 
            Login.setAdminPasswordFromFile(); 
        }
        /*if data file does exist it gets the admin password into memory*/
        else{
            Login.setAdminPasswordFromFile(); 
        }
        /*if login is successful,
        main loop is run*/
            if(Login.adminLoginAttempt()){ 
                FileManager.createFile();
                PasswordBeef.mainLoop();
            }
        
        SCANNER.close();
    }
}
