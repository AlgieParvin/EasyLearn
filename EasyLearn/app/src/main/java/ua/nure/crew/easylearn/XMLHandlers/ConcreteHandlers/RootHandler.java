package ua.nure.crew.easylearn.XMLHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.XMLHandlers.Exceptions.XmlParsingException;
import ua.nure.crew.easylearn.XMLHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.models.EasyLearnData;
import org.xml.sax.Attributes;

/**
 * Created by Slava on 19.10.2016.
 */
public class RootHandler extends XmlElementHandler {

    public RootHandler() {
        tagName = "EasyLearn";
        data = new EasyLearnData();
    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        switch (qName)
        {
            case "Vocabulary":
                innerHandler = new VocabularyHandler();
                break;
            //case "Test":
                // Change!
                //innerHandler = new TextHandler(qName);
                //break;
            default:
                throw new XmlParsingException("Unexpected tag type:" + qName);
        }
    }
}
