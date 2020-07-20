//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package file_handling;

import java.util.Scanner;

public class ReadFromConsole {
    public ReadFromConsole() {
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter something: ");
        String userInput = input.nextLine();
        System.out.println("You have entered: " + userInput);
    }
}
