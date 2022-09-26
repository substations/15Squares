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
    private Paint textColor;

    int number;

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
        blue.setColor(Color.BLUE);

        textColor = new Paint();
        textColor.setColor(Color.BLACK);
        textColor.setTextSize(100);
        textColor.setTextAlign(Paint.Align.CENTER);

        number = 1;

        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {

                squares[i][j] = new Square(
                        space + initLeft + (initRight - initLeft) * i,
                        space + initTop + (initBottom - initTop) * j,
                        initRight + (initRight - initLeft) * i,
                        initBottom + (initBottom - initTop) * j,
                        number,
                        white);

                number++;

                Log.d("Testing","init " + squares[i][j].getNum());
            }
        }
    }

    protected void onDraw(Canvas canvas) {

        Log.d("Testing","draw ");
        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {

                canvas.drawRect(
                        squares[i][j].getLeft(),
                        squares[i][j].getTop(),
                        squares[i][j].getRight(),
                        squares[i][j].getBottom(),
                        squares[i][j].getColor());

                canvas.drawText("" + squares[i][j].getNum(),
                        squares[i][j].getRight() - (squares[i][j].getRight() - squares[i][j].getLeft())/2,
                        squares[i][j].getBottom() - (squares[i][j].getBottom() - squares[i][j].getTop())/2,
                        textColor);

                //squares[i][j].setColor(white);
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

                        Log.i("Testing","tap" + i + " " + j + " ");

                        //if square isn't open and is clicked, it becomes open and unable to be tapped
                        if(!squares[i][j].isOpen()){
                            squares[i][j].toggleIsOpen();
                        }

                        invalidate();
                        return true;

                    }
                }
            }


        }
        return false;
    }
}
