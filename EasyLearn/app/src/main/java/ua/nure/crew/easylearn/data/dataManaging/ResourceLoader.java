package ua.nure.crew.easylearn.data.dataManaging;

import java.util.List;

import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.data.models.Word;

public interface ResourceLoader {

    List<Word> loadFromXML() throws DataLoadingException;
}
