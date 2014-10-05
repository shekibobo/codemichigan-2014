package com.codebits.codemichigan.michiganoutdoors;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Handler;

import com.codebits.codemichigan.michiganoutdoors.activities.MainActivity;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            /*
             * Show splash screen on a timer
             */
            @Override
            public void run(){
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);

                finish();
            }
        },SPLASH_TIME_OUT);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
