package edu.course.lab06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class TaskAnalytics {

    public Map<String, Integer> countByStatus(List<ProjectTask> tasks) {
        Map<String, Integer> result = new LinkedHashMap<>();

        for (ProjectTask task : tasks) {
            String status = task.getStatus();

            int count = result.getOrDefault(status, 0);

            result.put(status, count + 1);
        }

        return result;
    }

    public Set<String> uniqueCategories(List<ProjectTask> tasks) {
        Set<String> result = new LinkedHashSet<>();

        for (ProjectTask task : tasks) {
            result.add(task.getCategory());
        }

        return result;
    }

    public List<ProjectTask> sortByEffortAndId(List<ProjectTask> tasks) {
        List<ProjectTask> result = new ArrayList<>(tasks);

        result.sort(
                Comparator.comparingInt(ProjectTask::getEstimatedHours)
                        .thenComparing(ProjectTask::getId)
        );

        return result;
    }
}
