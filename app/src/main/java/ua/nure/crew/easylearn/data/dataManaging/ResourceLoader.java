package ua.nure.crew.easylearn.data.dataManaging;

import java.io.InputStream;
import java.util.List;

import android.content.res.AssetManager;
import android.provider.ContactsContract;
import ua.nure.crew.easylearn.data.models.Test;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.data.models.Word;

public interface ResourceLoader {

    Topic loadTopic(AssetManager manager, String topicName) throws DataLoadingException;
}