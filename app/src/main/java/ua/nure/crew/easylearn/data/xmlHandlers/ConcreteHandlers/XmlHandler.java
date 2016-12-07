package ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers;

import ua.nure.crew.easylearn.data.models.InitialTest;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.exceptions.XmlParsingException;
import ua.nure.crew.easylearn.data.xmlHandlers.AbstractHandlers.XmlElementHandler;
import ua.nure.crew.easylearn.data.models.EasyLearnData;
import org.xml.sax.Attributes;

/**
 * Created by Slava on 19.10.2016.
 */
public class XmlHandler extends XmlElementHandler {
    private DataType dataType;

    public XmlHandler(DataType type) throws XmlParsingException {
        dataType = type;

        switch (type) {
            case Topic:
                data = new Topic();
                break;
            case Options:
                data = new EasyLearnData();
                break;
            case InitialTest:
                data = new InitialTest();
                break;
            default:
                throw new XmlParsingException("This type can not be parsed.");
        }
    }

    @Override
    protected void switchHandler(String qName, Attributes attributes) throws XmlParsingException {
        switch (dataType) {
            case Topic:
                innerHandler = new TopicHandler();
                break;
            case Options:
                innerHandler = new OptionsHandler();
                break;
            case InitialTest:
                innerHandler = new InitialTestHandler();
                break;
            default:
                throw new XmlParsingException("This type can not be parsed.");
        }

    }

    public enum DataType {
        Topic,
        Options,
        InitialTest
    }
}
