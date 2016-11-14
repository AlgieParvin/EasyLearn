package ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers;

import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Slava on 19.10.2016.
 */
public abstract class SimpleHandler extends DefaultHandler {

    private boolean working = true;
    protected XmlParsable data;

    public boolean isWorking() {
        return working;
    }

    public XmlParsable getData() {
        return data;
    }

    /*public abstract void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException;*/

    public abstract void endElement(String uri, String localName, String qName) throws SAXException;

    @Override
    public abstract void characters(char[] ch, int start, int length) throws SAXException;

    protected void endWork()
    {
        working = false;
    }
}
