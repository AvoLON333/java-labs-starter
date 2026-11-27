package edu.course.lab06;

public final class ProjectTask {

    private final String id;
    private final String title;
    private final String category;
    private final int estimatedHours;
    private final String status;

    public ProjectTask(
            String id,
            String title,
            String category,
            int estimatedHours,
            String status
    ) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.estimatedHours = estimatedHours;
        this.status = status;
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

    public String getStatus() {
        return status;
    }
}