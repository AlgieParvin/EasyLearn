package ua.nure.crew.easylearn.data.models;


import  ua.nure.crew.easylearn.exceptions.ContentTypeException;
import  ua.nure.crew.easylearn.exceptions.InitializationException;
import  ua.nure.crew.easylearn.data.xmlHandlers.TypeDataPair;
import  ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;

public class Video implements XmlParsable {

    private String videoUrl;
    private Vocabulary vocabulary;
    private Test test;
    private boolean initialized;

    public Video(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Video(String videoUrl,
                 Vocabulary vocabulary,
                 Test test)
    {
        this.videoUrl = videoUrl;
        this.vocabulary = vocabulary;
        this.test = test;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Vocabulary getVocabulary(){ return vocabulary; }

    public Test getTest(){ return test; }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized");

        if (part instanceof Test) {
            if (test != null) {
                throw new ContentTypeException("More than one test in a video.");
            }
            this.test = (Test) part;
        } else if (part instanceof Vocabulary) {
            if (vocabulary != null) {
                throw new ContentTypeException("More than one vocabulary in a video.");
            }
            this.vocabulary = (Vocabulary) part;
        } else if (part instanceof TypeDataPair)
        {
            TypeDataPair typeDataPair = (TypeDataPair)part;
            String data = typeDataPair.getData();
            switch (typeDataPair.getType())
            {
                case "VideoUrl":
                    videoUrl = data;
                    break;
                default:
                    throw new ContentTypeException("Wrong data type");
            }
        }
        else
        {
            throw new ContentTypeException("Not correct type for a video part: " + part.getClass().getName());
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }

}
