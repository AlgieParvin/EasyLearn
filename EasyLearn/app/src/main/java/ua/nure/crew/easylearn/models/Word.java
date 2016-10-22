package ua.nure.crew.easylearn.models;


import  ua.nure.crew.easylearn.XMLHandlers.Exceptions.ContentTypeException;
import  ua.nure.crew.easylearn.XMLHandlers.Exceptions.InitializationException;
import  ua.nure.crew.easylearn.XMLHandlers.TypeDataPair;
import  ua.nure.crew.easylearn.XMLHandlers.XmlParsable;

import java.util.ArrayList;
import java.util.List;

public class Word implements XmlParsable {
    private String english;
    private String spelling;
	private List<String> definitions;
	private List<String> translations;
	private List<String> examples;
    private boolean initialized;

    public Word(String english)
    {
        this.english = english;

        this.definitions = new ArrayList<>();
        this.translations = new ArrayList<>();
        this.examples = new ArrayList<>();
    }
	
	public Word(String english,
                String spelling,
			List<String> definitions, 
			List<String> translations, 
			List<String> examples){
        this(english);
		this.spelling = spelling;
		this.definitions = new ArrayList<String>(definitions);
		this.translations = new ArrayList<String>(translations);
		this.examples = new ArrayList<String>(examples);
	}

    public String getEnglish() {
        return english;
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
                case "Spelling":
                    spelling = data;
                    break;
                case "Definition":
                    definitions.add(data);
                    break;
                case "Translation":
                    translations.add(data);
                    break;
                case "Example":
                    examples.add(data);
                    break;
                default:
                    throw new ContentTypeException("Wrong data type");
            }
		}
		else {
			throw new ContentTypeException("Only type data pairs can be added to word");
		}
	}

    @Override
    public void endInit() {
        initialized = true;
    }

}
