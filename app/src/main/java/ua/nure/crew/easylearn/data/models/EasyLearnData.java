package ua.nure.crew.easylearn.data.models;

import ua.nure.crew.easylearn.exceptions.ContentTypeException;
import ua.nure.crew.easylearn.exceptions.InitializationException;
import ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slava on 21.10.2016.
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
