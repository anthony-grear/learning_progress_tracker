package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.Main.TrackerState.*;

public class SampleTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void testMainMenuSelectionAddStudents() {
        Main menuSelect = new Main();
        String addStudent = "add students";
        assertEquals(ADD_STUDENTS, menuSelect.selectMainMenuCommand(addStudent));
    }

    @Test
    void testProcessAddStudentsInputFirstLastEmailOnly() {
        String firstLastEmail = "Jane Doe jdoe@example.com";
        Main processString = new Main();
        String[] firstLastEmailArray = processString.processAddStudentsInput(firstLastEmail);
        assertEquals("Jane", firstLastEmailArray[0]);
        assertEquals("Doe", firstLastEmailArray[1]);
        assertEquals("jdoe@example.com", firstLastEmailArray[2]);
    }

    @Test
    void testProcessAddStudentsInputExtendedLengthNameAndEmail() {
        String firstLastEmail = "Jim Bo Jones Jeffrey Jobs jjobs@example.com";
        Main processString = new Main();
        String[] firstLastEmailArray = processString.processAddStudentsInput(firstLastEmail);
        assertEquals("Jim", firstLastEmailArray[0]);
        assertEquals("Bo Jones Jeffrey Jobs", firstLastEmailArray[1]);
        assertEquals("jjobs@example.com", firstLastEmailArray[2]);
    }

    @Nested
    public class NestedSampleTest {
        @BeforeEach
        public void setUp() {
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void testDisplayStartProgramMessage() {
            Main main = new Main();
            main.displayStartProgramMessage();
            assertEquals("Learning Progress Tracker", outputStreamCaptor.toString().trim());
        }

        @Test
        void testDisplayExitProgramMessage() {
            Main main = new Main();
            main.displayExitProgramMessage();
            assertEquals("Bye!", outputStreamCaptor.toString().trim());
        }

        @Test
        void testMainMenuSelectionNoString() {
            Main menuSelect = new Main();
            String empty = "";
            assertEquals(MAIN_MENU, menuSelect.selectMainMenuCommand(empty));
            assertEquals("no input", outputStreamCaptor.toString().trim());

        }

        @Test
        void testMainMenuSelectionExit() {
            Main menuSelect = new Main();
            String exit = "exit";
            assertEquals(EXIT_TRACKER, menuSelect.selectMainMenuCommand(exit));
        }

        @Test
        void testMainMenuSelectionIncorrect() {
            Main menuSelect = new Main();
            String incorrectChoice = "exitt";
            assertEquals(MAIN_MENU, menuSelect.selectMainMenuCommand(incorrectChoice));
            assertEquals("Error: unknown command!", outputStreamCaptor.toString().trim());
        }

        @Test
        void testMainMenuSelectionBack() {
            Main menuSelect = new Main();
            String back = "back";
            assertEquals(MAIN_MENU, menuSelect.selectMainMenuCommand(back));
            assertEquals("Enter 'exit' to exit the program.", outputStreamCaptor.toString().trim());
        }


        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }


}