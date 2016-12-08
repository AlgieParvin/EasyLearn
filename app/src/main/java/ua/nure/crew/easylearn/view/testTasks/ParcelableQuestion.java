package ua.nure.crew.easylearn.view.testTasks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ua.nure.crew.easylearn.data.models.Question;

/**
 * Created by algie on 12/8/16.
 */

public class ParcelableQuestion implements Parcelable {

    String question;
    List<String> answers;
    String rightAnswer;

    public ParcelableQuestion(Question q) {
        question = q.getQuestionText();
        answers = q.getAnswers();
        rightAnswer = q.getRightAnswer();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.question);
        dest.writeStringList(this.answers);
        dest.writeString(this.rightAnswer);
    }

    protected ParcelableQuestion(Parcel in) {
        this.question = in.readString();
        this.answers = in.createStringArrayList();
        this.rightAnswer = in.readString();
    }

    public static final Parcelable.Creator<ParcelableQuestion> CREATOR = new Parcelable.Creator<ParcelableQuestion>() {
        @Override
        public ParcelableQuestion createFromParcel(Parcel source) {
            return new ParcelableQuestion(source);
        }

        @Override
        public ParcelableQuestion[] newArray(int size) {
            return new ParcelableQuestion[size];
        }
    };
}
