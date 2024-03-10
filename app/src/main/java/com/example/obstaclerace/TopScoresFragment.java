package com.example.obstaclerace;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TopScoresFragment extends Fragment {

    private ClickListener listener;
    private MaterialButton[] score_buttons;
    private ArrayList<Score> scores;
    public TopScoresFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_scores, container, false);
        findViews(view);
        addPlayer();
        return view;
    }

    private void addPlayer() {
        String js = MSPV3.getMe().getString("MY_DB", "");
        InternalDB DB= new Gson().fromJson(js, InternalDB.class);
        scores = DB.getRecords();
        if(scores.size()<10){
            for (int j = 0; j < scores.size(); j++) {
                score_buttons[j].setText("Name: "+scores.get(j).getName()+ " Score: "+scores.get(j).getScore());
                Score temp = scores.get(j);
                int finalJ = j;
                score_buttons[j].setOnClickListener(v -> listener.onScoreClick(finalJ));
            }
        }else{
            for (int i = 0; i < 10; i++) {
                top[i].setText("Name: "+scores.get(i).getName()+ " Score: "+scores.get(i).getScore());
                Score temp = scores.get(i);
                top[i].setOnClickListener(v -> callBackList.zoom(temp.getLon(),temp.getLat()));
            }
        }
    }

    private void findViews(View view) {
        score_buttons = new MaterialButton[]{
                view.findViewById(R.id.score_1),
                view.findViewById(R.id.score_2),
                view.findViewById(R.id.score_3),
                view.findViewById(R.id.score_4),
                view.findViewById(R.id.score_5),
                view.findViewById(R.id.score_6),
                view.findViewById(R.id.score_7),
                view.findViewById(R.id.score_8),
                view.findViewById(R.id.score_9),
                view.findViewById(R.id.score_10)
        };
    }
}