package ua.nure.crew.easylearn.view.type;

import android.content.ContextWrapper;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.view.rebuses.RebusesActivity;
import ua.nure.crew.easylearn.view.testTasks.TestTasksActivity;
import ua.nure.crew.easylearn.view.videos.VideoVocabularyActivity;
import ua.nure.crew.easylearn.view.videos.VideosListActivity;
import ua.nure.crew.easylearn.view.vocabulary.VocabularyActivity;

public class TaskTypeAdapter extends RecyclerView.Adapter<TaskTypeAdapter.TaskTypeViewHolder> {

    public static class TaskTypeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        CardView mTaskTypeCardView;
        TextView mTaskTypeNameTextView;
        TextView mTaskTypeInfoTextView;
        ImageView mTaskTypeImageView;

        TaskTypeViewHolder(View itemView) {
            super(itemView);
            mTaskTypeCardView = (CardView)itemView.findViewById(R.id.task_type_card_view);
            //mTaskTypeCardView.setOnClickListener(this);

            mTaskTypeNameTextView = (TextView)itemView.findViewById(R.id.task_type_name);
            mTaskTypeNameTextView.setOnClickListener(this);

            mTaskTypeInfoTextView = (TextView)itemView.findViewById(R.id.task_type_info);
            mTaskTypeInfoTextView.setOnClickListener(this);

            mTaskTypeImageView = (ImageView)itemView.findViewById(R.id.task_type_image);
            mTaskTypeImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ContextWrapper wrapper = (ContextWrapper) v.getContext();
            TaskTypeActivity activity = (TaskTypeActivity) wrapper.getBaseContext();

            Intent intent = null;
            switch ((int)v.getTag()) {
                case 0:
                    intent = new Intent(activity, RebusesActivity.class);
                    break;
                case 1:
                    intent = new Intent(activity, TestTasksActivity.class);
                    break;
                case 2:
                    intent = new Intent(activity, VideosListActivity.class);
                    break;
                case 3:
                    intent = new Intent(activity, VocabularyActivity.class);
                    break;
                default:
                    intent = new Intent(activity, VocabularyActivity.class);
            }
            intent.putExtra(TaskTypeActivity.TOPIC_TAG, activity.mTopic);
            activity.startActivity(intent);
        }
    }

    List<TaskType> mTaskTypeList;

    public TaskTypeAdapter(List<TaskType> taskTypes){
        this.mTaskTypeList = taskTypes;
    }

    @Override
    public TaskTypeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_type_item, viewGroup, false);
        return new TaskTypeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TaskTypeViewHolder taskTypeViewHolder, int i) {
        List<String> taskTypes = new ArrayList<>();
        taskTypes.add("Rebuses");
        taskTypes.add("Tests");
        taskTypes.add("Video");
        taskTypes.add("Vocabulary");

        TaskType taskType = mTaskTypeList.get(i);

        taskTypeViewHolder.mTaskTypeNameTextView.setText(taskType.name);
        taskTypeViewHolder.mTaskTypeImageView.setImageResource(taskType.imageId);

        //taskTypeViewHolder.mTaskTypeCardView.setTag(taskTypes.indexOf(taskType.name));
        taskTypeViewHolder.mTaskTypeImageView.setTag(taskTypes.indexOf(taskType.name));
        taskTypeViewHolder.mTaskTypeInfoTextView.setTag(taskTypes.indexOf(taskType.name));
        taskTypeViewHolder.mTaskTypeNameTextView.setTag(taskTypes.indexOf(taskType.name));

        mTaskTypeList.get(i);
        String info = "Tasks completed: " + taskType.passedTasks + " / " + taskType.tasks;
        taskTypeViewHolder.mTaskTypeInfoTextView.setText(info);
    }

    @Override
    public int getItemCount() {
        return mTaskTypeList.size();
    }
}
