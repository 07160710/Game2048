package com.huangkaifeng.game2048;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */

public class GameView extends GridLayout {
    private Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<Point>();

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initGameView();
    }
    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }
    //初始化
    private void initGameView(){
        setColumnCount(4);

        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new View.OnTouchListener(){
            private float startX,startY,offSetX,offSetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offSetX = event.getX()-startX;
                        offSetY = event.getY()-startY;

                        if(Math.abs(offSetX) > Math.abs(offSetY)){
                            if(offSetX < -5){
                                swipeLeft();
                            }else if(offSetX > 5){
                                swipeRight();
                            }
                        }else {
                            if (offSetY < -5){
                                swipeUp();
                            }else if(offSetY > 5){
                                swipeDown();
                            }
                        }
                        break;
                }

                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w,h)-10)/4;
        addCard(cardWidth,cardWidth);
        startGame();
    }
    private void addCard(int cardWidh,int cardHeight){
        Card c;
        for(int y = 0; y < 4; y++){
            for (int x = 0;x < 4; x++){
                c = new Card(getContext());
                c.setNum(0);
                addView(c,cardWidh,cardHeight);

                cardsMap[x][y] = c;
            }
        }
    }
    private void startGame(){
        GameActivity.getMainActivity().clearScore();
        for(int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardsMap[x][y].setNum(0);
            }
        }
        //添加两个随机数
        addRandomNum();
        addRandomNum();
    }

    private void addRandomNum(){
        emptyPoints.clear();

        for(int y = 0; y < 4; y++){
            for(int x = 0;x < 4; x++){
                if(cardsMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }

        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
    }

    private void swipeLeft(){
        boolean marge = false;
        for(int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

                for(int x1 = x+1;x1 < 4;x1++){
                    if (cardsMap[x1][y].getNum() > 0){
                        if(cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x--;
                            marge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            GameActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (marge){
            addRandomNum();
        }
    }
    private void swipeRight(){
        boolean marge = false;
        for(int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {

                for(int x1 = x-1;x1 >= 0;x1--){
                    if (cardsMap[x1][y].getNum() > 0){
                        if(cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x++;
                            marge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            GameActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (marge){
            addRandomNum();
        }
    }
    private void swipeUp(){
        boolean marge = false;
        for(int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                for(int y1 = y+1;y1 < 4;y1++){
                    if (cardsMap[x][y1].getNum() > 0){
                        if(cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y--;
                            marge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            GameActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (marge){
            addRandomNum();
        }
    }
    private void swipeDown(){
        boolean marge = false;
        for(int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {

                for(int y1 = y-1;y1 >= 0;y1--){
                    if (cardsMap[x][y1].getNum() > 0){
                        if(cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y++;
                            marge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            GameActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            marge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (marge){
            addRandomNum();
        }
    }

}
