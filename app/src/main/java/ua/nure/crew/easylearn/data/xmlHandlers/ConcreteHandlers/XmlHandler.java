package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.exceptions.XmlParsingException;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.data.models.EasyLearnData;
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
