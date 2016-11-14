package ua.nure.crew.easylearn.view.type;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.view.vocabulary.VocabularyActivity;

public class TaskTypeAdapter extends RecyclerView.Adapter<TaskTypeAdapter.TaskTypeViewHolder> {

    public static class TaskTypeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        CardView mTaskTypeCardView;
        TextView mTaskTypeNameTextView;
        TextView mTaskTypeInfoTextView;
        ImageView mTaskTypeImageView;

        TaskTypeViewHolder(View itemView) {
            super(itemView);
            mTaskTypeCardView = (CardView)itemView.findViewById(R.id.task_type_card_view);
            mTaskTypeCardView.setOnClickListener(this);

            mTaskTypeNameTextView = (TextView)itemView.findViewById(R.id.task_type_name);
            mTaskTypeNameTextView.setOnClickListener(this);

            mTaskTypeInfoTextView = (TextView)itemView.findViewById(R.id.task_type_info);
            mTaskTypeInfoTextView.setOnClickListener(this);

            mTaskTypeImageView = (ImageView)itemView.findViewById(R.id.task_type_image);
            mTaskTypeImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (getAdapterPosition()) {
                case 3:
                    Intent intent = new Intent(v.getContext(), VocabularyActivity.class);
                    v.getContext().startActivity(intent);
            }
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
        TaskType taskType = mTaskTypeList.get(i);

        taskTypeViewHolder.mTaskTypeNameTextView.setText(taskType.name);
        taskTypeViewHolder.mTaskTypeImageView.setImageResource(taskType.imageId);

        mTaskTypeList.get(i);
        String info = "Tasks completed: " + taskType.passedTasks + " / " + taskType.tasks;
        taskTypeViewHolder.mTaskTypeInfoTextView.setText(info);
    }

    @Override
    public int getItemCount() {
        return mTaskTypeList.size();
    }
}