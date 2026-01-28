package com.eokwingster.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    /**
     * Get the path of the folder where the .jar file contains this chatbot locates
     * @return the path of the folder .jar locates in
     * @throws URISyntaxException if the string path could not be converted to a URI
     */
    public static Path getJarFolderPath() throws URISyntaxException {
        URI jarPathUri = Utils.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI();
        Path path = Paths.get(jarPathUri);
        if (Files.isRegularFile(path)) {
            return path.getParent().resolve("data.json");
        } else {
            return path.resolve("data.json");
        }
    }
}
