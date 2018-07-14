package com.example.ally.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

class MyLayout extends ConstraintLayout {
    public MyLayout(Context context, ConstraintLayout layout) {
        super(context);
        addView(layout);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        layout.setLayoutParams(layoutParams);
    }
}

public class MainActivity extends AppCompatActivity {
    int x = 0;
    int delta = 1;
    int limit;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        ConstraintLayout layout1 = (ConstraintLayout)LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
        root.addView(layout1);
        final ConstraintLayout layout2 = new MyLayout(this, (ConstraintLayout)LayoutInflater.from(this).inflate(R.layout.activity_main, null, false));
        SurfaceView surfaceView = new SurfaceView(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(500, 500);
        surfaceView.setLayoutParams(layoutParams);
//        surfaceView.setMinimumWidth(500);
//        surfaceView.setMinimumHeight(500);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                new Thread(new Runnable() {
                    public void run() {
                         ServiceInvokeHelper.startActionFoo(MainActivity.this, holder.getSurface());
//
//                        try {
//                            Thread.sleep(5000);
//                        } catch (Exception e) {
//
//                        }
//                        Canvas canvas = holder.getSurface().lockCanvas(null);
//                        Paint paint = new Paint();
//                        paint.setColor(Color.BLACK);
//                        paint.setStrokeWidth(100);
////                      canvas.drawRGB(0, 0, 0);
//                        canvas.drawLine(0, 0, 500, 500, paint);
//                        canvas.drawLine(0, 500, 500, 0, paint);
//                        holder.getSurface().unlockCanvasAndPost(canvas);
                    }
                }).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        root.addView(surfaceView);
        root.addView(layout2);
        setContentView(root);
        layout2.animate().translationY(1000).setDuration(10000).start();
        layout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setTranslationY(v.getTranslationY() + 100);
                return true;
            }
        });
//        layout2.callOnClick();
        layout2.setClickable(true);
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTranslationY(v.getTranslationY() + 50);
            }
        });
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                layout2.callOnClick();
//                handler.postDelayed(this, 1000);
//            }
//        }, 1000);

//        findViewById(R.id.text_view_id).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        x += delta * 10;
//                        v.invalidate();
//                        if (x != 0) {
//                            handler.postDelayed(this, 1);
//                        }
//                        if (x >= limit) {
//                            delta = -1;
//                        }
//                        else if(x <= 0) {
//                            delta = 1;
//                        }
//                    }
//                }, 1);
//            }
//        });
//        findViewById(R.id.text_view_id).setBackground(new Drawable() {
//            @Override
//            public void draw(@NonNull Canvas canvas) {
//                limit = canvas.getWidth();
//                Paint paint = new Paint();
//                paint.setStrokeWidth(10);
//                canvas.drawLine(x, 0, canvas.getWidth() - x, canvas.getHeight(), paint);
//            }
//
//            @Override
//            public void setAlpha(int alpha) {
//            }
//
//            @Override
//            public void setColorFilter(@Nullable ColorFilter colorFilter) {
//
//            }
//
//            @Override
//            public int getOpacity() {
//                return PixelFormat.TRANSLUCENT;
//            }
//        });

//        findViewById(R.id.text_view_id).setOnClickListener(
//            new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
//                    popupMenu.getMenu().add("Item 1");
//                    popupMenu.getMenu().add("Item 2");
//                    popupMenu.show();
//                }
//            }
//        );
    }
}
