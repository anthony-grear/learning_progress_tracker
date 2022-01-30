package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.Main.TrackerState.*;

public class SampleTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void parseAddPointsTest() {
        String input = "10001 5 8 7 3";
        Main m = new Main();
        List<String> output = m.parseAddPointsInput(input);
        assertEquals("10001",output.get(0));
        assertEquals("5",output.get(1));
        assertEquals("8",output.get(2));
        assertEquals("7",output.get(3));
        assertEquals("3",output.get(4));
    }

    @Test
    void rejectMoreThanOneAt() {
        String email = "email@example@example.com";
        Main m = new Main();
        assertFalse(m.validateEmail(email));
    }

    @Test
    void rejectMissingAt() {
        String email = "email.example.com";
        Main m = new Main();
        assertFalse(m.validateEmail(email));
    }

    @Test
    void rejectNumericEmail() {
        String email = "1@1.1";
        Main m = new Main();
        assertTrue(m.validateEmail(email));
    }

    @Test
    void rejectMissingLocalName() {
        String email = "@example.com";
        Main m = new Main();
        assertFalse(m.validateEmail(email));
    }

    @Test
    void rejectRestrictedCharacters() {
        String email = "#@%^%#$@#$@#.com";
        Main m = new Main();
        assertFalse(m.validateEmail(email));
    }

    @Test
    void rejectAddress() {
        String email = "plainaddress";
        Main m = new Main();
        assertFalse(m.validateEmail(email));
    }

    @Test
    void validateSimpleEmailNumeric() {
        String email = "1234567890@example.com";
        Main m = new Main();
        assertTrue(m.validateEmail(email));
    }

    @Test
    void validateSimpleEmailInQuotes() {
        String email = "\"email\"@example.com";
        Main m = new Main();
        assertTrue(m.validateEmail(email));
    }

    @Test
    void validateSimpleEmailIPDomainInBrackets() {
        String email = "email@[123.123.123.123]";
        Main m = new Main();
        assertTrue(m.validateEmail(email));
    }

    @Test
    void rejectEmailWithoutDot() {
        String email = "email@emailxyz";
        Main m = new Main();
        assertFalse(m.validateEmail(email));
    }

    @Test
    void validateSimpleEmailPlus() {
        String email = "firstname+lastname@example.com";
        Main m = new Main();
        assertTrue(m.validateEmail(email));
    }

    @Test
    void validateSimpleEmailSubdomain() {
        String email = "email@subdomain.example.com";
        Main m = new Main();
        assertTrue(m.validateEmail(email));
    }

    @Test
    void validateSimpleEmailPeriod() {
        String email = "firstname.lastname@example.com";
        Main m = new Main();
        assertTrue(m.validateEmail(email));
    }

    @Test
    void validateSimpleEmail() {
        String email = "name@example.com";
        Main m = new Main();
        assertTrue(m.validateEmail(email));
    }

    @Test
    void missSpaceInName() {
        String name = "Anth ony"; //regex should return false because this is a valid name
        Main m = new Main();
        assertFalse(m.invalidateName(name));
    }

    @Test
    void detectShortName() {
        String name = "A"; //regex should return true because this is a invalid name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void missNormalName() {
        String name = "Anthony"; //regex should return false because this is a valid name
        Main m = new Main();
        assertFalse(m.invalidateName(name));
    }

    @Test
    void missSingleApostrophe() {
        String name = "Anth'ony"; //regex should return false because this is a valid name
        Main m = new Main();
        assertFalse(m.invalidateName(name));
    }

    @Test
    void missSingleHyphen() {
        String name = "Anth-ony"; //regex should return false because this is a valid name
        Main m = new Main();
        assertFalse(m.invalidateName(name));
    }

    @Test
    void detectInvalidCharacter() {
        String name = "Anth@ony"; //regex should return true because this symbol is not allowed
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectStartWithHyphen() {
        String name = "-Anthony"; //regex should return true because no first character hyphen
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectEndWithHyphen() {
        String name = "Anthony-"; //regex should return true because no end character hyphen
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectEndWithApostrophe() {
        String name = "Anthony'"; //regex should return true because no end character with apostrophe
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectStartWithApostrophe() {
        String name = "'Anthony"; //regex should return true because no first character wih apostrophe
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectAdjacentApostropheHyphenInName() {
        String name = "Antho'-ny"; //regex should return true because no adjacent apostrophe hyphen in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectAdjacentHyphenApostropheInName() {
        String name = "Antho-'ny"; //regex should return true because no adjacent hyphen apostrophe in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectConsecutiveApostrophesInName() {
        String name = "Antho''ny"; //regex should return true because no double apostrophes in a name
        Main m = new Main();
        assertTrue(m.invalidateName(name));
    }

    @Test
    void detectConsecutiveHyphensInName() {
        String name = "Ant--hony"; //regex should return true because no double hyphen in a name
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
    void testProcessAddStudentsInputOneWord() {
        String firstLastEmail = "exit";
        Main processString = new Main();
        String[] firstLastEmailArray = processString.splitAddStudentsUserInput(firstLastEmail);
        assertEquals("exit", firstLastEmailArray[0]);
        assertEquals(null, firstLastEmailArray[1]);
        assertEquals(null, firstLastEmailArray[2]);
    }

    @Test
    void testProcessAddStudentsInputFirstLastEmailOnly() {
        String firstLastEmail = "Jane Doe jdoe@example.com";
        Main processString = new Main();
        String[] firstLastEmailArray = processString.splitAddStudentsUserInput(firstLastEmail);
        assertEquals("Jane", firstLastEmailArray[0]);
        assertEquals("Doe", firstLastEmailArray[1]);
        assertEquals("jdoe@example.com", firstLastEmailArray[2]);
    }

    @Test
    void testProcessAddStudentsInputExtendedLengthNameAndEmail() {
        String firstLastEmail = "Jim Bo Jones Jeffrey Jobs jjobs@example.com";
        Main processString = new Main();
        String[] firstLastEmailArray = processString.splitAddStudentsUserInput(firstLastEmail);
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