
package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EffortPriorityPolicyTest {

    @Test
    void shouldGiveHigherPriorityToTaskWithLessEffort() {
        EffortPriorityPolicy policy = new EffortPriorityPolicy();

        ProjectTask first = new ProjectTask(
                "TASK-1", "Первая задача", TaskStatus.TODO, 2
        );

        ProjectTask second = new ProjectTask(
                "TASK-2", "Вторая задача", TaskStatus.TODO, 5
        );

        assertTrue(policy.calculatePriority(first) >
                policy.calculatePriority(second));
    }

    @Test
    void shouldRejectNullTask() {
        EffortPriorityPolicy policy = new EffortPriorityPolicy();

        assertThrows(IllegalArgumentException.class,
                () -> policy.calculatePriority(null));
    }
}

