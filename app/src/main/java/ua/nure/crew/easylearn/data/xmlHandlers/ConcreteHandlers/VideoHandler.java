package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import org.xml.sax.Attributes;
import ua.nure.crew.easylearn.data.models.Video;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;

public class VideoHandler extends XmlElementHandler {

    public VideoHandler(Attributes attributes)
    {
        tagName = "Video";
        data = new Video(attributes.getValue("videoUrl"));

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
            default:
                throw new XmlParsingException("Unexpected tag type:" + qName);
        }
    }
}
