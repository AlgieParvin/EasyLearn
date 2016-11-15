package ua.nure.crew.easylearn.data.dataManaging;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;


import ua.nure.crew.easylearn.data.xmlHandlers.ConcreteHandlers.XmlHandler;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.data.models.EasyLearnData;
import ua.nure.crew.easylearn.data.models.Options;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class OptionsLoader {
    private static OptionsLoader instance = null;

    public Options loadOptions(InputStream is) throws DataLoadingException {
        Options options;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XmlHandler xml = new XmlHandler();
            parser.parse(is, xml);

            EasyLearnData data = (EasyLearnData) xml.getData();
            options = (Options) data.getContent(0);
        }
        catch (SAXException | ParserConfigurationException | IOException e) {
            throw new DataLoadingException("Error in data parsing.\n" + e.getMessage(), e);
        }

        return options;
    }

    public static OptionsLoader getInstance()
    {
        if (instance == null)
            instance = new OptionsLoader();

        return instance;
    }
}
