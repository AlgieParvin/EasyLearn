package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import org.xml.sax.Attributes;
import ua.nure.crew.easylearn.data.models.Videos;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;

public class VideosHandler extends XmlElementHandler {
    public VideosHandler()
    {
        tagName = "Videos";
        data = new Videos();
    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        innerHandler = new VideoHandler(attributes);
    }
}
