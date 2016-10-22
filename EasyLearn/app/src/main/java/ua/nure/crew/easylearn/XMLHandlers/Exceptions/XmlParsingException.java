package ua.nure.crew.easylearn.XMLHandlers.Exceptions;

import org.xml.sax.SAXException;

/**
 * Created by Slava on 19.10.2016.
 */
public class XmlParsingException extends SAXException {

    public XmlParsingException(String message)
    {
        super(message);
    }

}
