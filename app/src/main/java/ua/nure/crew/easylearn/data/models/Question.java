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

public class Question implements XmlParsable {
    private boolean initialized = false;
    private List<String> answers;
    private String rightAnswer;
    private String question;

    public Question(String question)
    {
        this.question = question;
        answers = new ArrayList<>();
    }

    public List<String> getAnswers()
    {
        return new ArrayList<>(answers);
    }

    public boolean isAnswerRight(String variant)
    {
        return rightAnswer.equalsIgnoreCase(variant);
    }

    public String getQuestionText()
    {
        return question;
    }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized");

        if (part instanceof TypeDataPair)
        {
            TypeDataPair typeDataPair = (TypeDataPair)part;
            String data = typeDataPair.getData();

            String type = typeDataPair.getType();
            if(!type.equalsIgnoreCase("Right") && !type.equalsIgnoreCase("Wrong"))
            {
                throw new ContentTypeException("Wrong data type");
            }

            if(type.equalsIgnoreCase("Right"))
            {
                if(this.rightAnswer != null)
                {
                    throw new ContentTypeException("More than 1 correct answer.");
                }
                this.rightAnswer = data;
            }

            answers.add(data);
        }
        else
        {
            throw new ContentTypeException("Only type data pairs can be added to question");
        }

    }

    @Override
    public void endInit() {
        initialized = true;
    }
}
