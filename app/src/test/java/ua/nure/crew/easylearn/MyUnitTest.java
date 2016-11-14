package ua.nure.crew.easylearn;

import org.junit.Test;

import ua.nure.crew.easylearn.data.dataManaging.OptionsLoader;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Options;
import ua.nure.crew.easylearn.data.models.Word;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.vocabulary.Translation;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MyUnitTest {
    @Test
    public void options_Load() throws Exception {
        Options options;

        try {
            InputStream is = new FileInputStream("assets\\options.xml");
            options = OptionsLoader.getInstance().loadOptions(is);

        } catch (DataLoadingException e) {
            e.printStackTrace(); // TODO: Change to showing error to user!!!
        }
    }

    @Test
    public void loading_isCorrect() throws Exception {
        List<Translation> mTaskTypeList;


        //mTaskTypeList = Translation.loadData(mTopic);
        List<Word> words;
        try {
            InputStream is = new FileInputStream("assets\\data.xml");
            words = SimpleLoader.getInstance().loadFromXML(is);
            mTaskTypeList = new ArrayList<>(words.size());

            for (int i = 0; i < words.size(); i++)
            {
                mTaskTypeList.add(
                        new Translation(words.get(i)));
            }

        } catch (DataLoadingException e) {
            e.printStackTrace(); // TODO: Change to showing error to user!!!
        }
    }
}