package com.codespurt.googleanalytics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codespurt.googleanalytics.engine.MyApplication;
import com.codespurt.googleanalytics.fragments.FooterFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private Button btnSecondScreen, btnSendEvent, btnException, btnAppCrash, btnLoadFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        btnSecondScreen = (Button) findViewById(R.id.btnSecondScreen);
        btnSendEvent = (Button) findViewById(R.id.btnSendEvent);
        btnException = (Button) findViewById(R.id.btnException);
        btnAppCrash = (Button) findViewById(R.id.btnAppCrash);
        btnLoadFragment = (Button) findViewById(R.id.btnLoadFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Main Activity Local");

        btnSecondScreen.setOnClickListener(this);
        btnSendEvent.setOnClickListener(this);
        btnException.setOnClickListener(this);
        btnAppCrash.setOnClickListener(this);
        btnLoadFragment.setOnClickListener(this);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSecondScreen:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                Toast.makeText(this, getResources().getString(R.string.action_successful), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSendEvent:
                MyApplication.getInstance().trackEvent("Activity", "Button clicked", "Second Activity Opened");
                Toast.makeText(this, getResources().getString(R.string.action_successful), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnException:
                try {
                    String name = null;
                    if (name.equals("CodeSpurt")) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MyApplication.getInstance().trackException(e);
                }
                Toast.makeText(this, getResources().getString(R.string.action_successful), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnAppCrash:
                Runnable runnable = new Runnable() {

                    @Override
                    public void run() {
                        int answer = 12 / 0;
                    }
                };

                Handler handler = new Handler();
                handler.postDelayed(runnable, 2 * 1000);
                Toast.makeText(this, getResources().getString(R.string.action_successful), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLoadFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new FooterFragment()).commit();
                Toast.makeText(this, getResources().getString(R.string.action_successful), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        btnSecondScreen.setOnClickListener(null);
        btnSendEvent.setOnClickListener(null);
        btnException.setOnClickListener(null);
        btnAppCrash.setOnClickListener(null);
        btnLoadFragment.setOnClickListener(null);
    }
}
