package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import org.xml.sax.Attributes;
import ua.nure.crew.easylearn.data.models.Question;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;

/**
 * Created by Slava on 27.10.2016.
 */

public class QuestionHandler extends XmlElementHandler {

    public QuestionHandler(Attributes attributes)
    {
        tagName = "Question";
        data = new Question(attributes.getValue("question"));

    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        innerHandler = new TextHandler(qName);
    }
}
