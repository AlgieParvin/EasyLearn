package ua.nure.crew.easylearn.data.dataManaging;

import java.io.InputStream;
import java.util.List;

import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.data.models.Word;

public interface ResourceLoader {

    List<Word> loadFromXML(InputStream io) throws DataLoadingException;
}
