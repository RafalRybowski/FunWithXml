package pl.epiklp.profile;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isOpen = false;
    private ImageView mImageView;

    private ConstraintSet layout1, layout2;
    private ConstraintLayout mConstraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        layout1 = new ConstraintSet();
        layout2 = new ConstraintSet();
        mConstraintLayout = findViewById(R.id.constrait_layout);
        layout1.clone(mConstraintLayout);
        layout2.clone(this, R.layout.extendet);

        mImageView = findViewById(R.id.imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen){
                    TransitionManager.beginDelayedTransition(mConstraintLayout);
                    layout2.applyTo(mConstraintLayout);
                    isOpen = !isOpen;
                } else {
                    TransitionManager.beginDelayedTransition(mConstraintLayout);
                    layout1.applyTo(mConstraintLayout);
                    isOpen = !isOpen;
                }
            }
        });
    }
}
