package com.eokwingster.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public void testGetJarFolderPath() throws URISyntaxException {
        Path expected = Path.of("D:/_EOK_/NUS/Academic/Year3.2/CS2103/ip/build/classes/java/main/data.json");
        assertEquals(expected, Utils.getJarFolderPath());
    }

    @Test
    public void testGetNGramsString() {
        assertEquals(Utils.getNGramsString("abcde", 2), List.of("ab", "bc", "cd", "de"));
    }
}
