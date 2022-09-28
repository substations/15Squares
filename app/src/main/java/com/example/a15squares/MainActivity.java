package com.example.a15squares;

/**
 * @author Sebastian Santos-Mendoza
 * @version 9/26/2022
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        GameSurfaceView gameSurfaceView = findViewById(R.id.gameBoard);
        gameSurfaceView.setOnTouchListener(gameSurfaceView);

        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(gameSurfaceView);

        SeekBar grid = findViewById(R.id.gridSeekBar);
        grid.setOnSeekBarChangeListener(gameSurfaceView);

    }


}