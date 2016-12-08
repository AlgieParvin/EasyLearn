package ua.nure.crew.easylearn.view.testTasks;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.models.Question;

public class WatchMistakesFragment extends Fragment {

    private WatchMistakesActivity mContext;

    private Question mQuestion;
    private String mChosenAnswer;

    private TextView mQuestionTextView;
    private Button[] mAnswerButtons;

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);

        Bundle args = getArguments();

        String questionText = args.getString(TestTasksActivity.QUESTION);
        List<String> answers = args.getStringArrayList(TestTasksActivity.ANSWERS);
        String correctAnswer = args.getString(TestTasksActivity.CORRECT_ANSWER);
        mQuestion = new Question(questionText, answers, correctAnswer);

        mChosenAnswer = args.getString(TestTasksActivity.CHOSEN_ANSWER);

        mAnswerButtons = new Button[TestTasksFragment.QUESTIONS_MAX];
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
        mAnswerButtons[4] = (Button) view.findViewById(R.id.tests_fifth_answer_button);

        if (mQuestion.getAnswers().size() < 5) {
            mAnswerButtons[4].setVisibility(View.INVISIBLE);
        }
        if (mQuestion.getAnswers().size() < 4) {
            mAnswerButtons[3].setVisibility(View.INVISIBLE);
        }
        if (mQuestion.getAnswers().size() < 3) {
            mAnswerButtons[2].setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < mQuestion.getAnswers().size(); i++) {
            String answer = mQuestion.getAnswers().get(i);
            mAnswerButtons[i].setText(answer);
            mAnswerButtons[i].setClickable(false);

            if (mChosenAnswer.equalsIgnoreCase(answer)) {
                mAnswerButtons[i].setBackgroundResource(R.drawable.answer_button_wrong);
            }

            if (mQuestion.isAnswerRight(answer)) {
                mAnswerButtons[i].setBackgroundResource(R.drawable.answer_button_right);
            }
        }

        return view;
    }
}