package ua.nure.crew.easylearn.data.models;

import  ua.nure.crew.easylearn.exceptions.ContentTypeException;
import  ua.nure.crew.easylearn.exceptions.InitializationException;
import  ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;

import java.util.ArrayList;
import java.util.List;

public class Vocabulary implements XmlParsable {
	private boolean initialized;
	private List<Word> words;


    public Vocabulary()
    {
        this.words = new ArrayList<>();
    }

	public Vocabulary(List<Word> words){
		this.words = new ArrayList<Word>(words);
	}
	
	public List<Word> getWords(){
		return words;
	}

	@Override
	public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
		if (initialized)
            throw new InitializationException("Already initialized.");

        if (part instanceof Word)
        {
            Word word = (Word)part;
            this.words.add(word);
        }
        else
        {
            throw new ContentTypeException("Only words can be added to vocabulary.");
        }
	}

	@Override
	public void endInit() {
		initialized = true;
	}
}
