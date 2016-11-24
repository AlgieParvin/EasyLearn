package ua.nure.crew.easylearn.view.testTasks;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.models.Question;

public class TestResultsFragment extends Fragment {

    private TestResultsActivity mParent;
    private Question mQuestion;
    private TextView mQuestionTextView;
    private Button[] mAnswerButtons;
    private int mCurrentQuestion;

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        mParent = (TestResultsActivity) getActivity();
        mCurrentQuestion = getArguments().getInt("ANSWER");
        mQuestion = TestTasksActivity.mQuestions.get(mCurrentQuestion);
        mAnswerButtons = new Button[mQuestion.getAnswers().size()];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceBundle) {
        View view = inflater.inflate(R.layout.tests_fragment, container, false);

        mQuestionTextView = (TextView) view.findViewById(R.id.test_question_text_view);
        mQuestionTextView.setText(mQuestion.getQuestionText());

        mAnswerButtons[0] = (Button) view.findViewById(R.id.tests_first_answer_button);
        mAnswerButtons[1] = (Button) view.findViewById(R.id.tests_second_answer_button);
        mAnswerButtons[2] = (Button) view.findViewById(R.id.tests_third_answer_button);
        mAnswerButtons[3] = (Button) view.findViewById(R.id.tests_fourth_answer_button);

        for (int i = 0; i < mQuestion.getAnswers().size(); i++) {
            String answer = mQuestion.getAnswers().get(i);
            mAnswerButtons[i].setText(answer);
            mAnswerButtons[i].setClickable(false);

            String chosenAnswer = TestTasksActivity.mAnswers.get(mCurrentQuestion);
            if (chosenAnswer.equals(answer)) {
                mAnswerButtons[i].setBackgroundResource(R.drawable.answer_button_wrong);
            }

            if (mQuestion.isAnswerRight(answer)) {
                mAnswerButtons[i].setBackgroundResource(R.drawable.answer_button_right);
            }
        }

        return view;
    }
}

