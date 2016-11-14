package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;
import ua.nure.crew.easylearn.data.models.Vocabulary;
import org.xml.sax.Attributes;


/**
 * Created by Slava on 15.10.2016.
 */
public class VocabularyHandler extends XmlElementHandler {

    public VocabularyHandler()
    {
        tagName = "Vocabulary";
        data = new Vocabulary();
    }

    /*@Override
    public XmlParsable getData() {
        return data;
    }*/

    @Override
    protected void switchHandler(String qName, Attributes attributes) {
        innerHandler = new WordHandler(attributes);
    }
}
