package tracker;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;

import static tracker.Main.TrackerState.*;

public class Main {
    private static boolean exit = false;
    static Map<String, Student> emailMap = new HashMap<>();
    static Map<String, String> idMap = new HashMap<>();

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
                Main m = new Main();
                return m.addStudents();
            }
        },
        ADD_POINTS {
            @Override
            public TrackerState nextState() {
                Main m = new Main();
                return m.addPoints();
            }
        },
        FIND_STUDENT {
            @Override
            public TrackerState nextState() {
                Main m = new Main();
                return m.findStudent();
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

    TrackerState selectMainMenuCommand(@NotNull String input) {
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
            case "list":
                Main m = new Main();
                m.listStudents();
                return MAIN_MENU;
            case "find":
                System.out.println("Enter an id or 'back' to return:");
                return FIND_STUDENT;
            case "add points":
                System.out.println("Enter an id and points or 'back' to return:");
                return ADD_POINTS;
            default:
                System.out.println("Error: unknown command!");
                return MAIN_MENU;
        }
    }


    boolean checkForBadNumbers(List<String> userInputList) {
        if (!Pattern.matches("^[0-9]*$", userInputList.get(1))) {
            return true;
        } else if (!Pattern.matches("^[0-9]*$", userInputList.get(2))) {
            return true;
        } else if (!Pattern.matches("^[0-9]*$", userInputList.get(3))) {
            return true;
        } else if (!Pattern.matches("^[0-9]*$", userInputList.get(4))) {
            return true;
        } else {
            return false;
        }
    }

    TrackerState addPoints() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        List<String> userInputList = parseAddPointsInput(userInput);
        if ("back".equals(userInputList.get(0))) {
            return MAIN_MENU;
        } else if (userInputList.size() != 5) {
            System.out.println("Incorrect points format.");
            return ADD_POINTS;
        } else if (!idMap.containsKey(userInputList.get(0))) {
            System.out.println("No student is found for id=" + userInputList.get(0) + ".");
            return ADD_POINTS;
        } else if (checkForBadNumbers(userInputList)) {
            System.out.println("Incorrect points format.");
            return ADD_POINTS;
        } else {
            String id = userInputList.get(0);
            String java = userInputList.get(1);
            String dsa = userInputList.get(2);
            String db = userInputList.get(3);
            String spring = userInputList.get(4);
            String email = idMap.get(id);
            int currentJavaPoints = emailMap.get(email).javaPoints;
            int currentDsaPoints = emailMap.get(email).dsaPoints;
            int currentDbPoints = emailMap.get(email).databasesPoints;
            int currentSpringPoints = emailMap.get(email).springPoints;
            currentJavaPoints += Integer.parseInt(java);
            currentDsaPoints += Integer.parseInt(dsa);
            currentDbPoints += Integer.parseInt(db);
            currentSpringPoints += Integer.parseInt(spring);
            Student currentStudent = emailMap.get(email);
            currentStudent.javaPoints = currentJavaPoints;
            currentStudent.dsaPoints = currentDsaPoints;
            currentStudent.databasesPoints = currentDbPoints;
            currentStudent.springPoints = currentSpringPoints;
            emailMap.put(email, currentStudent);
            System.out.println("Points updated.");
            return ADD_POINTS;
        }
    }

    List<String> parseAddPointsInput(String line) {
        String[] lineArray = line.split(" ");
        List<String> addPointsList = Arrays.asList(lineArray);
        return addPointsList;
    }

    TrackerState findStudent() {

        String inputUserId;
        do {
            Scanner scanner = new Scanner(System.in);
            inputUserId = scanner.next();
            if ("back".equals(inputUserId)) {
                return MAIN_MENU;
            } else if (idMap.containsKey(inputUserId)) {
                String studentEmail = idMap.get(inputUserId);
                Student student = emailMap.get(studentEmail);
                System.out.println(inputUserId + " points: Java=" + student.javaPoints + "; DSA=" + student.dsaPoints +
                        "; Databases=" + student.databasesPoints + "; Spring=" + student.springPoints);
            } else {
                System.out.println("No student is found for id=" + inputUserId);
            }
        } while (!idMap.containsKey(inputUserId));
        return FIND_STUDENT;
    }

    void listStudents() {
       if (emailMap.isEmpty()) {
           System.out.println("No students found");
       } else {
           System.out.println("Students:");
           for (Map.Entry<String, Student> entry : emailMap.entrySet()) {
               Student s = entry.getValue();
               System.out.println(s.userId);
           }
       }
    }

    boolean validateForAddStudents(String[] parsedString) {
        if (parsedString[0] == null || parsedString[1] == null || parsedString[2] == null) {
            System.out.println("Incorrect credentials.");
            return true;
        } else if (invalidateName(parsedString[0])) {
            System.out.println("Incorrect first name.");
            return true;
        } else if (invalidateName(parsedString[1])) {
            System.out.println("Incorrect last name.");
            return true;
        } else if ((!validateEmail(parsedString[2]))) {
            System.out.println("Incorrect email.");
            return true;
        } else {
            return false;
        }
    }

    void addStudentProfile(String[] parsedString) {
        Student student = new Student(parsedString[2], parsedString[0], parsedString[1]);
        emailMap.put(parsedString[2], student);
        Main m = new Main();
        String id = m.generateUserId();
        while (idMap.containsKey(id)) {
            id = m.generateUserId();
        }
        idMap.put(id, parsedString[2]);
        student.userId = id;
    }

    TrackerState addStudents() {
        //loop until back is entered
        int count = 0;
        String input;
        System.out.println("Enter student credentials or 'back' to return");
        do {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine().strip();
            String[] parsedString = splitAddStudentsUserInput(input);
            if ("back".equals(input)) {
                break;
            } else if (validateForAddStudents(parsedString)) {
               assert true;
            } else if (emailMap.containsKey(parsedString[2])) {
                System.out.println("This email is already taken.");
            } else {
                Main m = new Main();
                m.addStudentProfile(parsedString);
                System.out.println("The student has been added.");
                count++;
            }
        } while (true);
        System.out.println("Total " + count + " students have been added.");
        return MAIN_MENU;
    }

    String generateUserId() {
        StringBuilder userId = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            char c = (char)(r.nextInt(26) + 'A');
            String s = Character.toString(c);
            userId.append(s);
        }
        String userIdString = userId.toString();
        return userIdString;
    }

    String[] splitAddStudentsUserInput(@NotNull String addStudentsInput) {
        String[] inputLine = addStudentsInput.split(" ");
        List<String> list = Arrays.asList(inputLine);
        String[] firstLastEmail = new String[3];
        if (list.size() >= 3) {
            firstLastEmail[0] = list.get(0);
            firstLastEmail[2] = list.get(list.size() - 1);
            StringBuilder lastName = new StringBuilder();
            for (int i = 0; i < inputLine.length - 2; i++) {
                lastName.append(inputLine[i+1]);
                lastName.append(" ");
            }
            firstLastEmail[1] = lastName.toString().strip();
        } else {
           for (int i = 0; i < list.size(); i++) {
               firstLastEmail[i] = list.get(i);
           }
        }
        return firstLastEmail;
    }

    boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[^@]+@[^@]+\\.[^@]+$");
        Matcher m = p.matcher(email);
        return (m.find());

    }

    /*returns true on any non-alphabet character, except apostrophe and hypen.
    Name also cannot begin or end with apostrophe or hyphen.
    Apostrophe and hyphen cannot be adjacent.
     */
    boolean invalidateName(String name) {
        if (name.length() < 2) {
            return true;
        }
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
//          Student newStudent = new Student("example@email.com", "Frank", "Costanza",
//                  "ABCDE", 1,2,3,4);
//          emailMap.put("example@email.com", newStudent);
//          Main m = new Main();
//          m.listStudents();
    }
}
