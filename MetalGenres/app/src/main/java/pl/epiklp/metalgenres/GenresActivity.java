package pl.epiklp.metalgenres;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.JsonReader;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by epiklp on 30.04.18.
 */

public class GenresActivity extends AppCompatActivity {

    private ViewPager vp;
    private SlideAdapter sa;
    private LinearLayout mLinearLayout;
    private TextView mDots[];


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        String json = loadJSON();
        ArrayList<Genre> root = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("genres");
            for(short i = 0; i < jsonArray.length(); ++i){
                ArrayList<Genre> mGenre = null;
                JSONObject jb = jsonArray.getJSONObject(i);
                String name = jb.getString("name");
                String describe = jb.getString("describe");
                String year = jb.getString("year");
                JSONArray Derivatives;
                if(!jb.isNull("Derivatives")) {
                    mGenre = new ArrayList<>();
                    Derivatives = jb.getJSONArray("Derivatives");
                    for(short j = 0; j < Derivatives.length(); ++j){
                        JSONObject DerivativesJb = Derivatives.getJSONObject(j);
                        String DerivativesName = DerivativesJb.getString("name");
                        String DerivativesDescribe = DerivativesJb.getString("describe");
                        String DerivativesYear = DerivativesJb.getString("year");
                        mGenre.add(new Genre(DerivativesName,DerivativesDescribe,DerivativesYear, null));
                    }
                }
                root.add(new Genre(name, describe, year, mGenre));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        vp = findViewById(R.id.viewPager);
        sa = new SlideAdapter(this, root);
        vp.setAdapter(sa);
        mLinearLayout = findViewById(R.id.linearLayout);
        addDots(0);
        vp.addOnPageChangeListener(viewListener);
    }

    public String loadJSON() {
        String json = null;
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.generated);
            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);

            inputStream.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
