package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.Main.displayExitProgramMessage;
import static tracker.Main.displayStartProgramMessage;

public class SampleTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testDisplayStartProgramMessage() {
        displayStartProgramMessage();
        assertEquals("Learning Progress Tracker", outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayExitProgramMessage() {
        displayExitProgramMessage();
        assertEquals("Bye!", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
