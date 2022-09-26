package com.example.a15squares;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Square {
    private int top;
    private int left;

    private int bottom;
    private int right;

    private int size;
    private int space;

    private Rect rect;
    private Paint color;

    public Square(int t, int l, int b, int r, Paint c){
        top = t;
        left = l;
        bottom = b;
        right = r;

        color = c;

        rect = new Rect(t, l, b, r);
    }

    public Rect getRect() {
        return rect;
    }

    public int getTop(){
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public void setColor(int c){
        color.setColor(c);
    }

    public Paint getColor() {
        return color;
    }
}
