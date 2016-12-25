package ua.nure.crew.easylearn.view.type;

import android.content.Context;
import android.content.res.AssetManager;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.RebusLoader;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Question;
import ua.nure.crew.easylearn.data.models.Rebus;
import ua.nure.crew.easylearn.data.models.Video;
import ua.nure.crew.easylearn.data.models.Word;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.testTasks.TestLoader;

public class TaskType {

    String name;
    int tasks;
    int passedTasks;
    int imageId;
    boolean tasksInfo;

    // This static method is responsible for getting the quantity of all and passed tasks from
    // database to TaskTypeActivity.
    // For now it returns some fictional data.
    public static List<TaskType> loadTypes(AssetManager assetManager, String topic) {
        List<TaskType> taskTypes = new ArrayList<>();

        String topicForRebuses = topic.split("/")[1];
        List<Rebus> rebuses = RebusLoader.getInstance().loadRebuses(assetManager, topicForRebuses)
                .getRebuses();
        if (rebuses != null && rebuses.size() != 0) {
            taskTypes.add(new TaskType("Rebuses", 50, 5, R.drawable.rebus));
        }

        try {
            List<Question> questions = new TestLoader().getTestQuestions(assetManager, topic);
            if (questions != null && questions.size() != 0) {
                taskTypes.add(new TaskType("Tests", 15, 2, R.drawable.test));
            }
        } catch (DataLoadingException e) {
            Log.i("TEST", "Tests are not available for topic " + topic);
        }

        try {
            List<Video> videos = new SimpleLoader().loadTopic(assetManager, topic).getVideos();
            if (videos != null && videos.size() != 0) {
                taskTypes.add(new TaskType("Video", 10, 6, R.drawable.video));
            }
        } catch (DataLoadingException e) {
            Log.i("VIDEOS", "Videos are not available for topic " + topic);
        }

        try {
            List<Word> words = SimpleLoader.getInstance().loadTopic(assetManager, topic).getWords();
            if (words != null && words.size() != 0) {
                taskTypes.add(new TaskType("Vocabulary", 20, 10, R.drawable.vocabulary));
            }
        } catch (DataLoadingException e) {
            Log.i("WORDS", "Vocabulary is not available for topic " + topic);
        }

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
