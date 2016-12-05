package ua.nure.crew.easylearn.data.models;

import  ua.nure.crew.easylearn.exceptions.ContentTypeException;
import  ua.nure.crew.easylearn.exceptions.InitializationException;
import  ua.nure.crew.easylearn.exceptions.InvalidValueException;
import  ua.nure.crew.easylearn.data.xmlHandlers.TypeDataPair;
import  ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;

import java.util.ArrayList;
import java.util.List;

public class Option implements XmlParsable {
    private static final String EMPTY_STRING = "";

    private String name;
    private List<String> values;
    private String currentValue;
    private boolean initialized;

    public Option(String name)
    {
        this.name = name;

        this.values = new ArrayList<>();
        currentValue = EMPTY_STRING;
    }

    public Option(String name,
                  List<String> values){
        this.name = name;
        this.values = new ArrayList<>(values);

        currentValue = EMPTY_STRING;
    }

    public Option(String name,
                  List<String> values,
                  String currentValue){
        this.name = name;
        this.values = new ArrayList<>(values);
        this.currentValue = currentValue;
    }

    public String getName() {
        return name;
    }

    public List<String> getValues(){
        return values;
    }

    public String getCurrentValue(){
        return currentValue;
    }

    public void setCurrentValue(String currentValue) throws InvalidValueException {
        if (!values.contains(currentValue)){
            throw new InvalidValueException("There is no such option");
        } else {
            this.currentValue = currentValue;
        }
    }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized");

        if (part instanceof TypeDataPair)
        {
            TypeDataPair typeDataPair = (TypeDataPair)part;
            String data = typeDataPair.getData();
            switch (typeDataPair.getType())
            {
                case "CurrentValue":
                    currentValue = data;
                    break;
                case "Value":
                    values.add(data);
                    break;
                default:
                    throw new ContentTypeException("Wrong data type");
            }
        }
        else {
            throw new ContentTypeException("Only type data pairs can be added to option");
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }

}