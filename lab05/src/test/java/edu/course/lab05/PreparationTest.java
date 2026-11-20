package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreparationTest {

    @TempDir
    Path tempDir;

    @Test
    void readsNumbersFromUtf8File() throws IOException {
        Path file = tempDir.resolve("numbers.txt");

        Files.write(
                file,
                List.of("10", "bad", "20"),
                StandardCharsets.UTF_8
        );

        List<String> lines = Files.readAllLines(
                file,
                StandardCharsets.UTF_8
        );

        assertEquals(List.of("10", "bad", "20"), lines);
    }

    @Test
    void badLineCausesNumberFormatException() throws IOException {
        Path file = tempDir.resolve("numbers.txt");

        Files.write(
                file,
                List.of("10", "bad", "20"),
                StandardCharsets.UTF_8
        );

        List<String> lines = Files.readAllLines(
                file,
                StandardCharsets.UTF_8
        );

        assertEquals(10, Integer.parseInt(lines.get(0)));

        assertThrows(
                NumberFormatException.class,
                () -> Integer.parseInt(lines.get(1))
        );

        assertEquals(20, Integer.parseInt(lines.get(2)));
    }
}