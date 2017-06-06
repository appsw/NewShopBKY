package bai.kang.yun.zxd.mvp.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import bai.kang.yun.zxd.R;


public class StartActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View startView = View.inflate(this, R.layout.activity_start, null);
        setContentView(startView);
        //渐变
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        startView.setAnimation(aa);
        aa.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                 

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                 

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                 
                redirectto();
            }
        }
        );
    }

    private void redirectto() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
