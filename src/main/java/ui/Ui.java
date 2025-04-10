package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui(){
        scanner = new Scanner(System.in);
    }

    public String inputString(){
        String input = scanner.nextLine();
        while(input.isEmpty()){ //Won't accept empty input
            input = scanner.nextLine();
        }
        return input;
    }

    public int inputInt(){
        System.out.print("Enter Number: ");
        String input = scanner.nextLine();
        while(input.isEmpty()){
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

    //Print and Ask
    public String readUserID(){
        System.out.print("Enter User ID: ");
        return inputString();
    }

    public String readPassword(){
        System.out.print("Enter Password: ");
        return inputString();
    }

    public String readNewPassword(){
        System.out.print("Enter New Password: ");
        return inputString();
    }

    public String switchOff(){
        System.out.print ("Press any key to Login, 0 to Quit: ");
        return scanner.nextLine();
    }
}
