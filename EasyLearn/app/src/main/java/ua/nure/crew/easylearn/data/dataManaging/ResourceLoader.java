package ua.nure.crew.easylearn.data.dataManaging;

import java.util.List;

import ua.nure.crew.easylearn.data.models.Test;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.data.models.Word;

public interface ResourceLoader {

    List<Word> loadWords() throws DataLoadingException;

    List<Test> loadTests() throws DataLoadingException;
}
