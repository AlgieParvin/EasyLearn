package ua.nure.crew.easylearn.view.topics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.nure.crew.easylearn.R;

public class TopicsFragment extends Fragment {

    public final static String LEVEL_TAG = "LEVEL";
    private String level;

    public TopicsFragment() { }

    public static Fragment newInstance(String level) {
        Bundle args = new Bundle();
        args.putString(LEVEL_TAG, level);

        Fragment fragment = new TopicsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        level = getArguments().getString(LEVEL_TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topics, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.topics_recycler_view);
        rv.setHasFixedSize(true);

        TopicsAdapter adapter = new TopicsAdapter(getActivity(), level);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

}
