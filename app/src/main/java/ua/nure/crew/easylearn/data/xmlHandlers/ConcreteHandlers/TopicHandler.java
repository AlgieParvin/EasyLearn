package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import org.xml.sax.Attributes;

/**
 * Created by Slava on 19.10.2016.
 */
public class TopicHandler extends XmlElementHandler {

    public TopicHandler() {
        tagName = "Topic";
        data = new Topic();
    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        switch (qName)
        {
            case "Vocabulary":
                innerHandler = new VocabularyHandler();
                break;
            case "Test":
                innerHandler = new TestHandler();
                break;
            case "Videos":
                innerHandler = new VideosHandler();
                break;
            case "Rebuses":
                innerHandler = new RebusesHandler();
                break;
            default:
                throw new XmlParsingException("Unexpected tag type:" + qName);
        }
    }
}
