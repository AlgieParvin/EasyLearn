package ua.nure.crew.easylearn.data.dataManaging;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xml.sax.SAXException;
import ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers.XmlHandler;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.data.models.EasyLearnData;
import ua.nure.crew.easylearn.data.models.Vocabulary;
import ua.nure.crew.easylearn.data.models.Word;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SimpleLoader implements ResourceLoader {
    private final String FILENAME = "Data.xml";
    private static ResourceLoader instance = null;

    public List<Word> loadFromXML(InputStream is) throws DataLoadingException {
        Vocabulary vocabulary;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XmlHandler xml = new XmlHandler();
            parser.parse(is, xml);

            EasyLearnData data = (EasyLearnData) xml.getData();
            vocabulary = (Vocabulary) data.getContent(0);
        }
        catch (SAXException | ParserConfigurationException | IOException e) {
            throw new DataLoadingException("Error in data parsing.\n" + e.getMessage(), e);
        }

        return vocabulary.getWords();
    }

    public static ResourceLoader getInstance()
    {
        if (instance == null)
            instance = new SimpleLoader();

        return instance;
    }
}
