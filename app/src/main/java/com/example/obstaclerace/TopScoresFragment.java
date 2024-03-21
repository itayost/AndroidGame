package com.example.obstaclerace;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TopScoresFragment extends Fragment {

    private AppCompatActivity activity;
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

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setListener(ClickListener listener) {this.listener = listener;}

    private void addPlayer() {
        String js = MSPV3.getMe().getString("InternalDB", "");
        InternalDB DB = new Gson().fromJson(js, InternalDB.class);
        scores = DB.getRecords();
        for (int j = 0; j < 10; j++) {
            if (j < scores.size()) {
                Score temp = scores.get(j);
                score_buttons[j].setText(temp.getName());
                score_buttons[j].setOnClickListener(v -> listener.onScoreClick(temp.getLat(), temp.getLon()));
            }
            else
            {
                score_buttons[j].setVisibility(View.INVISIBLE);
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