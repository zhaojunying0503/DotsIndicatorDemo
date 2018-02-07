package com.zhao.indicatorlibrary.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zhao.indicatorlibrary.R;

import java.util.ArrayList;

/**
 *
 * @author 狼
 * @date 2017/9/28
 * QQ 281788747 ;;
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 */

public class GuideInstructionView extends View{


    private RectF mRect;
    /**
     * 指示点的数量
     */
    private int mPointCount;
    private int mPointSize;
    private int mPointChoseColor;
    private int mPointNormalColor;
    private int mViewPadding;
    private Paint mCPointChosePaint;
    private Paint mCPointNormalPaint;
    private ArrayList<int[]> arrayList;
    private Path mPath;
    private PathMeasure mPathMeasure;
    /**
     * 线段的床都；；；
     */
    private float mPathLine = 0;

    public GuideInstructionView(Context context) {
        this(context,null);
    }

    public GuideInstructionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性；；；；；
        intiAttributes(context, attrs);
        initView();
    }







    private void intiAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GuideInstructionBar);
        mPointCount = a.getInteger(R.styleable.GuideInstructionBar_pointNumber, mPointCount);
        mPointSize = a.getInteger(R.styleable.GuideInstructionBar_pointSize, mPointSize);
        mViewPadding = a.getInteger(R.styleable.GuideInstructionBar_viewPadding, mViewPadding);
        mPointChoseColor = a.getColor(R.styleable.GuideInstructionBar_pointChoseColor, mPointChoseColor);
        mPointNormalColor = a.getColor(R.styleable.GuideInstructionBar_piontNormalColor, mPointNormalColor);
        a.recycle();
        //转dip转像素；；
        mPointSize = (int) dp2px(getResources(),mPointSize);
        mViewPadding = (int) dp2px(getResources(),mViewPadding);
    }

    private void initView() {
        //初始化画笔；；；
        mCPointChosePaint = new Paint();
        mCPointChosePaint.setAntiAlias(true);
        mCPointChosePaint.setColor(mPointChoseColor);

        mCPointNormalPaint = new Paint();
        mCPointNormalPaint.setAntiAlias(true);
        mCPointNormalPaint.setColor(mPointNormalColor);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateLocation();

        //初始化贝塞尔曲线；；；
        initQuad(canvas);
        //画点；；；
        drawNormalPoint(canvas);
        //画选中的点；；
        drawChosePoint(canvas);

    }


    /**
     * 画选中的点
     * @param canvas
     */
    private void drawChosePoint(Canvas canvas) {
        float[] mCurrentPosition = new float[2];
        mPathMeasure = new PathMeasure(mPath, false);
        mPathMeasure.getPosTan(mPathLine, mCurrentPosition, null);
        canvas.drawCircle(mCurrentPosition[0], mCurrentPosition[1], mPointSize, mCPointChosePaint);
    }

    /**
     * 设置进度；；；；；
     * @param postion
     * @param progress
     */
    public void setProgress(int postion,float progress){
        if(mPathMeasure!=null&&mPointCount>0){
            float length = mPathMeasure.getLength();
            float linePath = length / (mPointCount-1);
            mPathLine = postion * linePath + progress * linePath;
            postInvalidate();
        }
    }

    /**
     * 初始化贝塞尔曲线；；；；
     */
    private void initQuad(Canvas canves) {
        mPath = new Path();
        for(int i=0;i<arrayList.size();i++){
            if(i == 0){
                int[] ints = arrayList.get(i);
                mPath.moveTo(ints[0], ints[1]);
            }else{
                int[] ints = arrayList.get(i);
                int[] ints1 = arrayList.get(i - 1);
                //前两个是控制点，后两个是
                mPath.cubicTo(ints1[0],ints1[1],(ints[0]+ints1[0])/2,0 ,ints[0],
                        ints[1]);
            }
        }
    }

    /**
     * 绘画正常的点；；；；
     */
    private void drawNormalPoint(Canvas canvas) {
        for(int i =0 ;i<arrayList.size();i++){
            int[] ints = arrayList.get(i);
            canvas.drawCircle(ints[0], ints[1], mPointSize, mCPointNormalPaint);
        }
    }
    /**
     * 计算点的位置；；；；
     */
    private void calculateLocation() {
        //首先计算点的位置；；；
        arrayList = new ArrayList<>();
        //创建
        float halfHight = mRect.height() / 2;
        float pointLenght = 0;
        if(mPointCount>=1){
             pointLenght = (mRect.width()) /(mPointCount-1) ;
        }else{
            pointLenght = (mRect.width()) /(mPointCount) ;
        }
        float startX = mViewPadding;
        for(int i = 0;i<mPointCount;i++){
            int[] ponit = new int[] {
                    (int)startX, (int)(mViewPadding+halfHight)
            };
            startX+= pointLenght;
            arrayList.add(ponit);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(heightMeasureSpec));
    }
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            // Measure the text
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect = new RectF();
        mRect.top = getTop()+mViewPadding;
        mRect.left = getLeft()+mViewPadding;
        mRect.right = getLeft()+w-mViewPadding;
        mRect.bottom = getTop()+h-mViewPadding;
    }

    private float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }


}
