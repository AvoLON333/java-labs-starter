package edu.course.lab06;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TaskAnalyticsTest {

    private final TaskAnalytics analytics = new TaskAnalytics();

    private List<ProjectTask> createTasks() {
        List<ProjectTask> tasks = new ArrayList<>();

        tasks.add(new ProjectTask(
                "T-103", "Prepare release", "backend", 8, "READY"
        ));

        tasks.add(new ProjectTask(
                "T-101", "Fix bug", "frontend", 4, "IN_PROGRESS"
        ));

        tasks.add(new ProjectTask(
                "T-102", "Write tests", "backend", 4, "READY"
        ));

        tasks.add(new ProjectTask(
                "T-104", "Update docs", "testing", 2, "DONE"
        ));

        return tasks;
    }

    @Test
    void countsTasksByStatus() {
        List<ProjectTask> tasks = createTasks();

        Map<String, Integer> result = analytics.countByStatus(tasks);

        assertEquals(2, result.get("READY"));
        assertEquals(1, result.get("IN_PROGRESS"));
        assertEquals(1, result.get("DONE"));
    }

    @Test
    void findsUniqueCategories() {
        List<ProjectTask> tasks = createTasks();

        Set<String> result = analytics.uniqueCategories(tasks);

        assertEquals(3, result.size());
        assertTrue(result.contains("backend"));
        assertTrue(result.contains("frontend"));
        assertTrue(result.contains("testing"));
    }

    @Test
    void sortsTasksByEffortAndId() {
        List<ProjectTask> tasks = createTasks();

        List<ProjectTask> result = analytics.sortByEffortAndId(tasks);

        assertEquals("T-104", result.get(0).getId());
        assertEquals("T-101", result.get(1).getId());
        assertEquals("T-102", result.get(2).getId());
        assertEquals("T-103", result.get(3).getId());
    }

    @Test
    void sortingDoesNotChangeOriginalList() {
        List<ProjectTask> tasks = createTasks();

        List<String> originalIds = new ArrayList<>();

        for (ProjectTask task : tasks) {
            originalIds.add(task.getId());
        }

        analytics.sortByEffortAndId(tasks);

        List<String> idsAfterSorting = new ArrayList<>();

        for (ProjectTask task : tasks) {
            idsAfterSorting.add(task.getId());
        }

        assertEquals(originalIds, idsAfterSorting);
    }
}
