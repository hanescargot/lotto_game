package com.pyrion.game.lotto_shopping.research;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pyrion.game.lotto_shopping.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragmentRanking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentRanking extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogFragmentRanking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogFragmentBingo.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogFragmentRanking newInstance(String param1, String param2) {
        DialogFragmentRanking fragment = new DialogFragmentRanking();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.research_dialog_ranking, container);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        AdapterRecyclerViewRanking adapter = new AdapterRecyclerViewRanking(getActivity());

        recyclerView.setAdapter(adapter);
//        return inflater.inflate(R.layout.research_dialog_ranking, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        AdapterRecyclerViewRanking adapter = new AdapterRecyclerViewRanking(getActivity());
//
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}