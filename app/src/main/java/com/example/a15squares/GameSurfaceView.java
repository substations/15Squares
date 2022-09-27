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
    private int emptyValue;

    private int initTop;
    private int initLeft;

    private int initBottom;
    private int initRight;

    private int size;

    private Paint closedColor;
    private Paint openColor;
    private Paint textColor;

    private int rLoc;
    private int cLoc;

    int number;

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        gridSize = 4;
        emptyValue = gridSize*gridSize;

        space = 5;
        size = 250;

        initTop = 20;
        initLeft = 20;

        initBottom = initTop + size;
        initRight = initLeft + size;

        squares = new Square[gridSize][gridSize];

        closedColor = new Paint();
        closedColor.setColor(Color.WHITE);

        openColor = new Paint();
        openColor.setColor(Color.BLUE);

        textColor = new Paint();
        textColor.setColor(Color.BLACK);
        textColor.setTextSize(100);
        textColor.setTextAlign(Paint.Align.CENTER);

        number = 1;

        rLoc = 2;
        cLoc = 3;

        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {

                squares[i][j] = new Square(
                        space + initLeft + (initRight - initLeft) * i,
                        space + initTop + (initBottom - initTop) * j,
                        initRight + (initRight - initLeft) * i,
                        initBottom + (initBottom - initTop) * j,
                        number,
                        closedColor);

                number++;

                Log.d("Testing","init " + squares[i][j].getNum());
            }
        }
    }

    protected void onDraw(Canvas canvas) {

        Log.d("Testing","draw ");
        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {

                //acts as open square
                if(squares[i][j].getNum() == emptyValue){
                    canvas.drawRect(
                        squares[i][j].getLeft(),
                        squares[i][j].getTop(),
                        squares[i][j].getRight(),
                        squares[i][j].getBottom(),
                        openColor
                    );
                } else {

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

                }

            }
        }

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        int temp;
        //Log.i("Testing","ont");
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){


            for(int i = 0; i < gridSize; i++) {
                for(int j = 0; j < gridSize; j++) {

                    if(x > squares[i][j].getLeft() && x < squares[i][j].getRight() &&
                            y > squares[i][j].getTop() && y < squares[i][j].getBottom()){

                        Log.i("Testing","tap" + i + " " + j + " ");

                        //if square is open, do nothing
                        if(squares[i][j].getNum() == emptyValue){
                            return false;
                        }

                        //square is closed, check if near open: above
                        if(i - 1 >= 0) {
                            if (squares[i - 1][j].getNum() == emptyValue) {
                                //above is open, move
                                temp = squares[i][j].getNum();
                                squares[i][j].setNum(emptyValue);
                                squares[i - 1][j].setNum(temp);

                                invalidate();
                                return true;
                            }
                        }

                        //check right
                        if(j + 1 < gridSize){
                            if(squares[i][j + 1].getNum() == emptyValue){
                                //above is open, move
                                temp = squares[i][j].getNum();
                                squares[i][j].setNum(emptyValue);
                                squares[i][j + 1].setNum(temp);

                                invalidate();
                                return true;
                            }
                        }

                        //below
                        if(i + 1 < gridSize) {
                            if (squares[i + 1][j].getNum() == emptyValue) {
                                //above is open, move
                                temp = squares[i][j].getNum();
                                squares[i][j].setNum(emptyValue);
                                squares[i + 1][j].setNum(temp);

                                invalidate();
                                return true;
                            }
                        }

                        //check left
                        if(j - 1 >= 0){
                            if(squares[i][j - 1].getNum() == emptyValue){
                                //above is open, move
                                temp = squares[i][j].getNum();
                                squares[i][j].setNum(emptyValue);
                                squares[i][j - 1].setNum(temp);

                                invalidate();
                                return true;
                            }
                        }

                    }
                }
            }
        }
        return false;
    }
}
