package edu.course.lab03;

public class TaskPrioritizer {

    private final PriorityPolicy priorityPolicy;

    public TaskPrioritizer(PriorityPolicy priorityPolicy) {
        if (priorityPolicy == null) {
            throw new IllegalArgumentException("Политика приоритета не null");
        }

        this.priorityPolicy = priorityPolicy;
    }

    public ProjectTask chooseHigherPriority(ProjectTask first, ProjectTask second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Задачи не null");
        }

        int firstPriority = priorityPolicy.calculatePriority(first);
        int secondPriority = priorityPolicy.calculatePriority(second);

        if (firstPriority >= secondPriority) {
            return first;
        }

        return second;
    }
}
