package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTaskTest {

    @Test
    void shouldCreateTask() {
        ProjectTask task = new ProjectTask(
                "TASK-1",
                "Создать форму авторизации",
                TaskStatus.TODO,
                5
        );

        assertEquals("TASK-1", task.getId());
        assertEquals("Создать форму авторизации", task.getTitle());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(5, task.getEstimatedHours());
    }

    @Test
    void shouldNotAllowEmptyId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("", "Авторизация", TaskStatus.TODO, 5));
    }

    @Test
    void shouldNotAllowNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask(null, "Авторизация", TaskStatus.TODO, 5));
    }

    @Test
    void shouldNotAllowEmptyTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", " ", TaskStatus.TODO, 5));
    }

    @Test
    void shouldNotAllowNullTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", null, TaskStatus.TODO, 5));
    }

    @Test
    void shouldNotAllowNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", "Авторизация", null, 5));
    }

    @Test
    void shouldNotAllowNegativeEstimatedHours() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", "Авторизация", TaskStatus.TODO, -1));
    }

    @Test
    void shouldChangeStatus() {
        ProjectTask task = new ProjectTask(
                "TASK-1", "Авторизация", TaskStatus.TODO, 5
        );

        task.changeStatus(TaskStatus.IN_PROGRESS);

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void shouldNotAllowNullStatusWhenChangingStatus() {
        ProjectTask task = new ProjectTask(
                "TASK-1", "Авторизация", TaskStatus.TODO, 5
        );

        assertThrows(IllegalArgumentException.class,
                () -> task.changeStatus(null));

        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test
    void shouldDetectCompletedTask() {
        ProjectTask task = new ProjectTask(
                "TASK-1", "Авторизация", TaskStatus.TODO, 5
        );

        assertFalse(task.isCompleted());

        task.changeStatus(TaskStatus.DONE);

        assertTrue(task.isCompleted());
    }

    @Test
    void shouldIncreaseEstimatedHours() {
        ProjectTask task = new ProjectTask(
                "TASK-1", "Авторизация", TaskStatus.TODO, 5
        );

        task.increaseEstimatedHours(3);

        assertEquals(8, task.getEstimatedHours());
    }

    @Test
    void shouldNotAllowNonPositiveHoursIncrease() {
        ProjectTask task = new ProjectTask(
                "TASK-1", "Авторизация", TaskStatus.TODO, 5
        );

        assertThrows(IllegalArgumentException.class,
                () -> task.increaseEstimatedHours(0));

        assertThrows(IllegalArgumentException.class,
                () -> task.increaseEstimatedHours(-2));

        assertEquals(5, task.getEstimatedHours());
    }
}
