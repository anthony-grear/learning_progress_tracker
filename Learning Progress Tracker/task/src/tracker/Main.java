package tracker;


import java.util.Scanner;

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
        },
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

    }
}
