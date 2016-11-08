package ua.nure.crew.easylearn.data.dataManaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import ua.nure.crew.easylearn.data.models.Test;
import ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers.XmlHandler;
import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;
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
    EasyLearnData data = null;

    public List<Word> loadWords() throws DataLoadingException {
        getData();

        List<Word> res = new ArrayList<>();
        for (int i = 0; i < data.count(); i++)
        {
            XmlParsable part = data.getContent(i);
            if (part instanceof Vocabulary)
            {
                Vocabulary vocabulary = (Vocabulary) part;
                res.addAll(vocabulary.getWords());
            }
        }

        return res;
    }

    public List<Test> loadTests() throws DataLoadingException
    {
        getData();

        List<Test> res = new ArrayList<>();
        for (int i = 0; i < data.count(); i++)
        {
            XmlParsable part = data.getContent(i);
            if (part instanceof Test)
            {
                res.add((Test)part);
            }
        }

        return res;
    }

    public static ResourceLoader getInstance()
    {
        if (instance == null)
            instance = new SimpleLoader();

        return instance;
    }

    private EasyLearnData getData() throws DataLoadingException
    {
        if (data != null)
            return data;

        XmlHandler xml = new XmlHandler();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse(FILENAME, xml);
        }
        catch (SAXException | ParserConfigurationException | IOException e) {
            throw new DataLoadingException("Error in data parsing.\n" + e.getMessage(), e);
        }

        return data = (EasyLearnData) xml.getData();
    }
}
