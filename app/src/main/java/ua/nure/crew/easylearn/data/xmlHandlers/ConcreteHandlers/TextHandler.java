package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.SimpleHandler;
import ua.nure.crew.easylearn.exceptions.ContentTypeException;
import ua.nure.crew.easylearn.exceptions.InitializationException;
import ua.nure.crew.easylearn.data.xmlHandlers.TypeDataPair;
import org.xml.sax.SAXException;

/**
 * Created by Slava on 19.10.2016.
 */
public class TextHandler extends SimpleHandler {

    public TextHandler(String qName)
    {
        data = new TypeDataPair(qName);
    }


    /*@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        dataType = qName;
    }*/

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        endWork();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).trim();
        try {
            this.data.addData(
                new TypeDataPair(null, data));
        } catch (ContentTypeException | InitializationException e) {
            throw new SAXException("Text extraction fail.", e);
        }
    }
}
