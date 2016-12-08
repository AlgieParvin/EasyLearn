package ua.nure.crew.easylearn.view.testTasks;

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

public class TestTasksFragment extends Fragment {

    public static final int QUESTIONS_MAX = 5;

    private TestTasksActivity mContext;

    private Question mQuestion;

    private TextView mQuestionTextView;
    private Button[] mAnswerButtons;

    private View.OnClickListener answerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String answer = ((Button) view).getText().toString();
            mContext.checkAnswer(answer);
        }
    };

    public static Fragment newInstance(Question q) {
        Bundle args = new Bundle();

        args.putString(TestTasksActivity.QUESTION, q.getQuestionText());
        args.putStringArrayList(TestTasksActivity.ANSWERS, new ArrayList<String>(q.getAnswers()));
        args.putString(TestTasksActivity.CORRECT_ANSWER, q.getRightAnswer());

        Fragment fragment = new TestTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);

        mContext = (TestTasksActivity) getActivity();

        Bundle args = getArguments();
        String questionText = args.getString(TestTasksActivity.QUESTION);
        List<String> answers = args.getStringArrayList(TestTasksActivity.ANSWERS);
        String correctAnswers = args.getString(TestTasksActivity.CORRECT_ANSWER);
        mQuestion = new Question(questionText, answers, correctAnswers);

        mAnswerButtons = new Button[QUESTIONS_MAX];
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
            mAnswerButtons[i].setText(mQuestion.getAnswers().get(i));
            mAnswerButtons[i].setOnClickListener(answerListener);
        }

        return view;
    }
}
