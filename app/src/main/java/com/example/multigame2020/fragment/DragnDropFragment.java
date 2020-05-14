package com.example.multigame2020.fragment;

import android.content.ClipData;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.multigame2020.R;

import java.util.Timer;

public class DragnDropFragment extends Fragment {


    final static int UP_LEFT = 0;
    final static int UP_RIGHT = 1;
    final static int CENTER_LEFT = 2;
    final static int CENTER_RIGHT = 3;
    final static int DOWN_LEFT = 4;
    final static int DOWN_RIGHT = 5;

    private TextView topLeft;
    private TextView topRight;
    private TextView middleLeft;
    private TextView middleRight;
    private TextView bottomLeft;
    private TextView bottomRight;

    private int score = -1;
    private TextView scoreTv;
    private int dragZone = 0;
    private FrameLayout circle;

    private Timer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag_n_drop, container, false);
        scoreTv = view.findViewById(R.id.drag_score);
        circle = view.findViewById(R.id.drag_target);
        topLeft = view.findViewById(R.id.drag_frame_top_left);
        topRight = view.findViewById(R.id.drag_frame_top_right);
        middleLeft = view.findViewById(R.id.drag_frame_center_left);
        middleRight = view.findViewById(R.id.drag_frame_center_right);
        bottomLeft = view.findViewById(R.id.drag_frame_bottom_left);
        bottomRight = view.findViewById(R.id.drag_frame_bottom_right);

        setAction();

        circle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);
                    //    view.setVisibility(View.INVISIBLE);
                    return true;
                }
                return true;
            }
        });

        topLeft.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == UP_LEFT) {
                    setAction();
                }
                return true;
            }
        });

        topRight.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == UP_RIGHT) {
                    setAction();
                }
                return true;
            }
        });

        middleLeft.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == CENTER_LEFT) {
                    setAction();
                }
                return true;
            }
        });

        middleRight.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == CENTER_RIGHT) {
                    setAction();
                }
                return true;
            }
        });

        bottomLeft.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == DOWN_LEFT) {
                    setAction();
                }
                return true;
            }
        });

        bottomRight.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == DOWN_RIGHT) {
                    setAction();
                }
                return true;
            }
        });
        return view;
    }

    private void setAction() {
        score++;
        scoreTv.setText(getString(R.string.score, score));
        if (Math.random() < 0.16) {
            dragZone = UP_LEFT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_OVER);
        } else if (Math.random() < 0.32) {
            dragZone = UP_RIGHT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_OVER);
        } else if (Math.random() < 0.48) {
            dragZone = CENTER_LEFT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_OVER);
        } else if (Math.random() < 0.64) {
            dragZone = CENTER_RIGHT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_OVER);
        } else if (Math.random() < 0.80) {
            dragZone = DOWN_LEFT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_OVER);
        } else {
            dragZone = DOWN_RIGHT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_OVER);
        }
    }
}