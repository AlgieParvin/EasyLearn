package ua.nure.crew.easylearn.view.testTasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.models.Question;
import ua.nure.crew.easylearn.view.topics.TopicsActivity;

public class TestResultsFragment extends Fragment {

    public static final String QUESTIONS = "TEST_RESULTS_ACTIVITY_QUESTIONS";
    public static final String CORRECT_ANSWERS = "TEST_RESULTS_ACTIVITY_CORRECT_ANSWERS";

    private TestTasksActivity mContext;

    private int mCorrectAnswers;
    private int mQuestions;
    private String mLevel;

    private TextView mCorrectAnswersTextView;
    private TextView mWrongAnswersTextView;
    private Button mFinishTestButton;
    private Button mShowMistakesButton;
    private TextView mLevelTextView;
    private TextView mLevelResultsTextView;

    public static Fragment newInstance(int questions, int correctAnswers) {
        Bundle args = new Bundle();
        args.putInt(QUESTIONS, questions);
        args.putInt(CORRECT_ANSWERS, correctAnswers);

        Fragment fragment = new TestResultsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        mContext = (TestTasksActivity) getActivity();

        Bundle args = getArguments();
        mQuestions = args.getInt(QUESTIONS);
        mCorrectAnswers = args.getInt(CORRECT_ANSWERS);

        calculateLevel();
        mContext.prepareForReturn(mLevel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceBundle) {
        View view = inflater.inflate(R.layout.tests_finish_fragment, container, false);

        mCorrectAnswersTextView = (TextView) view.findViewById(R.id.tests_results_correct_answers);
        mCorrectAnswersTextView.setText(String.valueOf(mCorrectAnswers));

        mWrongAnswersTextView = (TextView) view.findViewById(R.id.tests_results_incorrect_answers);
        mWrongAnswersTextView.setText(String.valueOf(mQuestions - mCorrectAnswers));

        mFinishTestButton = (Button) view.findViewById(R.id.tests_finish_button);
        mFinishTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.finish();
            }
        });

        mShowMistakesButton = (Button) view.findViewById(R.id.tests_mistakes_button);
        mShowMistakesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();

                args.putString(TestTasksActivity.TOPIC_TAG, mContext.mTopic);
                args.putString(TestTasksActivity.PURPOSE_TAG, mContext.mPurpose);

                ArrayList<String> answers = new ArrayList<>(mContext.mAnswers);
                args.putStringArrayList(TestTasksActivity.ANSWERS, answers);

                // ! implement a method for this
                ArrayList<ParcelableQuestion> parcelableQuestions = new ArrayList<>();
                for (Question q : mContext.mQuestions) {
                    parcelableQuestions.add(new ParcelableQuestion(q));
                }
                args.putParcelableArrayList(TestTasksActivity.QUESTIONS, parcelableQuestions);

                Intent intent = new Intent(mContext, WatchMistakesActivity.class);
                intent.putExtras(args);
                mContext.startActivity(intent);
            }
        });

        mLevelTextView = (TextView) view.findViewById(R.id.tests_results_level_label);
        mLevelResultsTextView = (TextView) view.findViewById(R.id.tests_results_level);

        if (mContext.mPurpose.equals(mContext.ENTRY)) {
            mLevelResultsTextView.setText(mLevel);
        } else {
            mLevelTextView.setVisibility(View.INVISIBLE);
            mLevelResultsTextView.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    private void calculateLevel() {
        float res = (float)mCorrectAnswers / (float)mQuestions;
        if (res < 0.7) {
            mLevel = TopicsActivity.EASY;
        } else if (res < 0.9) {
            mLevel = TopicsActivity.MEDIUM;
        } else {
            mLevel = TopicsActivity.HARD;
        }
    }
}