package com.example.obstaclerace.UI;

import java.util.ArrayList;

public class InternalDB {

    private ArrayList<Score> scores;
    public InternalDB() { }
    public ArrayList<Score> getRecords() {
        if(scores == null)
            scores = new ArrayList<>();
        return scores;
    }
}
