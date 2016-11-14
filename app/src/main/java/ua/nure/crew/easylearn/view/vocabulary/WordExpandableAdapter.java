package ua.nure.crew.easylearn.view.vocabulary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

import ua.nure.crew.easylearn.R;

public class WordExpandableAdapter extends ExpandableRecyclerAdapter<WordParentViewHolder, WordChildViewHolder> {

    private LayoutInflater mInflator;

    public WordExpandableAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public WordParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View recipeView = mInflator.inflate(R.layout.vocabulary_item, parentViewGroup, false);
        return new WordParentViewHolder(recipeView);
    }

    @Override
    public WordChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View ingredientView = mInflator.inflate(R.layout.vocabulary_item_expand, childViewGroup, false);
        return new WordChildViewHolder(ingredientView);
    }

    @Override
    public void onBindParentViewHolder(WordParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Translation translation = (Translation) parentListItem;
        parentViewHolder.bind(translation);
    }

    @Override
    public void onBindChildViewHolder(WordChildViewHolder childViewHolder, int position, Object childListItem) {
        Translation.Details details = (Translation.Details) childListItem;
        childViewHolder.bind(details);
    }
}
