package com.george.db.utils;

import java.io.*;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceFileUtils {

    private ResourceFileUtils() {
    }

    // Loads a resource file as an InputStream and applies a provided mapping function to it.
    public static <T> T withResourceFile(String fileName, Function<InputStream, T> mapper) throws IOException {
        try (InputStream inputStream = ResourceFileUtils.class.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new FileNotFoundException(fileName);
            }
            return mapper.apply(inputStream);
        }
    }

    // Loads a text resource file and provides a stream of its lines for processing.
    public static <T> T withResourceTextFile(String fileName, Function<Stream<String>, T> mapper) throws IOException {
        return withResourceFile(fileName, inputStream -> {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                return mapper.apply(bufferedReader.lines());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Reads the entire contents of a text resource file and returns it as a single string.
    public static String readResourceFileAsText(String fileName) throws IOException {
        return withResourceTextFile(fileName, stream -> stream.collect(Collectors.joining("\n")));
    }

    // Loads a resource file in .properties format and returns its contents as a Properties object.
    public static Properties readResourceFileAsProperties(String fileName) throws IOException {
        return withResourceFile(fileName, (inputStream -> {
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
                return properties;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }

}
