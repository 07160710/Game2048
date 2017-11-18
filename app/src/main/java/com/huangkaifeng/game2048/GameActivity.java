package com.huangkaifeng.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private TextView tvScore;
    private static GameActivity mainActivity = null;
    private int score = 0;

    public GameActivity(){
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tvScore = (TextView) findViewById(R.id.tvScore);
    }
    public void clearScore(){
        score = 0;
        showScore();
    }
    public void showScore(){
        tvScore.setText(score+"");
    }
    public void addScore(int s){
        score += s;
        showScore();
    }


    public static GameActivity getMainActivity() {
        return mainActivity;
    }
}
