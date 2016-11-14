package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import org.xml.sax.Attributes;
import ua.nure.crew.easylearn.data.models.Test;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;

/**
 * Created by Slava on 27.10.2016.
 */

public class TestHandler extends XmlElementHandler {
    public TestHandler()
    {
        tagName = "Test";
        data = new Test();
    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        innerHandler = new QuestionHandler(attributes);
    }
}
