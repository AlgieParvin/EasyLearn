package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import org.xml.sax.Attributes;
import ua.nure.crew.easylearn.data.models.Option;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;


public class OptionHandler extends XmlElementHandler {

    public OptionHandler(Attributes attributes)
    {
        tagName = "Option";
        data = new Option(attributes.getValue("name"));

    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        innerHandler = new TextHandler(qName);
    }
}