package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TaskFileLoader {

    private static final String EXPECTED_HEADER =
            "id;title;category;estimatedHours;status";

    private static final String[] FIELD_NAMES = {
            "id", "title", "category", "estimatedHours", "status"
    };

    public LoadResult load(Path path) {
        Objects.requireNonNull(path, "Path cannot be null");

        List<String> lines;

        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException | SecurityException e) {
            throw new DataLoadingException(
                    "Cannot read " + path, e
            );
        }

        if (lines.isEmpty()) {
            throw new DataLoadingException(
                    "File is empty",
                    new IllegalArgumentException("Header is missing")
            );
        }

        if (!EXPECTED_HEADER.equals(lines.get(0).trim())) {
            throw new DataLoadingException(
                    "Invalid file header",
                    new IllegalArgumentException("Unexpected header")
            );
        }

        List<Task> tasks = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.isBlank()) {
                continue;
            }

            String[] fields = line.split(";", -1);

            if (fields.length != 5) {
                errors.add(new ValidationError(
                        i + 1,
                        "row",
                        "Expected 5 fields, but found " + fields.length
                ));
                continue;
            }

            for (int j = 0; j < fields.length; j++) {
                fields[j] = fields[j].trim();
            }

            boolean hasEmptyField = false;

            for (int j = 0; j < fields.length; j++) {
                if (fields[j].isEmpty()) {
                    errors.add(new ValidationError(
                            i + 1,
                            FIELD_NAMES[j],
                            "Field cannot be empty"
                    ));

                    hasEmptyField = true;
                    break;
                }
            }

            if (hasEmptyField) {
                continue;
            }

            try {
                int estimatedHours = Integer.parseInt(fields[3]);
                TaskStatus status = TaskStatus.valueOf(fields[4]);

                Task task = new Task(
                        fields[0],
                        fields[1],
                        fields[2],
                        estimatedHours,
                        status
                );

                tasks.add(task);

            } catch (NumberFormatException e) {
                errors.add(new ValidationError(
                        i + 1,
                        "estimatedHours",
                        "Invalid number"
                ));

            } catch (IllegalArgumentException e) {
                errors.add(new ValidationError(
                        i + 1,
                        "status",
                        "Unknown status or invalid task data"
                ));
            }
        }

        return new LoadResult(tasks, errors);
    }
}