package ua.nure.crew.easylearn.data.models;

import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;
import ua.nure.crew.easylearn.exceptions.ContentTypeException;
import ua.nure.crew.easylearn.exceptions.InitializationException;

import java.util.ArrayList;
import java.util.List;


public class InitialTest implements XmlParsable {

    private List<Test> tests;
    boolean initialized = false;

    public InitialTest()
    {
        tests = new ArrayList<>();
    }

    public List<Test>  getTests() {

        return new ArrayList<>(tests);
    }

    /**
     * Returns a list containing nGeneral random questions selected from general question pool and
     * nLevelBased random questions selected from level based question pool.
     * @param nGeneral number of random questions from general question pool
     * @param nLevelBased number of random questions from general question pool
     * @return a list containing random questions
     */
    public List<Question> getQuestions(int nGeneral, int nLevelBased) {
        List<Question> questions = new ArrayList<>(tests.get(0).getNQuestions(nGeneral));
        for (int i = 1; i < this.tests.size(); i++) {
            questions.addAll(tests.get(i).getNQuestions(nLevelBased));
        }
        return questions;
    }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized.");

        if (part instanceof Test)
        {
            Test test = (Test)part;
            this.tests.add(test);
        }
        else if (part instanceof InitialTest) {
            this.tests = new ArrayList<>(((InitialTest)part).tests);
        }
        else
        {
            throw new ContentTypeException("Only tests can be added to initial test.");
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }
}