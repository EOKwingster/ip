package com.eokwingster.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.ResponseStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testDisplay() {
        Response response = ResponseStub.of(
                "Test line 1",
                "Test line 2",
                "Test line 3"
        );
        String expected = """
                Wee: | Test line 1
                     | Test line 2
                     | Test line 3
                """;
        new Ui().display(response);
        assertEquals(expected.trim(), outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }
}
