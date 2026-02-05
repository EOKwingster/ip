package com.eokwingster.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all util functions
 */
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

    /**
     * Calculate the dice factor of two strings' grams when n is 2.
     * @param s1 string 1
     * @param s2 string 2
     * @return the dice factor
     */
    public static float getNGramDiceOfTwoStrings(String s1, String s2) {
        if (s1.equals(s2)) {
            return 1f;
        }
        List<String> grams1 = getNGramsString(s1, 2);
        List<String> grams2 = getNGramsString(s2, 2);
        if (grams1.isEmpty() || grams2.isEmpty()) {
            return 0f;
        }

        int intersection = 0;
        for (String gram1 : grams1) {
            if (grams2.contains(gram1)) {
                intersection++;
            }
        }
        return 2f * intersection / (grams1.size() + grams2.size());
    }

    /**
     * Split the lowercase, no spaces form of a string into n strings with length n.
     * @param s a string
     * @param n number of strings
     * @return list of n strings
     */
    public static List<String> getNGramsString(String s, int n) {
        String text = s.toLowerCase().replace(" ", "");
        List<String> nGrams = new ArrayList<>();
        for (int i = 0; i < text.length() - n + 1; i++) {
            nGrams.add(text.substring(i, i + n));
        }
        return nGrams;
    }
}
