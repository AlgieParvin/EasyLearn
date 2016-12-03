package ua.nure.crew.easylearn.data.models;

import  ua.nure.crew.easylearn.exceptions.ContentTypeException;
import  ua.nure.crew.easylearn.exceptions.InitializationException;
import  ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;

import java.util.ArrayList;
import java.util.List;

public class Options implements XmlParsable {
    private boolean initialized;
    private List<Option> options;


    public Options() { this.options = new ArrayList<>(); }

    public Options(List<Option> options){
        this.options = new ArrayList<>(options);
    }

    public List<Option> getOptions(){
        return options;
    }

    public void addOption(Option option){ options.add(option); }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized.");

        if (part instanceof Option)
        {
            Option option = (Option)part;
            this.options.add(option);
        }
        else
        {
            throw new ContentTypeException("Only options can be added.");
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }
}