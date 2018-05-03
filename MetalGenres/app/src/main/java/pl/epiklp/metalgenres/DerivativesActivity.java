package pl.epiklp.metalgenres;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by epiklp on 03.05.18.
 */

public class DerivativesActivity extends AppCompatActivity {

    private ViewPager vp;
    private SlideAdapter sa;
    private LinearLayout mLinearLayout;
    private TextView[] mDots;

    public DerivativesActivity(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        vp = findViewById(R.id.viewPager);
        sa = new SlideAdapter(this, EpikLP.tmp);
        vp.setAdapter(sa);
        mLinearLayout = findViewById(R.id.linearLayout);
        addDots(0);
        vp.addOnPageChangeListener(viewListener);
    }

    public void addDots(int pos){
        mDots = new TextView[sa.getCount()];
        mLinearLayout.removeAllViews();
        for(short i = 0; i < sa.getCount(); ++i){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(20);
            mDots[i].setTextColor(getResources().getColor(R.color.Black));
            mLinearLayout.addView(mDots[i]);
        }
        mDots[pos].setTextColor(getResources().getColor(R.color.White));
    }



    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
