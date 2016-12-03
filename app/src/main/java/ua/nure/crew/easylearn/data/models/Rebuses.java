package ua.nure.crew.easylearn.data.models;

import  ua.nure.crew.easylearn.exceptions.ContentTypeException;
import  ua.nure.crew.easylearn.exceptions.InitializationException;
import  ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;

import java.util.ArrayList;
import java.util.List;

public class Rebuses implements XmlParsable {
    private boolean initialized;
    private List<Rebus> rebuses;


    public Rebuses() { this.rebuses = new ArrayList<>(); }

    public Rebuses(List<Rebus> rebuses){
        this.rebuses = new ArrayList<>(rebuses);
    }

    public List<Rebus> getRebuses(){
        return rebuses;
    }

    public void addRebus(Rebus rebus){ rebuses.add(rebus); }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized.");

        if (part instanceof Rebus)
        {
            Rebus rebus = (Rebus) part;
            this.rebuses.add(rebus);
        }
        else
        {
            throw new ContentTypeException("Only rebuses can be added.");
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }
}
