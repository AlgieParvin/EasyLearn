package ua.nure.crew.easylearn.view.testTasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.models.Question;

public class TestTasksFragment extends Fragment {

    private TestTasksActivity mParent;
    private Question mQuestion;
    private TextView mQuestionTextView;
    private Button[] mAnswerButtons;

    private View.OnClickListener answerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String answer = ((Button) view).getText().toString();
            mParent.mAnswers.add(answer);
            if (mParent.pager.getCurrentItem() == mParent.mQuestions.size() - 1) {
                Intent intent = new Intent(mParent, TestResultsActivity.class);
                startActivity(intent);
            } else {
                mParent.pager.setCurrentItem(mParent.pager.getCurrentItem() + 1);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        mParent = (TestTasksActivity) getActivity();
        mQuestion = mParent.mQuestions.get(mParent.currentQuestion++);
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
            mAnswerButtons[i].setText(mQuestion.getAnswers().get(i));
            mAnswerButtons[i].setOnClickListener(answerListener);
        }
        return view;
    }
}
