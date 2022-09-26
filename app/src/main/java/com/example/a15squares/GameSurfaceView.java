package com.example.a15squares;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class GameSurfaceView extends SurfaceView implements View.OnTouchListener{

    private Square squares[][];
    private int gridSize;
    private int space;

    private int initTop;
    private int initLeft;

    private int initBottom;
    private int initRight;

    private int size;
    private Paint white;
    private Paint blue;

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

        white = new Paint();
        white.setColor(Color.WHITE);

        blue = new Paint();
        blue.setColor(Color.WHITE);

        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {

                squares[i][j] = new Square(
                        space + initLeft + (initRight - initLeft) * i,
                        space + initTop + (initBottom - initTop) * j,
                        initRight + (initRight - initLeft) * i,
                        initBottom + (initBottom - initTop) * j,
                        white);
            }
        }
    }

    protected void onDraw(Canvas canvas) {

        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {
                canvas.drawRect(
                        squares[i][j].getLeft(),
                        squares[i][j].getTop(),
                        squares[i][j].getRight(),
                        squares[i][j].getBottom(),
                        white
                );
            }
        }

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        //Log.i("Testing","ont");
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){


            for(int j = 0; j < gridSize; j++) {
                for(int i = 0; i < gridSize; i++) {
                    if(x > squares[i][j].getLeft() && x < squares[i][j].getRight() &&
                            y > squares[i][j].getTop() && y < squares[i][j].getBottom()){
                        Log.i("Testing","tap" + i + "" + j);
                    }
                }
            }
        }
        return false;
    }
}
