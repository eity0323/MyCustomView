package com.zms.mycustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/4/28.
 */
public class TopBar extends RelativeLayout {
    private Button btnLeft, btnRight;
    private TextView tvTitle;

    // attrs
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;
    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;
    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private LayoutParams leftParams, rightParams, titleParams;

    private TopBarOnClickListener listener;

    public interface TopBarOnClickListener{
        public void leftOnClick();
        public void rightOnClick();
    }

    public void setOnTopBarClickListener(TopBarOnClickListener listener){
        this.listener = listener;
    }

    public TopBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);


        leftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        leftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        leftText = typedArray.getString(R.styleable.TopBar_leftText);
        rightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        rightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        rightText = typedArray.getString(R.styleable.TopBar_rightText);
        titleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 0);
        titleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
        title = typedArray.getString(R.styleable.TopBar_mTitle);

        typedArray.recycle();

        btnLeft = new Button(context);
        btnRight = new Button(context);
        tvTitle = new TextView(context);

        btnLeft.setTextColor(leftTextColor);
        btnLeft.setBackground(leftBackground);
        btnLeft.setText(leftText);

        btnRight.setTextColor(rightTextColor);
        btnRight.setBackground(rightBackground);
        btnRight.setText(rightText);

        tvTitle.setTextSize(titleTextSize);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0xFF0099cc);

        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(btnLeft, leftParams);

        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(btnRight, rightParams);

        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tvTitle, titleParams);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftOnClick();
            }
        });

        btnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightOnClick();
            }
        });
    }

    public void setLeftVisibility(boolean flag) {
        if (flag)
            btnLeft.setVisibility(View.VISIBLE);
        else
            btnLeft.setVisibility(View.GONE);
    }

    public void setRightVisibility(boolean flag) {
        if (flag)
            btnRight.setVisibility(View.VISIBLE);
        else
            btnRight.setVisibility(View.GONE);
    }

}
