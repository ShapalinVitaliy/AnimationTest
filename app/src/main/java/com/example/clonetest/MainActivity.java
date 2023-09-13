package com.example.clonetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        ConstraintLayout el1 = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.fragment_elem, null);
        TextView text = el1.findViewById(R.id.testing);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(0, margin, 0, margin);
        el1.setLayoutParams(params);


        text.setText("Element 1");


        ConstraintLayout el2 = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.fragment_elem, null);
        text = el2.findViewById(R.id.testing);
        text.setText("Element 2");
        el2.setLayoutParams(params);

        ConstraintLayout el3 = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.fragment_elem, null);
        text = el3.findViewById(R.id.testing);
        text.setText("Element 3");
        el3.setLayoutParams(params);

        ConstraintLayout el4 = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.fragment_elem, null);
        text = el4.findViewById(R.id.testing);
        text.setText("Element 4");
        el4.setLayoutParams(params);

        ConstraintLayout el5 = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.fragment_elem, null);
        text = el5.findViewById(R.id.testing);
        text.setText("Element 5");
        el5.setLayoutParams(params);

        LinearLayout linear = findViewById(R.id.linear);

        linear.addView(el1);
        linear.addView(el2);
        linear.addView(el3);
        linear.addView(el4);
        linear.addView(el5);



        //el3.callOnClick();

        //el3.setOnClickListener(v -> {


        //} );



        el2.setOnClickListener(v -> {

            el3Click(el3);

            if (el3.getVisibility() == View.INVISIBLE) {return;}
            el3.setVisibility(View.INVISIBLE);

            ConstraintLayout main = findViewById(R.id.main);
            ConstraintLayout elXchng = (ConstraintLayout) main.getChildAt(1);

            int[] w = new int[2];
            el3.getLocationInWindow(w);
            ValueAnimator valueAnimator = ValueAnimator.ofInt(w[1]-283, 250);
            valueAnimator.setDuration(500);
            valueAnimator
                    .addUpdateListener(animation -> {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(main);
                        constraintSet.connect(elXchng.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                        constraintSet.connect(elXchng.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
                        constraintSet.connect(elXchng.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                        constraintSet.setMargin(elXchng.getId(), ConstraintSet.LEFT, 0);//q[0]);
                        constraintSet.setMargin(elXchng.getId(), ConstraintSet.TOP,  (int)animation.getAnimatedValue());
                        constraintSet.applyTo(main);
                        //animation.setInterpolator(new AccelerateInterpolator());ConstraintLayout.LayoutParams param =  (ConstraintLayout.LayoutParams) elXchng.getLayoutParams();
                        }
                    });
            valueAnimator.start();

            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    Toast.makeText(MainActivity.this, "Animation is done", Toast.LENGTH_SHORT).show();
                    super.onAnimationEnd(animation);
                }
            });

            valueAnimator = ValueAnimator.ofInt(el3.getWidth(), el3.getWidth()+30);
            valueAnimator.setDuration(500);
            valueAnimator
                    .addUpdateListener(animation -> {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                            ConstraintLayout.LayoutParams param =  (ConstraintLayout.LayoutParams) elXchng.getLayoutParams();
                            param.width = (int)animation.getAnimatedValue();
                        }
                    });
            valueAnimator.start();


            el3.setVisibility(View.INVISIBLE);

        });






        el1.setOnClickListener(v -> {

            if (el3.getVisibility() == View.VISIBLE) {return;}

            ConstraintLayout main = findViewById(R.id.main);
            ConstraintLayout elXchng = (ConstraintLayout) main.getChildAt(1);

            int[] w = new int[2];
            el3.getLocationInWindow(w);
            ValueAnimator valueAnimator = ValueAnimator.ofInt(250, w[1]-283);
            valueAnimator.setDuration(500);
            valueAnimator
                    .addUpdateListener(animation -> {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                            ConstraintSet constraintSet = new ConstraintSet();
                            constraintSet.clone(main);
                            constraintSet.connect(elXchng.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                            constraintSet.connect(elXchng.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
                            constraintSet.connect(elXchng.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                            constraintSet.setMargin(elXchng.getId(), ConstraintSet.LEFT, 0);//q[0]);
                            constraintSet.setMargin(elXchng.getId(), ConstraintSet.TOP,  (int)animation.getAnimatedValue());
                            constraintSet.applyTo(main);
                            //animation.setInterpolator(new AccelerateInterpolator());ConstraintLayout.LayoutParams param =  (ConstraintLayout.LayoutParams) elXchng.getLayoutParams();
                        }
                    });
            valueAnimator.start();

            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    Toast.makeText(MainActivity.this, "Animation is done", Toast.LENGTH_SHORT).show();
                    el3.setVisibility(View.VISIBLE);
                    super.onAnimationEnd(animation);
                }
            });

            valueAnimator = ValueAnimator.ofInt(elXchng.getWidth(), elXchng.getWidth()-30);
            valueAnimator.setDuration(500);
            valueAnimator
                    .addUpdateListener(animation -> {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                            ConstraintLayout.LayoutParams param =  (ConstraintLayout.LayoutParams) elXchng.getLayoutParams();
                            param.width = (int)animation.getAnimatedValue();
                        }
                    });
            valueAnimator.start();

            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                    el3.setVisibility(View.VISIBLE);

                    super.onAnimationCancel(animation);
                }
            });


        });


    }

    void el3Click(View el3)
    {
        int [] q = new int [2];
        el3.getLocationInWindow(q);
        int e = 7;

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());



        ConstraintLayout elXchng = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.fragment_elem, null);
        ConstraintLayout main = findViewById(R.id.main);

        if (main.getChildCount() > 1) { return; }

        ((TextView)(elXchng.findViewById(R.id.testing))).setText("Element 3");
        //elXchng.setBackground(new ColorDrawable(getColor(R.color.exchng)));

        ConstraintLayout.LayoutParams qwe = new ConstraintLayout.LayoutParams(width, height);
        //qwe.setMargins(q[0], q[1]-283, 0, 0);

        elXchng.setLayoutParams(qwe);

        main.addView(elXchng);


        elXchng.setId(View.generateViewId());
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(main);
        constraintSet.connect(elXchng.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSet.connect(elXchng.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.setMargin(elXchng.getId(), ConstraintSet.LEFT, q[0]);//q[0]);
        constraintSet.setMargin(elXchng.getId(), ConstraintSet.TOP, q[1]-283);
        constraintSet.applyTo(main);
    }
}