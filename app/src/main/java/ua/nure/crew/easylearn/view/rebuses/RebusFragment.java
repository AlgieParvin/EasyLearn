package ua.nure.crew.easylearn.view.rebuses;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.models.Rebus;

public class RebusFragment extends Fragment {

    public static final String REBUS="REBUS_FRAGMENT_REBUS";

    private int mRebusPosition;
    private Rebus mRebus;

    RebusesActivity mContext;

    ImageView mRebusImageView;
    EditText mAnswerTextEdit;
    Button mClearAnswerButton;
    Button mCheckAnswerButton;

    public static Fragment newInstance(int rebusPosition) {
        Bundle args = new Bundle();
        args.putInt(REBUS, rebusPosition);

        Fragment fragment = new RebusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (RebusesActivity) getActivity();

        mRebusPosition = getArguments().getInt(REBUS);

        mRebus = mContext.mRebuses.get(mRebusPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceBundle) {
        View view = inflater.inflate(R.layout.rebus_fragment, container, false);

        mRebusImageView = (ImageView) view.findViewById(R.id.rebus_image_view);
        try {
            InputStream is = mContext.getAssets().open(mRebus.getImagePath());
            Drawable image = Drawable.createFromStream(is, null);
            mRebusImageView.setImageDrawable(image);
        } catch (IOException e) {
            Toast.makeText(mContext, "Unfortunately the image is unavailable", Toast.LENGTH_LONG)
                    .show();
        }

        mAnswerTextEdit = (EditText) view.findViewById(R.id.rebus_answer_edit_text);

        mClearAnswerButton = (Button) view.findViewById(R.id.rebus_clear_answer_button);
        mClearAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnswerTextEdit.setText("");
            }
        });

        mCheckAnswerButton = (Button) view.findViewById(R.id.rebus_check_answer_button);
        mCheckAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = mAnswerTextEdit.getText().toString();
                if (mRebus.getCorrectAnswer().equalsIgnoreCase(answer)) {
                    Toast.makeText(mContext, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
