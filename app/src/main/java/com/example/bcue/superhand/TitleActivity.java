package com.example.bcue.superhand;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.R.id.content;
import static android.R.id.switch_widget;

public class TitleActivity extends AppCompatActivity {
    private float mPrevX;
    private float mPrevY;
    private boolean isActivityChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        View mScreen = findViewById(R.id.fullscreen_content);
        mScreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPrevX = event.getX();
                        mPrevY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float X = event.getX();
                        float Y = event.getY();
                        if (!isActivityChanged && ((mPrevX - X) * (mPrevX - X) + (mPrevY - Y) * (mPrevY - Y) > 180000f)) {
                            Intent intent = new Intent(TitleActivity.this, MainActivity.class);
                            isActivityChanged = true;
                            startActivity(intent);
                            finish();
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

}
