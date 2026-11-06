package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskPrioritizerTest {

    @Test
    void shouldChooseTaskWithHigherEffortPriority() {
        TaskPrioritizer prioritizer =
                new TaskPrioritizer(new EffortPriorityPolicy());

        ProjectTask first = new ProjectTask(
                "TASK-1", "Первая задача", TaskStatus.TODO, 2
        );

        ProjectTask second = new ProjectTask(
                "TASK-2", "Вторая задача", TaskStatus.TODO, 5
        );

        assertEquals(first, prioritizer.chooseHigherPriority(first, second));
    }

    @Test
    void shouldChooseTaskWithHigherStatusPriority() {
        TaskPrioritizer prioritizer =
                new TaskPrioritizer(new StatusPriorityPolicy());

        ProjectTask first = new ProjectTask(
                "TASK-1", "Первая задача", TaskStatus.TODO, 2
        );

        ProjectTask second = new ProjectTask(
                "TASK-2", "Вторая задача", TaskStatus.IN_PROGRESS, 5
        );

        assertEquals(second, prioritizer.chooseHigherPriority(first, second));
    }

    @Test
    void shouldRejectNullTasks() {
        TaskPrioritizer prioritizer =
                new TaskPrioritizer(new EffortPriorityPolicy());

        ProjectTask task = new ProjectTask(
                "TASK-1", "Первая задача", TaskStatus.TODO, 2
        );

        assertThrows(IllegalArgumentException.class,
                () -> prioritizer.chooseHigherPriority(null, task));
    }
}
