import java.util.Base64;

public class PasswordBeef {

    public static String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String decode(String base64Input) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Input);
        return new String(decodedBytes);
    }

    public static void mainLoop(){
            while(true){
                PasswordBeef.printOptions();
                int input;

                while(true){
                    String line = Main.SCANNER.nextLine();
                    
                    try {
                        input = Integer.parseInt(line.trim());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Error invalid input");
                    }
                }

                PasswordBeef.checkInputOptions(input);
            }
    }

    public static void printOptions(){
        System.out.println(" 1: Add \n 2: View \n 3: Delete \n 4: Change Admin Password \n 5: Exit Application");
    }

    public static void checkInputOptions(int input){
    switch (input) {
                case 1 -> {
                    FileManager.addOption();
                }
                case 2 -> {
                    FileManager.viewOption();
                }
                case 3 -> {
                    FileManager.deleteOption();
                }
                case 4 -> {
                    FileManager.changeAdminPassword();
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Unknown option."); 
            }

    }

    

}
