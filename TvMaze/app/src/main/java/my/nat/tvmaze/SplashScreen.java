package my.nat.tvmaze;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tvmaze.R;
import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;
import my.nat.tvmaze.views.activities.HomeActivity;

public class SplashScreen extends AppCompatActivity {
    @BindView(R.id.appBar)
    AppBarLayout app_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_art);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TvMaze");
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);


    }
}
