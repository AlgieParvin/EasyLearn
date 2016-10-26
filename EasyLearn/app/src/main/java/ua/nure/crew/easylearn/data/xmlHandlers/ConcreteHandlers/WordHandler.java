package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.exceptions.XmlParsingException;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;
import ua.nure.crew.easylearn.data.models.Word;
import org.xml.sax.Attributes;

/**
 * Created by Slava on 19.10.2016.
 */
public class WordHandler extends XmlElementHandler {

    public WordHandler(Attributes attributes)
    {
        tagName = "Translation";
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
