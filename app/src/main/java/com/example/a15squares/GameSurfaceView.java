package com.example.a15squares;

/**
 * @author Sebastian Santos-Mendoza
 * @version 9/26/2022
 *
 * ENHANCEMENTS: Grid Size is changeable using seekbar
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;

import java.util.Random;

public class GameSurfaceView extends SurfaceView implements View.OnTouchListener,
        View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    //Randomizer
    private Random rand = new Random();

    //Array of squares for number housing
    private Square squares[][];

    //size of grid
    private int gridSize;

    //amount of space between each square
    private int space;

    //value represents 1) largest number (not visible in game) and 2) empty square to move into
    private int emptyValue;

    //numbers array for keeping track of what numbers are already in use (for rng)
    private int numbers[];

    //initial square placement
    private int initTop;
    private int initLeft;
    private int initBottom;
    private int initRight;

    //square size
    private int size;

    //colors
    private Paint closedColor;
    private Paint openColor;
    private Paint textColor;
    private Paint correctColor;

    //seekbar for

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        gridSize = 4;
        init();
    }

    protected void onDraw(Canvas canvas) {

        //Log.d("Testing","draw ");

        //draw every square
        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {

                //open square gets colored differently
                if(squares[i][j].getNum() == emptyValue){
                    canvas.drawRect(
                        squares[i][j].getLeft(),
                        squares[i][j].getTop(),
                        squares[i][j].getRight(),
                        squares[i][j].getBottom(),
                        openColor
                    );

                } else {  //closed square, draws number in addition to color

                    canvas.drawRect(
                            squares[i][j].getLeft(),
                            squares[i][j].getTop(),
                            squares[i][j].getRight(),
                            squares[i][j].getBottom(),
                            closedColor);

                    canvas.drawText("" + squares[i][j].getNum(),
                            squares[i][j].getRight() - (squares[i][j].getRight() - squares[i][j].getLeft())/2,
                            squares[i][j].getBottom() - (squares[i][j].getBottom() - squares[i][j].getTop())/4,
                            textColor);

                }

                //if in correct square, color accordingly
                if((squares[i][j].getNum() == ((j + 1) + (gridSize * i))) && squares[i][j].getNum() != emptyValue){
                    canvas.drawRect(
                            squares[i][j].getLeft(),
                            squares[i][j].getTop(),
                            squares[i][j].getRight(),
                            squares[i][j].getBottom(),
                            correctColor
                    );

                    canvas.drawText("" + squares[i][j].getNum(),
                            squares[i][j].getRight() - (squares[i][j].getRight() - squares[i][j].getLeft())/2,
                            squares[i][j].getBottom() - (squares[i][j].getBottom() - squares[i][j].getTop())/4,
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

            //checks which square was just tapped
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

    @Override
    public void onClick(View view) {
        //this onClick acts as a reset
        Log.d("Testing","click");

        init();
        invalidate();
    }

    //initialization method
    private void init(){

        //initialization
        emptyValue = gridSize*gridSize;
        numbers = new int[emptyValue];

        //arbitrary values
        space = 5;

        size = 880/gridSize;
        initTop = 20;
        initLeft = 20;

        initBottom = initTop + size;
        initRight = initLeft + size;

        squares = new Square[gridSize][gridSize];

        //setting colors
        closedColor = new Paint();
        closedColor.setColor(Color.WHITE);

        openColor = new Paint();
        openColor.setColor(Color.DKGRAY);

        correctColor = new Paint();
        correctColor.setColor(Color.GREEN);

        //color for the numbers/text
        textColor = new Paint();
        textColor.setColor(Color.BLACK);
        textColor.setTextSize(400/gridSize);
        textColor.setTextAlign(Paint.Align.CENTER);

        //initialization
        for(int n : numbers){
            n = 0;
        }

        //assigns random numbers to each square
        int assign;
        for(int j = 0; j < gridSize; j++) {
            for(int i = 0; i < gridSize; i++) {

                //making sure number isn't already in use
                boolean pass;

                do{
                    pass = true;
                    assign = rand.nextInt(emptyValue) + 1;

                    for (int n : numbers) {
                        if (assign == n){
                            pass = false;
                        }
                    }
                } while (!pass);

                for(int k = 0; k < emptyValue; k++){
                    if(numbers[k] == 0){
                        numbers[k] = assign;
                        break;
                    }
                }

                //creates square
                squares[i][j] = new Square(
                        space + initLeft + (initRight - initLeft) * i,
                        space + initTop + (initBottom - initTop) * j,
                        initRight + (initRight - initLeft) * i,
                        initBottom + (initBottom - initTop) * j,
                        assign);

                //Log.d("Testing","init " + squares[i][j].getNum());
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

        gridSize = progress + 3;
        Log.d("Testing","progress:" + gridSize);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
