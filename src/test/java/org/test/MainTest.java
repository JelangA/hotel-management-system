package org.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private static final Logger logger = Logger.getLogger(MainTest.class.getName());

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput() {
        ByteArrayInputStream testIn = new ByteArrayInputStream("6\n".getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testApplicationDisplaysMainMenu() {
        logger.info("test mulai");
        provideInput();


        Main.main(new String[0]);

        String output = testOut.toString();
        logger.info("Hasil tampilan yang didapatkan :\n" + output);
        logger.info("eksekusi selesai");

        assertTrue(output.contains("Enter your choice :"), "Should display main menu prompt");
        assertTrue(output.contains("1.Display room details"), "Should contain display room details option");
        assertTrue(output.contains("6.Exit"), "Should contain exit option");

    }
}
