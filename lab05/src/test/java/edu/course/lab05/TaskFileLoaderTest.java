package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskFileLoaderTest {

    @TempDir
    Path tempDir;

    private final TaskFileLoader loader = new TaskFileLoader();

    private Path createFile(String name, List<String> lines)
            throws IOException {

        Path file = tempDir.resolve(name);

        Files.write(file, lines, StandardCharsets.UTF_8);

        return file;
    }

    @Test
    void loadsCorrectFile() throws IOException {
        Path file = createFile("tasks.txt", List.of(
                "id;title;category;estimatedHours;status",
                "T-101;Prepare release;backend;8;READY"
        ));

        LoadResult result = loader.load(file);

        assertEquals(1, result.getTasks().size());
        assertEquals(0, result.getErrors().size());

        Task task = result.getTasks().get(0);

        assertEquals("T-101", task.getId());
        assertEquals("Prepare release", task.getTitle());
        assertEquals("backend", task.getCategory());
        assertEquals(8, task.getEstimatedHours());
        assertEquals(TaskStatus.READY, task.getStatus());
    }

    @Test
    void loadsUnicodeText() throws IOException {
        Path file = createFile("unicode.txt", List.of(
                "id;title;category;estimatedHours;status",
                "T-102;Подготовить релиз;разработка;5;READY"
        ));

        LoadResult result = loader.load(file);

        assertEquals(1, result.getTasks().size());
        assertEquals("Подготовить релиз",
                result.getTasks().get(0).getTitle());
        assertEquals("разработка",
                result.getTasks().get(0).getCategory());
    }

    @Test
    void reportsEmptyField() throws IOException {
        Path file = createFile("empty.txt", List.of(
                "id;title;category;estimatedHours;status",
                "T-103;Prepare release;;8;READY"
        ));

        LoadResult result = loader.load(file);

        assertEquals(0, result.getTasks().size());
        assertEquals(1, result.getErrors().size());

        assertEquals("category", result.getErrors().get(0).getField());
        assertEquals(2, result.getErrors().get(0).getLineNumber());
    }

    @Test
    void reportsInvalidNumber() throws IOException {
        Path file = createFile("number.txt", List.of(
                "id;title;category;estimatedHours;status",
                "T-104;Prepare release;backend;abc;READY"
        ));

        LoadResult result = loader.load(file);

        assertEquals(0, result.getTasks().size());
        assertEquals(1, result.getErrors().size());

        assertEquals(
                "estimatedHours",
                result.getErrors().get(0).getField()
        );
    }

    @Test
    void reportsUnknownStatus() throws IOException {
        Path file = createFile("status.txt", List.of(
                "id;title;category;estimatedHours;status",
                "T-105;Prepare release;backend;8;UNKNOWN"
        ));

        LoadResult result = loader.load(file);

        assertEquals(0, result.getTasks().size());
        assertEquals(1, result.getErrors().size());

        assertEquals("status", result.getErrors().get(0).getField());
    }

    @Test
    void reportsWrongNumberOfColumns() throws IOException {
        Path file = createFile("columns.txt", List.of(
                "id;title;category;estimatedHours;status",
                "T-106;Prepare release;backend;8"
        ));

        LoadResult result = loader.load(file);

        assertEquals(0, result.getTasks().size());
        assertEquals(1, result.getErrors().size());

        assertEquals("row", result.getErrors().get(0).getField());
    }

    @Test
    void loadsCorrectRowsAndReportsIncorrectRows() throws IOException {
        Path file = createFile("mixed.txt", List.of(
                "id;title;category;estimatedHours;status",
                "T-101;Prepare release;backend;8;READY",
                "T-102;Fix login;backend;abc;READY",
                "T-103;Write tests;testing;4;DONE"
        ));

        LoadResult result = loader.load(file);

        assertEquals(2, result.getTasks().size());
        assertEquals(1, result.getErrors().size());

        assertEquals("T-101", result.getTasks().get(0).getId());
        assertEquals("T-103", result.getTasks().get(1).getId());

        assertEquals(3, result.getErrors().get(0).getLineNumber());
    }

    @Test
    void missingFileCausesDataLoadingException() {
        Path file = tempDir.resolve("missing.txt");

        DataLoadingException exception = assertThrows(
                DataLoadingException.class,
                () -> loader.load(file)
        );

        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof IOException);
    }
}
