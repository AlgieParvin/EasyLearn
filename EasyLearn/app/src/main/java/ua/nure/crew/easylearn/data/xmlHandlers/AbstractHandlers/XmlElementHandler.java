package ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers;

import ua.nure.crew.easylearn.exceptions.ContentTypeException;
import ua.nure.crew.easylearn.exceptions.InitializationException;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Created by Slava on 19.10.2016.
 */
public abstract class XmlElementHandler extends SimpleHandler {

    protected SimpleHandler innerHandler;
    protected String tagName;


    protected abstract void switchHandler(String qName, Attributes attributes) throws XmlParsingException;

    //protected abstract void addData(String qName);


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // ������ ����� Handler, ���� �������� ���.
        if (innerHandler == null || !innerHandler.isWorking()) {
            switchHandler(qName, attributes);
        }
        else{
            innerHandler.startElement(uri, localName, qName, attributes);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // ��������� ������ � ������������� � �������� ������ ������.
        if (qName.equalsIgnoreCase(tagName))
        {
            if (innerHandler.isWorking())
                throw new XmlParsingException("Tag not closed.");
            endWork();
        }
        // �������� ��������� �������� ������.
        else
        {
            innerHandler.endElement(uri, localName, qName);
            // � ������� ������, ���� ������ ��������. ����� �������� ����� Handler � ����� �����.
            if (!innerHandler.isWorking())
            {
                try {
                    data.addData(innerHandler.getData());
                } catch (ContentTypeException | InitializationException e) {
                    throw new SAXException("Object constructing fail.", e);
                }
                //addData(qName); // innerHandler.getData();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (innerHandler != null)
            innerHandler.characters(ch, start, length);
    }

    @Override
    protected void endWork() {
        super.endWork();
        data.endInit();
    }
}
