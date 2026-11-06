package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusPriorityPolicyTest {

    @Test
    void shouldGiveHighestPriorityToInProgressTask() {
        StatusPriorityPolicy policy = new StatusPriorityPolicy();

        ProjectTask first = new ProjectTask(
                "TASK-1", "Первая задача", TaskStatus.IN_PROGRESS, 5
        );

        ProjectTask second = new ProjectTask(
                "TASK-2", "Вторая задача", TaskStatus.TODO, 2
        );

        assertTrue(policy.calculatePriority(first) >
                policy.calculatePriority(second));
    }

    @Test
    void shouldGiveLowestPriorityToCompletedTask() {
        StatusPriorityPolicy policy = new StatusPriorityPolicy();

        ProjectTask completed = new ProjectTask(
                "TASK-1", "Завершённая задача", TaskStatus.DONE, 3
        );

        ProjectTask waiting = new ProjectTask(
                "TASK-2", "Ожидающая задача", TaskStatus.TODO, 5
        );

        assertTrue(policy.calculatePriority(completed) <
                policy.calculatePriority(waiting));
    }

    @Test
    void shouldRejectNullTask() {
        StatusPriorityPolicy policy = new StatusPriorityPolicy();

        assertThrows(IllegalArgumentException.class,
                () -> policy.calculatePriority(null));
    }
}

