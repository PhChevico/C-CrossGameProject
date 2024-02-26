import java.util.Scanner;

public class Screen {
    private Scanner scanner = new Scanner(System.in);
    public int LoginScreen(){
        int choice;
        System.out.println("enter \n1-LOGIN \n2-REGISTER");
        choice = scanner.nextInt();
        return choice;
    }
}
