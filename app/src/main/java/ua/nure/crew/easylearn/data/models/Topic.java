package ua.nure.crew.easylearn.data.models;

import android.support.annotation.NonNull;
import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;
import ua.nure.crew.easylearn.exceptions.ContentTypeException;
import ua.nure.crew.easylearn.exceptions.InitializationException;

import java.util.*;

/**
 * Created by Slava on 08.11.2016.
 */

public class Topic implements XmlParsable {
    private boolean initialized;
    private Test test;
    private Vocabulary vocabulary;
    private Videos videos;
    //private Rebuses rebuses;

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
        } else if (part instanceof Topic) {
            Topic other = (Topic) part;
            this.initialized = other.initialized;
            this.vocabulary = other.vocabulary;
            this.test = other.test;
            this.videos = other.videos;
        } else if (part instanceof Videos) {
            if (videos != null) {
                throw new ContentTypeException("More than one video collection in a topic.");
            }
            this.videos = (Videos) part;
        } /*else if (part instanceof Rebuses) {
            if (rebuses != null) {
                throw new ContentTypeException("More than one rebus collection in a topic.");
            }
            this.rebuses = (Rebuses) part;
        }*/
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

    public List<Video> getVideos() {
        if (videos == null)
            return new ArrayList<>();
        return videos.getVideos();
    }

    /*public List<Rebus> getRebuses() { return rebuses.getRebuses(); }*/
}
