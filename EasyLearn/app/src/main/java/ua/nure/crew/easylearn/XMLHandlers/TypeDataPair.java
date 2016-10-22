package ua.nure.crew.easylearn.XMLHandlers;

import ua.nure.crew.easylearn.XMLHandlers.Exceptions.ContentTypeException;
import ua.nure.crew.easylearn.XMLHandlers.Exceptions.InitializationException;

/**
 * Created by Slava on 20.10.2016.
 */
public class TypeDataPair implements XmlParsable {

    private String type;
    private String data;
    private boolean initialized = false;

    public TypeDataPair() {}

    public TypeDataPair(String type) {
        this.type = type;
    }

    public TypeDataPair(String type, String data) {
        this(type);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized.");

        if (part instanceof TypeDataPair)
        {
            TypeDataPair typeDataPair = (TypeDataPair)part;
            this.data = typeDataPair.data;
        }
        else
        {
            throw new ContentTypeException("Pair of data and it's part can be only created from other pair.");
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }
}
