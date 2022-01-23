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

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }


}
