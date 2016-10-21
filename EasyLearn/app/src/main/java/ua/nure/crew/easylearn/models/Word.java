package ua.nure.crew.easylearn.models;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String spelling;
    private List<String> definitions;
    private List<String> translations;
    private List<String> examples;

    public Word(String spelling,
                List<String> definitions,
                List<String> translations,
                List<String> examples){
        this.spelling = spelling;
        this.definitions = new ArrayList<String>(definitions);
        this.translations = new ArrayList<String>(translations);
        this.examples = new ArrayList<String>(examples);
    }

    public String getSpelling(){
        return spelling;
    }

    public List<String> getDefinitions(){
        return definitions;
    }

    public List<String> getTranslations(){
        return translations;
    }

    public List<String> getExamples(){
        return examples;
    }
}
