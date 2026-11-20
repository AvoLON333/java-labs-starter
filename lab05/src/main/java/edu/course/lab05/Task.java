package edu.course.lab05;

import java.util.Objects;

public final class Task {

    private final String id;
    private final String title;
    private final String category;
    private final int estimatedHours;
    private final TaskStatus status;

    public Task(String id, String title, String category,
                int estimatedHours, TaskStatus status) {

        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.category = Objects.requireNonNull(category, "Category cannot be null");
        this.status = Objects.requireNonNull(status, "Status cannot be null");

        if (id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be blank");
        }

        if (title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }

        if (category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be blank");
        }

        if (estimatedHours <= 0) {
            throw new IllegalArgumentException("Estimated hours must be positive");
        }

        this.estimatedHours = estimatedHours;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public TaskStatus getStatus() {
        return status;
    }
}