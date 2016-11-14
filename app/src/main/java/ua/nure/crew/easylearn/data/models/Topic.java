package ua.nure.crew.easylearn.data.models;

import android.provider.UserDictionary;
import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;
import ua.nure.crew.easylearn.exceptions.ContentTypeException;
import ua.nure.crew.easylearn.exceptions.InitializationException;

import java.util.List;

/**
 * Created by Slava on 08.11.2016.
 */

public class Topic implements XmlParsable {
    private boolean initialized;
    private Test test;
    private Vocabulary vocabulary;

    public Topic() {
    }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized) {
            throw new InitializationException("Already initialized.");
        }

        if (part instanceof Test) {
            if (test != null) {
                throw new ContentTypeException("More than one test in a topic.");
            }
            this.test = (Test) part;
        } else if (part instanceof Vocabulary) {
            if (vocabulary != null) {
                throw new ContentTypeException("More than one vocabulary in a topic.");
            }
            this.vocabulary = (Vocabulary) part;
        }
        else if (part instanceof Topic) {
            Topic other = (Topic) part;
            this.vocabulary = other.vocabulary;
            this.test = other.test;
        }
        // Some more are coming...
        else
        {
            throw new ContentTypeException("Not correct type for a topic part: " + part.getClass().getName());
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }

    public List<Question> getTest() {
        return test.getQuestions();
    }

    public List<Word> getWords() {
        return vocabulary.getWords();
    }
}
