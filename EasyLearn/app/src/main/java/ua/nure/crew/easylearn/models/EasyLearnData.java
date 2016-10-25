package ua.nure.crew.easylearn.models;

import ua.nure.crew.easylearn.XMLHandlers.Exceptions.ContentTypeException;
import ua.nure.crew.easylearn.XMLHandlers.Exceptions.InitializationException;
import ua.nure.crew.easylearn.XMLHandlers.XmlParsable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slava on 21.10.2016.
 *
 * Код для запуска парсинга из файла:
 *
 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();

            XmlElementHandler handler = new XmlHandler();

            parser.parse(new File("xml.xml"), handler);
            XmlParsable elem = handler.getData(); //<--- Это объект, который содержит данные.
            EasyLearnData data = (EasyLearData)elem;
            Vocabulary voc = (Vocabulary)data.getContent(0);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

Сам xml для парсинга:
<EasyLearn>
    <!--<Theme name="Family members">-->
        <Vocabulary>
            <Translation english="Father">
                <Spelling>mglfmlmv;ls,;fv</Spelling>
                <Translation>отец</Translation>
                <Definition>Parent which isn't mother.</Definition>
                <Example>Luke, I am your father.</Example>
            </Translation>
        </Vocabulary>
        <!--<Test>

        </Test>-->

</EasyLearn>

 */

public class EasyLearnData implements XmlParsable {

    private List<XmlParsable> content = new ArrayList<>();
    private boolean initialized = false;


    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized.");

        if (part instanceof EasyLearnData)
        {
            // ��������, ���� �� ������ ��.
            EasyLearnData easyLearnData = (EasyLearnData) part;
            this.content = new ArrayList<>(easyLearnData.content);
        }
        else
            content.add(part);
    }

    @Override
    public void endInit() {
        initialized = true;
    }

    public XmlParsable getContent(int index)
    {
        return content.get(index);
    }

    public int count()
    {
        return content.size();
    }
}
