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
    void missSpaceInName() {
        String name = "Anth ony"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertFalse(m.invalidateName(name));
    }

    @Test
    void missNormalName() {
        String name = "Anthony"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertFalse(m.invalidateName(name));
    }

    @Test
    void missSingleApostrophe() {
        String name = "Anth'ony"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertFalse(m.invalidateName(name));
    }

    @Test
    void missSingleHyphen() {
        String name = "Anth-ony"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertFalse(m.invalidateName(name));
    }

    @Test
    void detectInvalidCharacter() {
        String name = "Anth@ony"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectStartWithHyphen() {
        String name = "-Anthony"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectEndWithHyphen() {
        String name = "Anthony-"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectEndWithApostrophe() {
        String name = "Anthony'"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectStartWithApostrophe() {
        String name = "'Anthony"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectAdjacentApostropheHyphenInName() {
        String name = "Antho'-ny"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectAdjacentHyphenApostropheInName() {
        String name = "Antho-'ny"; //regex should detect adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectConsecutiveApostrophesInName() {
        String name = "Antho''ny"; //regex should detect double apostrophes in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectConsecutiveHyphensInName() {
        String name = "Ant--hony"; //regex should detect double hyphen in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

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