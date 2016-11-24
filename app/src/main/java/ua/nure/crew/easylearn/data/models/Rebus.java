package ua.nure.crew.easylearn.data.models;


import  ua.nure.crew.easylearn.exceptions.ContentTypeException;
import  ua.nure.crew.easylearn.exceptions.InitializationException;
import  ua.nure.crew.easylearn.data.xmlHandlers.TypeDataPair;
import  ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;

public class Rebus implements XmlParsable {

    private String imagePath;
    private String correctAnswer;
    private boolean initialized;

    public Rebus(String imagePath,
                 String correctAnswer)
    {
        this.imagePath = imagePath;
        this.correctAnswer = correctAnswer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized");

        if (part instanceof TypeDataPair)
        {
            TypeDataPair typeDataPair = (TypeDataPair)part;
            String data = typeDataPair.getData();
            switch (typeDataPair.getType())
            {
                case "ImagePath":
                    imagePath = data;
                    break;
                case "CorrectAnswer":
                    correctAnswer = data;
                    break;
                default:
                    throw new ContentTypeException("Wrong data type");
            }
        }
        else {
            throw new ContentTypeException("Only type data pairs can be added to rebus");
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }

}
