package br.edu.ifsc.edson;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimplePaint extends View {
    List<Paint> mPaintList;
    List<Path> mPathList;
    Paint currentPaint;
    Path currentPath;

    ColorDrawable currentColor;

    public enum DrawMode {
        LINE,
        RECTANGLE,
        CIRCLE
    }

    private DrawMode currentMode = DrawMode.LINE;
    private float startX, startY,lastX, lastY;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintList = new ArrayList<Paint>();
        mPathList = new ArrayList<Path>();

        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);
        initLayerDraw();
    }

    public void initLayerDraw(){
        //configurando paint
        currentPaint = new Paint();
        currentPath = new Path();

        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(20);
        currentPaint.setColor(currentColor.getColor());
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i<mPaintList.size(); i++) {
            canvas.drawPath(mPathList.get(i), mPaintList.get(i));
        }

        if(currentMode == DrawMode.RECTANGLE){
            canvas.drawRect(startX, startY, lastX, lastY, currentPaint);
        }else if(currentMode == DrawMode.CIRCLE){
            float radius = (float) Math.sqrt(Math.pow(lastX-startX,2)+Math.pow(lastY-startY,2));
            canvas.drawCircle(startX, startY, radius, currentPaint);
        } else {
            canvas.drawPath(currentPath,currentPaint);
        }

        //canvas.drawPath(currentPath,currentPaint);
    }

    public void setDrawMode(DrawMode mode){
        this.currentMode = mode;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ly, lx;

        lx = event.getX();
        ly = event.getY();
        switch (event.getAction()){
            case (MotionEvent.ACTION_DOWN):
                startX = lx;
                startY = ly;

                currentPath = new Path();
                if(currentMode == DrawMode.LINE){
                    currentPath.moveTo(lx,ly);
                }
                break;

            case (MotionEvent.ACTION_MOVE):
                if (currentMode == DrawMode.LINE) {
                    currentPath.lineTo(lx, ly);
                }
                lastX = lx;
                lastY = ly;
                break;

            case (MotionEvent.ACTION_UP):
                if(currentMode == DrawMode.RECTANGLE){
                    currentPath.addRect(startX,startY,lx,ly, Path.Direction.CW);
                } else if(currentMode == DrawMode.CIRCLE){
                    float radius = (float) Math.sqrt(Math.pow(lx-startX,2)+Math.pow(ly-startY,2) + Math.pow(ly - startY, 2));
                    currentPath.addCircle(startX, startY, radius, Path.Direction.CW);
                }

                mPaintList.add(currentPaint);
                mPathList.add(currentPath);

                initLayerDraw();
                break;

            default:
                break;
        }
        invalidate();
        return true;
    }

    public void setColor(Color color) {
        currentColor.setColor(color.toArgb());
        currentPaint.setColor(color.toArgb());
    }
}
