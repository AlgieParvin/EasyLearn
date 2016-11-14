package ua.nure.crew.easylearn.interfaces;

public interface Test {
    Task getCurrentTask();
    boolean hasNextTask();
    void nextTask();
    void reset();
}