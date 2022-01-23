package tracker;


import java.util.Scanner;

import com.sanctionco.jmail.JMail;

import static tracker.Main.TrackerState.EXIT_TRACKER;
import static tracker.Main.TrackerState.MAIN_MENU;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static boolean exit = false;

    public enum TrackerState {

        START_TRACKER {
            @Override
            public TrackerState nextState() {
                displayStartProgramMessage();
                return MAIN_MENU;
            }
        },
        MAIN_MENU{
            @Override
            public TrackerState nextState() {
                String input = scanner.nextLine().strip();
                return selectMainMenuCommand(input);
            }
        },
//        ADD_STUDENT {
//            @Override
//            public TrackerState nextState() {
//                //To-do: validate and add first and last name, and email address
//                return MAIN_MENU;//To-do: change according to outcome of above statement
//            }
//        },
        EXIT_TRACKER {
            @Override
            public TrackerState nextState() {
                displayExitProgramMessage();
                exit = true;
                return this;
            }
        };

        public abstract TrackerState nextState();
    }

    static TrackerState selectMainMenuCommand(String input) {
        switch (input) {
            case "":
                System.out.println("no input");
                return MAIN_MENU;
            case "exit":
                return EXIT_TRACKER;
            default:
                System.out.println("Error: unknown command!");
                return MAIN_MENU;
        }

    }

    static void displayStartProgramMessage() {
        System.out.println("Learning Progress Tracker");
    }

    static void displayExitProgramMessage() {
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
