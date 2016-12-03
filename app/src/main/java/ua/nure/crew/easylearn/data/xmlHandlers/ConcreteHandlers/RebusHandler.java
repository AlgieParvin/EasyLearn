package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import org.xml.sax.Attributes;
import ua.nure.crew.easylearn.data.models.Rebus;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;

public class RebusHandler extends XmlElementHandler {

    public RebusHandler(Attributes attributes)
    {
        tagName = "Rebus";
        data = new Rebus();

    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        innerHandler = new TextHandler(qName);
    }
}
