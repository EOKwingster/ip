package com.eokwingster.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
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
