package ua.nure.crew.easylearn.type;

import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.R;

public class TaskType {

    String name;
    int tasks;
    int passedTasks;
    int imageId;
    boolean tasksInfo;

    // This static method is responsible for getting the quantity of all and passed tasks from
    // database to TaskTypeActivity.
    // For now it returns some fictional data.
    public static List<TaskType> loadTypes(String topic) {
        List<TaskType> taskTypes = new ArrayList<>();
        taskTypes.add(new TaskType("Rebuses", 50, 5, R.drawable.rebus));
        taskTypes.add(new TaskType("Tests", 15, 2, R.drawable.test));
        taskTypes.add(new TaskType("Video", 10, 6, R.drawable.video));
        taskTypes.add(new TaskType("Vocabulary", 20, 10, R.drawable.vocabulary));
        return taskTypes;
    }

    public TaskType(String name, int tasks, int passedTasks, int imageId) {
        this.name = name;
        this.tasks = tasks;
        this.passedTasks = passedTasks;
        this.imageId = imageId;
        this.tasksInfo = true;
    }
}
