package com.mtg.projectevaluator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (isFirstTime()) {
            SharedPreferences mPreferences = this.getSharedPreferences("mySharedPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString("password", "12345");
            editor.putInt("idea", 3);
            editor.putInt("design", 3);
            editor.putInt("extra", 3);
            editor.apply();
        }
    }

    public void login(View view) {      // onClick listener for the login button
        String password = ((EditText) findViewById(R.id.txtPwd)).getText().toString();
        // Toast.makeText(getApplicationContext(), password, Toast.LENGTH_SHORT).show(); //just for debug
        if (password.equals((this.getSharedPreferences("mySharedPref", Context.MODE_PRIVATE)).getString("password", "-1-1-1"))) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "\t\t\t\t\t\t\t\t\t\t\tInvalid Password\nPlease Enter Valid Password and Try Again", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isFirstTime() {     //   To check whether the app is run for the first time
        Boolean firstTime = null;
        //noinspection ConstantConditions
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("mySharedPref", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.apply();
            }
        }
        return firstTime;
    }
}
