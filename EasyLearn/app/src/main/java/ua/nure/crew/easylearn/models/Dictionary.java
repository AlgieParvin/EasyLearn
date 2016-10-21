package ua.nure.crew.easylearn.models;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<Word> words;

    public Dictionary(List<Word> words){
        this.words = new ArrayList<Word>(words);
    }

    public List<Word> getWords(){
        return words;
    }
}
