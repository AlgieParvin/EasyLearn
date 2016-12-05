package ua.nure.crew.easylearn.view.enterTest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.enter_test_dialogue);

        yesButton = (Button) findViewById(R.id.enter_testing_yes);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogueActivity.this, TestTasksActivity.class);

                // ! for now 'Crime and Punishment';
                intent.putExtra(TaskTypeActivity.TOPIC_TAG, "Medium/Crime and Punishment");
                intent.putExtra(TestTasksActivity.PURPOSE_TAG, TestTasksActivity.ENTRY);
                startActivityForResult(intent, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String level = intent.getStringExtra(TestTasksActivity.LEVEL_TAG);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(TestTasksActivity.LEVEL_TAG, level);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }
}
