package ua.nure.crew.easylearn.data.dataManaging;

import android.content.res.AssetManager;
import android.util.Log;

import ua.nure.crew.easylearn.data.models.Rebus;
import ua.nure.crew.easylearn.data.models.Rebuses;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;

import java.io.IOException;

/**
 * Created by Slava on 05.12.2016.
 */
public class RebusLoader {
    private static RebusLoader ourInstance = new RebusLoader();
    private static final String REBUS_FOLDER = "Rebuses/";
    
    public static RebusLoader getInstance() {
        return ourInstance;
    }
    
    private RebusLoader() {
    }

    public Rebuses loadRebuses(AssetManager manager, String topicName) {
        Rebuses res = new Rebuses();

        String[] rebusList;
        try {
            String path = REBUS_FOLDER + topicName;
            rebusList = manager.list(path);
        } catch (IOException e) {
            return res;
            // throw new DataLoadingException("Error in rebus loading:\n" + e.getMessage(), e);
        }
        for (String rebus : rebusList) {
            String[] parts = rebus.split("\\.");
            String correctAnswer = parts[0];
            Rebus toAdd = new Rebus(REBUS_FOLDER + topicName + "/" + rebus, correctAnswer);
            res.addRebus(toAdd);
        }
        return res;
    }
}
