package com.example.a15squares;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class GameSurfaceView extends SurfaceView{

    private Square squares[][];
    private int gridSize;
    private int space;

    private int initTop;
    private int initLeft;

    private int initBottom;
    private int initRight;

    private int size;
    private Paint initColor;

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        gridSize = 4;

        space = 5;
        size = 250;

        initTop = 20;
        initLeft = 20;

        initBottom = initTop + size;
        initRight = initLeft + size;

        squares = new Square[gridSize][gridSize];

        initColor = new Paint();
        initColor.setColor(Color.WHITE);

        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {

                squares[i][j] = new Square(
                        initLeft + (initRight - initLeft) * i,
                        initTop + (initBottom - initTop) * j,
                        initRight + (initRight - initLeft) * i,
                        initBottom + (initBottom - initTop) * j,
                        initColor);
            }
        }
    }

    protected void onDraw(Canvas canvas) {

        //canvas.drawRect(squares[0][0].getRect(),initColor);

        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {
                canvas.drawRect(
                        squares[i][j].getLeft() + space,
                        squares[i][j].getTop() + space,
                        squares[i][j].getRight(),
                        squares[i][j].getBottom(),
                        initColor
                );
            }
        }

    }

    /*
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN){

        }
        return false;
    }
    */
}
