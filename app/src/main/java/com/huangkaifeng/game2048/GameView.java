package com.huangkaifeng.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by Administrator on 2017/11/17.
 */

public class GameView extends GridLayout {
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
    }
    private void addCard(int cardWidh,int cardHeight){
        Card c;
        for(int y = 0; y < 4; y++){
            for (int x = 0;x < 4; x++){
                c = new Card(getContext());
                c.setNum(2);
                addView(c,cardWidh,cardHeight);

                cardsMap[x][y] = c;
            }
        }
    }

    private void swipeLeft(){
    }
    private void swipeRight(){
    }
    private void swipeUp(){
    }
    private void swipeDown(){
    }
    private Card[][] cardsMap = new Card[4][4];
}
