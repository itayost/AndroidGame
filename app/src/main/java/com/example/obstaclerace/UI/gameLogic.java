package com.example.obstaclerace.UI;

import android.view.View;

public class gameLogic {

    private static final int EGG = -1;
    private static final int HEART = 1;
    private static int Turns_For_Heart = 4;
    private int chicken_place;
    private int lives;
    private int hits = 0;
    private int[][] board;
    private int cols;
    private int rows;

    private int score;

    public gameLogic(int rows, int cols, int lives) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
        this.chicken_place = cols / 2;
        this.lives = lives;
        this.score = 0;
    }

    private void addObject(){
        Turns_For_Heart--;
        int randomColumn = (int) (Math.random() * cols);
        if (Turns_For_Heart == 0){
            Turns_For_Heart = 4;
            board[0][randomColumn] = HEART;
        }
        else{
            board[0][randomColumn] = EGG;
        }
    }

    private void moveObjects(){
        for (int i = 0; i < cols; i ++){
            if (board[rows - 1][i] != 0){
                tryToHit(i, board[rows - 1][i]);
                board[rows - 1][i] = 0;
                score += 100;
            }
        }

        for (int i = rows - 2; i >= 0; i--){
            for (int j = 0; j < cols; j ++){
                if(board[i][j] != 0){
                    board[i + 1][j] = board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }

    public int getLives() {
        return lives - hits;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getChicken_place() {
        return chicken_place;
    }

    public int getScore() {
        return score;
    }

    public boolean movePlayer(int step){
        if (0 <= (chicken_place + step) && (chicken_place + step) < cols){
            chicken_place += step;
            return true;
        }
        return false;
    }

    private void tryToHit(int i, int changeLives) {
        if(chicken_place == i){
            if(board[rows - 1][i] == EGG && hits < lives){
                hits++;
                score -= 100;
            }
            else if(board[rows - 1][i] == HEART && hits > 0){
                hits--;
            }
        }
    }

    public boolean gameOver(){
        return hits == lives;
    }

    public void nextMove(){
        this.moveObjects();
        this.addObject();
    }
}
