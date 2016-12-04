package ua.nure.crew.easylearn;

import android.app.Application;
import android.test.ApplicationTestCase;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Question;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.data.models.Word;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class loadTopicTest extends ApplicationTestCase<Application> {

    public loadTopicTest() throws Exception {
        super(Application.class);
    }

    @Override
    protected void runTest() throws Throwable {
        //super.runTest();


        Topic t = SimpleLoader.getInstance().loadTopic(
                getContext().getAssets(),
                "Hard/Wars and weapons");

        List<Question> questionList = t.getTest();
        Question first = questionList.get(0);
        System.out.println(first.getQuestionText());
        for (String answer: first.getAnswers()) {
            System.out.println(answer);
        }

        List<Word> wordList = t.getWords();
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
}