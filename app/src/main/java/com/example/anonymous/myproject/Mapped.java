package com.example.anonymous.myproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Anonymous on 20.02.2017.
 */

public class Mapped extends View {

    public Mapped(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);




            for (int i = 1; i < 31; i++) {
                for (int j = 1; j < 31; j++) {
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    switch (Path.myWorld.map[i][j]) {
                        case 1: {
                            paint.setColor(Color.GRAY);
                            canvas.drawRect((i - 1) * getWidth() / 30, (j - 1) * getHeight() / 30, +i * getWidth() / 30, j * getHeight() / 30, paint);
                            break;
                        }
                        case 2: {
                            paint.setColor(Color.YELLOW);
                            canvas.drawRect((i - 1) * getWidth() / 30, (j - 1) * getHeight() / 30, +i * getWidth() / 30, j * getHeight() / 30, paint);
                            break;
                        }
                        case 3: {
                            paint.setColor(Color.GREEN);
                            canvas.drawRect((i - 1) * getWidth() / 30, (j - 1) * getHeight() / 30, +i * getWidth() / 30, j * getHeight() / 30, paint);
                            break;
                        }
                        case 4: {
                            paint.setColor(Color.WHITE);
                            canvas.drawRect((i - 1) * getWidth() / 30, (j - 1) * getHeight() / 30, +i * getWidth() / 30, j * getHeight() / 30, paint);
                            break;
                        }
                    }
                }
            }
                        for (int i = 1; i < 31; i++) {
                            for (int j = 1; j < 31; j++) {
                                switch (Path.myWorld.map[i][j]) {
                                    case 5: {
                                        paint.setColor(Color.BLACK);
                                        canvas.drawCircle((float) ((i - 1) * getWidth() / 30 + 0.5 * getWidth() / 30), (float) ((j - 1) * getHeight() / 30 + 0.5 * getHeight() / 30), getHeight() / 45, paint);
                                        break;
                                    }
                                    case 6: {
                                        paint.setColor(getResources().getColor(R.color.gold));
                                        canvas.drawCircle((float) ((i - 1) * getWidth() / 30 + 0.5 * getWidth() / 30), (float) ((j - 1) * getHeight() / 30 + 0.5 * getHeight() / 30), getHeight() / 45, paint);
                                        break;
                                    }
                                    case 7: {
                                        paint.setColor(getResources().getColor(R.color.aquamarine));
                                        canvas.drawCircle((float) ((i - 1) * getWidth() / 30 + 0.5 * getWidth() / 30), (float) ((j - 1) * getHeight() / 30 + 0.5 * getHeight() / 30), getHeight() / 45, paint);
                                        break;
                                    }
                                    case 8: {
                                        paint.setColor(getResources().getColor(R.color.violet));
                                        canvas.drawCircle((float) ((i - 1) * getWidth() / 30 + 0.5 * getWidth() / 30), (float) ((j - 1) * getHeight() / 30 + 0.5 * getHeight() / 30), getHeight() / 45, paint);
                                        break;
                                    }
                                    case 9: {
                                        paint.setColor(getResources().getColor(R.color.blueviolet));
                                        canvas.drawCircle((float) ((i - 1) * getWidth() / 30 + 0.5 * getWidth() / 30), (float) ((j - 1) * getHeight() / 30 + 0.5 * getHeight() / 30), getHeight() / 45, paint);
                                        break;
                                    }
                                }
                            }
                        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        canvas.drawCircle((float) ((Path.king.y - 1) * getWidth() / 30 + 0.5 * getWidth() / 30), (float) ((Path.king.x - 1) * getHeight() / 30 + 0.5 * getHeight() / 30), getHeight() / 45, paint);
            }
}

