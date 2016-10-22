package ua.nure.crew.easylearn.XMLHandlers;

import ua.nure.crew.easylearn.XMLHandlers.Exceptions.ContentTypeException;
import ua.nure.crew.easylearn.XMLHandlers.Exceptions.InitializationException;

/**
 * Created by Slava on 21.10.2016.
 */
public interface XmlParsable {

    void addData(XmlParsable part) throws ContentTypeException, InitializationException;

    void endInit();
}
