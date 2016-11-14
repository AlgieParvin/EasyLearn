package ua.nure.crew.easylearn.exceptions;

/**
 * Created by Slava on 26.10.2016.
 */
public class DataLoadingException extends Exception {

    public DataLoadingException(String message, Exception e) {
        super(message, e);
    }
}
