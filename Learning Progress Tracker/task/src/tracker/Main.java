package tracker;


import java.util.Scanner;

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
                Main addStudentsMenuSelection = new Main();
                return addStudentsMenuSelection.selectAddStudentsMenuCommand(input);
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

//        String email = "email";
//
//        if (JMail.isValid(email)) {
//            System.out.println("Valid");
//        } else {
//            System.out.println("Invalid");
//        }

    }
}
