package ua.nure.crew.easylearn.view.testTasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ua.nure.crew.easylearn.R;

public class TestFinishFragment extends Fragment {

    private TestTasksActivity mContext;
    private TextView mCorrectAnswersTextView;
    private TextView mWrongAnswersTextView;
    private Button mFinishTestButton;
    private Button mShowMistakesButton;

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceBundle) {
        View view = inflater.inflate(R.layout.tests_finish_fragment, container, false);

        mContext = (TestTasksActivity) getActivity();
        Log.i("FRAGMENT TEST", "" + mContext.mCorrectAnswers);

        mCorrectAnswersTextView = (TextView) view.findViewById(R.id.tests_results_correct_answers);
        mCorrectAnswersTextView.setText("" + mContext.mCorrectAnswers);

        mWrongAnswersTextView = (TextView) view.findViewById(R.id.tests_results_incorrect_answers);
        mWrongAnswersTextView.setText("" + (TestTasksActivity.mQuestions.size() - mContext.mCorrectAnswers));

        mFinishTestButton = (Button) view.findViewById(R.id.tests_finish_button);
        mFinishTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.kill();
            }
        });

        mShowMistakesButton = (Button) view.findViewById(R.id.tests_mistakes_button);
        mShowMistakesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TestResultsActivity.class);
                mContext.startActivity(intent);
            }
        });

        return view;
    }
}
