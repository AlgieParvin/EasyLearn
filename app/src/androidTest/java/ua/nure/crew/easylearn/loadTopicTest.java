package ua.nure.crew.easylearn;

import android.app.Application;
import android.test.ApplicationTestCase;
import ua.nure.crew.easylearn.data.dataManaging.RebusLoader;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class loadTopicTest extends ApplicationTestCase<Application> {

    String TOPIC_NAME = "Crime and Punishment";

    public loadTopicTest() throws Exception {
        super(Application.class);
    }

    @Override
    protected void runTest() throws Throwable {
        //super.runTest();

        String[] strings = getContext().getAssets().list("");
        strings = getContext().getAssets().list("Easy");
        strings = getContext().getAssets().list("Rebuses");
        strings = getContext().getAssets().list("Rebuses/Travelling");
        strings = getContext().getAssets().list("Rebuses/Crime and Punishment");


        Topic t = SimpleLoader.getInstance().loadTopic(
                getContext().getAssets(),
                "Medium/" + TOPIC_NAME);

        testTest(t.getTest());
        testVocabulary(t.getWords());

        Video v = t.getVideos().get(0);
        String url = v.getVideoUrl();
        System.out.println(url);

        testTest(v.getTest().getQuestions());
        testVocabulary(v.getVocabulary().getWords()); /**/

        testRebuses(RebusLoader.getInstance());
    }

    private void testTest(List<Question> questionList) {
        Question first = questionList.get(0);
        System.out.println(first.getQuestionText());
        for (String answer: first.getAnswers()) {
            System.out.println(answer);
        }
    }

    private void testVocabulary(List<Word> wordList) {
        Word firstW = wordList.get(0);
        System.out.println("eng: " + firstW.getEnglish());
        System.out.println("spell: " + firstW.getSpelling());
        for (String translation: firstW.getTranslations()) {
            System.out.println("translation: " + translation);
        }
        for (String def: firstW.getDefinitions()) {
            System.out.println("def: " + def);
        }

        for (String ex: firstW.getExamples()) {
            System.out.println("ex: " + ex);
        }
    }

    private void testRebuses(RebusLoader loader) throws IOException {
        Rebuses re = loader.loadRebuses(getContext().getAssets(), TOPIC_NAME);

        Rebus r = re.getRebuses().get(0);

        InputStream is = getContext().getAssets().open(r.getImagePath());
        is.read();
    }
}