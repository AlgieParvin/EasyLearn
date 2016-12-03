package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import org.xml.sax.Attributes;
import ua.nure.crew.easylearn.data.models.Rebuses;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;

public class RebusesHandler extends XmlElementHandler {
    public RebusesHandler()
    {
        tagName = "Rebuses";
        data = new Rebuses();
    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        innerHandler = new RebusHandler(attributes);
    }
}
