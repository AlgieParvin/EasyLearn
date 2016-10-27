package ua.nure.crew.easylearn.data.models;

import ua.nure.crew.easylearn.data.xmlHandlers.TypeDataPair;
import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;
import ua.nure.crew.easylearn.exceptions.ContentTypeException;
import ua.nure.crew.easylearn.exceptions.InitializationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slava on 27.10.2016.
 */

public class Test implements XmlParsable {

    private List<Question> questions;
    boolean initialized = false;

    public Test()
    {
        questions = new ArrayList<>();
    }

    public List<Question>  getQuestions()
    {
        return new ArrayList<>(questions);
    }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized");

        if (part instanceof Question)
        {
            Question q = (Question)part;
            this.questions.add(q);
        }
        else
        {
            throw new ContentTypeException("Only questions can be added to tests.");
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }
}
