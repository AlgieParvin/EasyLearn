package ua.nure.crew.easylearn.data.dataManaging;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import org.xml.sax.SAXException;
import ua.nure.crew.easylearn.data.models.*;
import ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers.XmlHandler;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SimpleLoader implements ResourceLoader {
    private static ResourceLoader instance = null;
    private final String EXTENSION = ".xml";

    public static ResourceLoader getInstance()
    {
        if (instance == null)
            instance = new SimpleLoader();

        return instance;
    }

    public Topic loadTopic(AssetManager manager, String topicName) throws DataLoadingException
    {
        XmlHandler xml;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            xml = new XmlHandler(XmlHandler.DataType.Topic);

            parser.parse(manager.open(topicName + EXTENSION), xml);
        }
        catch (SAXException | ParserConfigurationException | IOException e) {
            throw new DataLoadingException("Error in data parsing.\n" + e.getMessage(), e);
        }

        return (Topic) xml.getData();
    }

    public String[] getTopicNames(AssetManager manager, String difficulty) throws DataLoadingException {
        String[] res;
        try {
            res = manager.list(difficulty);
        }
        catch (IOException e) {
            throw new DataLoadingException("Error in loading topic names on the difficulty " + difficulty + ".", e);
        }
        return res;
    }
}
