package com.huangkaifeng.game2048;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/17.
 */

public class Card extends FrameLayout {
    private int num = 0;
    private TextView label;
    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);
        LayoutParams lp = new LayoutParams(-1,-1);
        addView(label,lp);
        setNum(0);
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        label.setText(num + "");
    }

    public boolean equals(Card o) {
        return getNum()==o.getNum();
    }
}
