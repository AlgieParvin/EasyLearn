package ua.nure.crew.easylearn.XMLHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.XMLHandlers.Exceptions.XmlParsingException;
import ua.nure.crew.easylearn.XMLHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.models.EasyLearnData;
import org.xml.sax.Attributes;

/**
 * Created by Slava on 19.10.2016.
 */
public class XmlHandler extends XmlElementHandler {

    public XmlHandler()
    {
        data = new EasyLearnData();
    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        innerHandler = new RootHandler();
    }
}
