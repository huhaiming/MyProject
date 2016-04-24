package com.huhaiming.newsproject.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;


import com.huhaiming.newsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by congtaowang on 16/3/11.
 */
public class NavBar extends LinearLayout {

    @Bind(R.id.homePageIcon)
    CheckedTextView homePageIcon;
    @Bind(R.id.homePageText)
    CheckedTextView homePageText;
    @Bind(R.id.homePage)
    LinearLayout homePage;
    @Bind(R.id.teacherIcon)
    CheckedTextView teacherIcon;
    @Bind(R.id.teacherText)
    CheckedTextView teacherText;
    @Bind(R.id.teacher)
    LinearLayout teacher;
    @Bind(R.id.timeTableIcon)
    CheckedTextView timeTableIcon;
    @Bind(R.id.timeTableText)
    CheckedTextView timeTableText;
    @Bind(R.id.timeTable)
    LinearLayout timeTable;
    @Bind(R.id.mineIcon)
    CheckedTextView mineIcon;
    @Bind(R.id.mineText)
    CheckedTextView mineText;
    @Bind(R.id.mine)
    LinearLayout mine;

    private OnNavBarActionListener listener;

    private int lastClickedId = R.id.homePage;

    public NavBar(Context context) {
        super(context, null);
    }

    public NavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_navbar, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        homePage.setOnClickListener(clickListener);
        teacher.setOnClickListener(clickListener);
        timeTable.setOnClickListener(clickListener);
        mine.setOnClickListener(clickListener);

        homePage.setOnTouchListener(touchListener);
        teacher.setOnTouchListener(touchListener);
        timeTable.setOnTouchListener(touchListener);
        mine.setOnTouchListener(touchListener);
    }

    private boolean isStillKeep = false;

    private OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (!isStillKeep) {
                        isStillKeep = true;
                        setButtonsClickableStatus(v.getId(), false);
                    }
                    return false;
                case MotionEvent.ACTION_UP:
                    if (isStillKeep) {
                        isStillKeep = false;
                        setButtonsClickableStatus(-1, true);
                    }
                    return false;
            }
            return false;
        }
    };

    /**
     * @param clickableId View's id which is still clickable.
     * @param clickable   Views' clickable status.
     */
    private void setButtonsClickableStatus(int clickableId, boolean clickable) {
        homePage.setClickable(clickable);
        teacher.setClickable(clickable);
        timeTable.setClickable(clickable);
        mine.setClickable(clickable);
        switch (clickableId) {
            case R.id.homePage:
                homePage.setClickable(true);
                break;
            case R.id.teacher:
                teacher.setClickable(true);
                break;
            case R.id.timeTable:
                timeTable.setClickable(true);
                break;
            case R.id.mine:
                mine.setClickable(true);
                break;
        }
    }


    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener == null) {
                return;
            }
            if (lastClickedId == v.getId()) {
                return;
            }
            unCheckAllButtons();
            switch (v.getId()) {
                case R.id.homePage:
                    listener.onHomePage();
                    break;
                case R.id.teacher:
                    listener.onTeacher();
                    break;
                case R.id.timeTable:
                    listener.onTimeTable();
                    break;
                case R.id.mine:
                    listener.onMine();
                    break;
            }
            checkButton(v.getId());
            lastClickedId = v.getId();
        }
    };

    private void unCheckAllButtons() {
        homePageText.setChecked(false);
        homePageIcon.setChecked(false);
        teacherIcon.setChecked(false);
        teacherText.setChecked(false);
        timeTableText.setChecked(false);
        timeTableIcon.setChecked(false);
        mineText.setChecked(false);
        mineIcon.setChecked(false);
    }

    private void checkButton(int id) {
        switch (id) {
            case R.id.homePage:
                homePageText.setChecked(true);
                homePageIcon.setChecked(true);
                break;
            case R.id.teacher:
//                timeTableIcon.setChecked(true);
//                timeTableText.setChecked(true);
                teacherIcon.setChecked(true);
                teacherText.setChecked(true);
                break;
            case R.id.timeTable:
//                categoryIcon.setChecked(true);
//                categoryText.setChecked(true);
                timeTableIcon.setChecked(true);
                timeTableText.setChecked(true);
                break;
            case R.id.mine:
                mineText.setChecked(true);
                mineIcon.setChecked(true);
                break;
        }
    }

    /**
     * Auto post {@link OnNavBarActionListener#onHomePage()} event if listener is not null.
     *
     * @param listener OnNavBarActionListener instance.
     */
    public void setOnNavBarActionListener(OnNavBarActionListener listener) {
        this.listener = listener;
        autoPostHomePageActionAfterListenerAttached();
    }

    private void autoPostHomePageActionAfterListenerAttached() {
        if (listener != null) {
            checkButton(R.id.homePage);
            listener.onHomePage();
        }
    }

    public interface OnNavBarActionListener {

        void onHomePage();

        void onTeacher();

        void onTimeTable();

        void onMine();
    }
}
