package ua.nure.crew.easylearn.data.dataManaging;


import android.content.res.AssetManager;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;

public interface ResourceLoader {

    Topic loadTopic(AssetManager manager, String topicName) throws DataLoadingException;

    String[] getTopicNames(AssetManager manager, String difficulty) throws DataLoadingException;
}