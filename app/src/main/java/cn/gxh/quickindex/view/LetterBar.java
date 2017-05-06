package cn.gxh.quickindex.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GXH on 2017/5/6.
 */
public class LetterBar extends View {

    /**
     * 索引字母颜色
     */
    private static final int LETTER_COLOR = 0xff595959;

    /**
     * 字母索引条背景颜色
     */
    private static final int BG_COLOR = 0xffB0B0B0;

    /**
     * 26个字母
     */
    public static final String[] LETTERS = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private float mCellHeight;//每个字母格的高度
    private int index;
    private TextView tvSelectedTextView;

    public LetterBar(Context context) {
        super(context);
    }

    public LetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(LETTER_COLOR);
        mPaint.setTextSize(dp2px(14));
        // 去锯齿，让字体边缘变得平滑
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];
            float x = mWidth / 2 - getTextWidth(letter) / 2;
            float y = mCellHeight / 2 + getTextHeight(letter) / 2 + mCellHeight * i;
            canvas.drawText(letter, x, y, mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCellHeight = (float) mHeight / LETTERS.length;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int tempIndex = -1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tempIndex = (int) (event.getY() / mCellHeight);
                if (tempIndex >= 0 && tempIndex < LETTERS.length) {

                    if (tempIndex != index) {
                        String letter = LETTERS[tempIndex];
                        index = tempIndex;

                        tvSelectedTextView.setText(letter);
                        tvSelectedTextView.setVisibility(VISIBLE);
                       if(onLetterChangedListener!=null){
                           onLetterChangedListener.onLetterChange(index,letter);
                       }
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                tempIndex = (int) (event.getY() / mCellHeight);
                if (tempIndex >= 0 && tempIndex < LETTERS.length) {
                    if (tempIndex != index) {
                        String letter = LETTERS[tempIndex];
                        index=tempIndex;

                        tvSelectedTextView.setText(letter);
                        tvSelectedTextView.setVisibility(VISIBLE);
                        if(onLetterChangedListener!=null){
                            onLetterChangedListener.onLetterChange(index,letter);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                tvSelectedTextView.setVisibility(INVISIBLE);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 获取字符宽度
     *
     * @param str
     * @return
     */
    private int getTextWidth(String str) {
        Rect bounds = new Rect();
        mPaint.getTextBounds(str, 0, str.length(), bounds);
        return bounds.width();
    }

    /**
     * 获取字符高度
     *
     * @param str
     * @return
     */
    private int getTextHeight(String str) {
        Rect bounds = new Rect();
        mPaint.getTextBounds(str, 0, str.length(), bounds);
        return bounds.height();
    }

    public int dp2px(int dp) {
        return (int) (getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    public void setSelectedTextView(TextView tvSelected) {
        tvSelectedTextView=tvSelected;
    }


    // 1. 定义监听器
    public interface OnLetterChangedListener {

        /**
         * 选中的字母改变了
         *
         * @param index 选中的字母的索引
         * @param letter 选中的字母
         */
        void onLetterChange(int index, String letter);
    }

    public  OnLetterChangedListener onLetterChangedListener;

    // 2. 提供设置监听器的set方法
    public void setOnLetterChangedListener(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

  /*  public void setLetterTextView(TextView tvSelectedLetter) {
        this.tvSelectedLetter = tvSelectedLetter;
    }*/
}
