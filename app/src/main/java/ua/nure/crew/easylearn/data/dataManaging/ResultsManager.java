package ua.nure.crew.easylearn.data.dataManaging;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ResultsManager{
    private static final String RESULTS_DATA_PATH = "appResults.data";

    //name of test - max solved
    private Map<String, Integer> tests = new HashMap<>();

    //name of video - max solved
    private Map<String, Integer> videos = new HashMap<>();

    //name of rebus - solved/not solved
    private Map<String, Boolean> rebuses = new HashMap<>();

    //Number of solved rebuses
    private Integer rebusesSolved = 0;

    private static ResultsManager instance = null;


    public static ResultsManager getInstance(){
        if( instance == null ){
            instance = new ResultsManager();
        }
        return instance;
    }

    public Map<String, Integer> getTests(){
        return tests;
    }

    public Map<String, Integer> getVideos(){
        return videos;
    }

    public Map<String, Boolean> getRebuses(){
        return rebuses;
    }

    public Integer getRebusesSolved(){
        return rebusesSolved;
    }


    public static void saveData(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(RESULTS_DATA_PATH, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getInstance().tests);
            oos.writeObject(getInstance().videos);
            oos.writeObject(getInstance().rebuses);
            oos.writeObject(getInstance().rebusesSolved);
            oos.close();
            fos.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void loadData(Context context){
        try {
            FileInputStream fis = context.openFileInput(RESULTS_DATA_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            getInstance().tests = (HashMap<String, Integer>)ois.readObject();
            getInstance().videos = (HashMap<String, Integer>)ois.readObject();
            getInstance().rebuses = (HashMap<String, Boolean>)ois.readObject();
            getInstance().rebusesSolved = (Integer)ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
