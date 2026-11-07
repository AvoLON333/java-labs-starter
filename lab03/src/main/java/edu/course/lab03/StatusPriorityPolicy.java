package edu.course.lab03;

public class StatusPriorityPolicy implements PriorityPolicy {

    @Override
    public int calculatePriority(ProjectTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Задача не null");
        }

        switch (task.getStatus()) {
            case IN_PROGRESS:
                return 3;

            case TODO:
                return 2;

            case DONE:
                return 1;

            default:
                throw new IllegalArgumentException("Неизвестный статус задачи");
        }
    }
}

