package ua.nure.crew.easylearn.view.enterTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.view.testTasks.TestTasksActivity;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class DialogueActivity extends Activity {

    Button yesButton;
    Button noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.enter_test_dialogue);

        yesButton = (Button) findViewById(R.id.enter_testing_yes);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogueActivity.this, TestTasksActivity.class);
                intent.putExtra(TaskTypeActivity.TOPIC_TAG, "Medium/Crime and Punishment");
                intent.putExtra(TestTasksActivity.PURPOSE_TAG, TestTasksActivity.ENTRY);
                startActivity(intent);
                finish();
            }
        });

        noButton = (Button) findViewById(R.id.enter_testing_no);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
