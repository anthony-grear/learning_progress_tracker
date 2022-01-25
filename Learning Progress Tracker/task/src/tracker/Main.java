package tracker;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sanctionco.jmail.JMail;

import static tracker.Main.TrackerState.*;

public class Main {
    private static boolean exit = false;

    public enum TrackerState {

        START_TRACKER {
            @Override
            public TrackerState nextState() {
                Main startMessage = new Main();
                startMessage.displayStartProgramMessage();
                return MAIN_MENU;
            }
        },
        MAIN_MENU{
            @Override
            public TrackerState nextState() {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().strip();
                Main mainMenuSelection = new Main();
                return mainMenuSelection.selectMainMenuCommand(input);
            }
        },
        ADD_STUDENTS {
            @Override
            public TrackerState nextState() {
                System.out.println("Enter student credentials or 'back' to return");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().strip();
                Main menuSelection = new Main();
                return menuSelection.selectAddStudentsMenuCommand(input);
            }
        },
        EXIT_TRACKER {
            @Override
            public TrackerState nextState() {
                Main exitMessage = new Main();
                exitMessage.displayExitProgramMessage();
                exit = true;
                return this;
            }
        };

        public abstract TrackerState nextState();
    }

    TrackerState selectMainMenuCommand(String input) {
        switch (input) {
            case "":
                System.out.println("no input");
                return MAIN_MENU;
            case "exit":
                return EXIT_TRACKER;
            case "add students":
                return ADD_STUDENTS;
            case "back":
                System.out.println("Enter 'exit' to exit the program.");
                return MAIN_MENU;
            default:
                System.out.println("Error: unknown command!");
                return MAIN_MENU;
        }
    }

    TrackerState selectAddStudentsMenuCommand(String input) {
        switch (input) {
            case "back":
                
                return MAIN_MENU;
            default:
                System.out.println("Error: unknown command!");
                return MAIN_MENU;
        }
    }

    String[] splitAddStudentsUserInput(String addStudentsInput) {
        String[] inputLine = addStudentsInput.split(" ");
        String[] firstLastEmail = new String[3];
        firstLastEmail[0] = inputLine[0];
        firstLastEmail[2] = inputLine[inputLine.length - 1];
        StringBuilder lastName = new StringBuilder();
        for (int i = 0; i < inputLine.length - 2; i++) {
            lastName.append(inputLine[i+1]);
            lastName.append(" ");
        }
        firstLastEmail[1] = lastName.toString().strip();
        return firstLastEmail;
    }

    boolean validateEmail(String email) {
        return JMail.isValid(email);
    }

    /*returns true on any non-alphabet character, except apostrophe and hypen.
    Name also cannot begin or end with apostrophe or hyphen.
    Apostrophe and hyphen cannot be adjacent.
     */
    boolean invalidateName(String name) {
        Pattern p = Pattern.compile("--|-'|'-|''|^[-'].|.[-']$|[^A-Za-z-' ]");
        Matcher m = p.matcher(name);
        return m.find();
    }

    void displayStartProgramMessage() {
        System.out.println("Learning Progress Tracker");
    }

    void displayExitProgramMessage() {
        System.out.println("Bye!");
    }

    public static void main(String[] args) {
        TrackerState state = TrackerState.START_TRACKER;
        while (!exit) {
            state = state.nextState();
        }


    }
}
