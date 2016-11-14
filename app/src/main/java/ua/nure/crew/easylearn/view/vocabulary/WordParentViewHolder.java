package ua.nure.crew.easylearn.view.vocabulary;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import ua.nure.crew.easylearn.R;

public class WordParentViewHolder extends ParentViewHolder {

    private TextView mWordTextView;

    public WordParentViewHolder(View itemView) {
        super(itemView);
        mWordTextView = (TextView) itemView.findViewById(R.id.vocab_translation_text_view);
    }

    public void bind(Translation tr) {
        mWordTextView.setText(tr.mEnglish + " - " + tr.mRussian);
    }
}