package ua.nure.crew.easylearn.view.vocabulary;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import ua.nure.crew.easylearn.R;

public class WordChildViewHolder extends ChildViewHolder {

    TextView mTranscriptionTextView;
    TextView mDefinitionTextView;
    TextView mExamplesTextView;

    public WordChildViewHolder(View itemView) {
        super(itemView);
        mTranscriptionTextView = (TextView) itemView.findViewById(R.id.vocab_transcription_text_view);
        mDefinitionTextView = (TextView) itemView.findViewById(R.id.vocab_definition_text_view);
        mExamplesTextView = (TextView) itemView.findViewById(R.id.vocab_examples_text_view);
    }

    public void bind(Translation.Details details) {
        mTranscriptionTextView.setText("[ " + details.mTranscription + " ]");
        mDefinitionTextView.setText("Definition: " + details.mDefinition);

        int i = 1;
        StringBuilder examples = new StringBuilder();
        examples.append("Examples:\n");
        for (String example : details.mExamples) {
            examples.append(i++ + ". ");
            examples.append(example);
            examples.append("\n");
        }
        mExamplesTextView.setText(examples.toString());
    }


}