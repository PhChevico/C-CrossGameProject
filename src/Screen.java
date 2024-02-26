import java.util.Scanner;

public class Screen {
    private Scanner scanner = new Scanner(System.in);
    public int LoginScreen(){ //we ask the user if they want to either login or register and we return the choice
        int choice;
        System.out.println("enter \n1-LOGIN \n2-REGISTER");
        choice = scanner.nextInt();
        return choice;
    }
}
