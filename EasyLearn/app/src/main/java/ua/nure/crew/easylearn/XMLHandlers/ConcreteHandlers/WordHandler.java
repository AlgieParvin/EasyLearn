package ua.nure.crew.easylearn.XMLHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.XMLHandlers.Exceptions.XmlParsingException;
import ua.nure.crew.easylearn.XMLHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.XMLHandlers.XmlParsable;
import ua.nure.crew.easylearn.models.Word;
import org.xml.sax.Attributes;

/**
 * Created by Slava on 19.10.2016.
 */
public class WordHandler extends XmlElementHandler {

    public WordHandler(Attributes attributes)
    {
        tagName = "Word";
        data = new Word(attributes.getValue("english"));
    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        innerHandler = new TextHandler(qName);
    }

    /*@Override
    protected void addData(String qName) {
        TypeDataPair pair = (TypeDataPair)innerHandler.getData();
        word += pair.getType() + " " + pair.getData();
    }*/

    @Override
    public XmlParsable getData() {
        return data;
    }
}
