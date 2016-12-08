package ua.nure.crew.easylearn.view.testTasks;

import android.content.res.AssetManager;

import java.util.List;

import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Question;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;

/**
 * Created by algie on 12/8/16.
 */

public class TestLoader {

    public List<Question> getTestQuestions(AssetManager manager, String topic)
            throws DataLoadingException {
        return SimpleLoader.getInstance().loadTopic(manager, topic).getTest();
    }

    public List<Question> getInitialQuestions(AssetManager manager)
            throws DataLoadingException {
        return SimpleLoader.getInstance().loadInitialTest(manager).getQuestions(15, 2);
    }
}
