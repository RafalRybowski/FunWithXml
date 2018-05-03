package pl.epiklp.metalgenres;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by epiklp on 30.04.18.
 */

public class SlideAdapter extends PagerAdapter {
    Activity mContext;
    LayoutInflater mLayoutInflater;

    private ArrayList<Genre> mGenre;

    public SlideAdapter(Activity context, ArrayList<Genre> genres){
        mContext = context;
        mGenre = genres;

    }

    @Override
    public int getCount() {
        return mGenre.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.slide, container, false);
        //ConstraintLayout constraintLayout = view.findViewById(R.id.slide_constrain);

        TextView tv = view.findViewById(R.id.textGenre);
        TextView year = view.findViewById(R.id.textYear);
        TextView Describe = view.findViewById(R.id.Describe);
        tv.setText(mGenre.get(position).getName());
        year.setText(mGenre.get(position).getYear());
        Describe.setText(mGenre.get(position).getDescribe());

        Button button = view.findViewById(R.id.buttonDerivatives);
        if(mGenre.get(position).getmGenre() == null) {
            button.setVisibility(View.INVISIBLE);
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EpikLP.tmp = mGenre.get(position).getmGenre();
                    final Intent mIntent = new Intent(mContext, DerivativesActivity.class);
                    mContext.startActivity(mIntent);
                }
            });
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (ConstraintLayout)object);
    }
}
