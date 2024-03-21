package com.example.obstaclerace;

public class Score {
    private String name;
    private int score;

    private double lat = 0.0, lon = 0.0;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getLat() {
        return lat;
    }
    public Score setLat(double lat) {
        this.lat = lat;
        return this;
    }
    public double getLon() {
        return lon;
    }
    public Score setLon(double lon) {
        this.lon = lon;
        return this;
    }
}
