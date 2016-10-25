package ua.nure.crew.easylearn.vocabulary;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.Arrays;
import java.util.List;

import ua.nure.crew.easylearn.interfaces.ResourseLoader;
import ua.nure.crew.easylearn.models.Word;

public class Translation implements ParentListItem {

    String mEnglish;
    String mRussian;
    List<Details> mDetails;
    private VocabularyLoader loader;

    public static class Details {

        public String mTranscription;
        public String mDefinition;
        public List<String> mExamples;

        public Details(String transcription, String definition, List<String> examples) {
            mTranscription = transcription;
            mDefinition = definition;
            mExamples = examples;
        }
    }

    public Translation(String english, String russian, List<Details> details) {
        mEnglish = english;
        mRussian = russian;
        mDetails = details;
        loader = new VocabularyLoader();
    }

    @Override
    public List getChildItemList() {
        return mDetails;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public static List<Translation> loadData(String topic) {
        Translation tr = new Translation("father", "отец",
                Arrays.asList(new Details(
                    "ˈfɑːðə",
                    "a parent who is not a mother",
                    Arrays.asList("Luke, i'm your father.")
                )
        ));

        return Arrays.asList(tr, tr, tr, tr, tr, tr, tr, tr, tr, tr, tr, tr, tr, tr, tr, tr, tr);
    }
}
