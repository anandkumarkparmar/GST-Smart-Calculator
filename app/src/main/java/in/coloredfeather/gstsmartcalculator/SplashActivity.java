package in.coloredfeather.gstsmartcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anandparmar on 01/01/18.
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.wheel_1)
    ImageView wheel1;

    @BindView(R.id.wheel_2)
    ImageView wheel2;

    @BindView(R.id.wheel_3)
    ImageView wheel3;

    private final int SPLASH_DISPLAY_LENGHT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.rotate_around_center);
        wheel1.startAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.rotate_around_center_reverse);
        wheel2.startAnimation(animation2);

        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.rotate_around_center);
        wheel3.startAnimation(animation3);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, GSTCalcActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
