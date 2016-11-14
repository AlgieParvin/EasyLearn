package ua.nure.crew.easylearn.data.xmlHandlers;

import ua.nure.crew.easylearn.exceptions.ContentTypeException;
import ua.nure.crew.easylearn.exceptions.InitializationException;

/**
 * Created by Slava on 21.10.2016.
 */
public interface XmlParsable {

    void addData(XmlParsable part) throws ContentTypeException, InitializationException;

    void endInit();
}
