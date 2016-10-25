package ua.nure.crew.easylearn.vocabulary;

import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.interfaces.ResourseLoader;
import ua.nure.crew.easylearn.models.Word;

public class VocabularyLoader implements ResourseLoader {

    public List<Word> loadFromXML() {
        List<Word> vocabulary = new ArrayList<>();
        return vocabulary;
    }
}
