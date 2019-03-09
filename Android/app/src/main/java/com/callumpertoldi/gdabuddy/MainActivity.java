package com.callumpertoldi.gdabuddy;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    String TAG = "Main: ";

    User Operator = new User();
    Bundle data;

    ProgressBar KCalProgressBar;
    ProgressBar FatsProgressBar;
    ProgressBar SaturatesProgressBar;
    ProgressBar SugarsProgressBar;
    ProgressBar SaltsProgressBar;

    TextView GreetTV;
    TextView KCalTV;
    TextView FatsTV;
    TextView SaturatesTV;
    TextView SugarsTV;
    TextView SaltsTV;

    String FileName = "UserData";


    public void SaveFile()
    {
        try
        {
            FileOutputStream fos = openFileOutput(FileName, Context.MODE_PRIVATE);
            fos.write(Operator.getBytes());
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            fos.close();
        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Save Error", Toast.LENGTH_LONG).show();
            Log.d("ERROR: ", "Unable to save file!");
        }
    }

    private void ResetUserData()
    {
        Operator.Reset();
        deleteFile("UserData");
        deleteFile("FoodList");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Log.d("Main: ", "Loading Bundle");
        data = getIntent().getExtras();
        Log.d("Main: ", "Created Bundle");
        Operator = data.getParcelable("User");
        Log.d("Main: ", "Created Operator");

        Log.d(TAG, Operator.getSsName());

        KCalProgressBar = (ProgressBar) findViewById(R.id.kCalProgressBar);
        FatsProgressBar = (ProgressBar) findViewById(R.id.fatsProgressBar);
        SaturatesProgressBar = (ProgressBar) findViewById(R.id.saturatesProgressBar);
        SugarsProgressBar = (ProgressBar) findViewById(R.id.sugarsProgressBar);
        SaltsProgressBar = (ProgressBar) findViewById(R.id.saltsProgressBar);

        TextView GreetTV = (TextView) findViewById(R.id.welcomeTV);
        KCalTV = (TextView) findViewById(R.id.kCalTV);
        FatsTV = (TextView) findViewById(R.id.fatsTV);
        SaturatesTV = (TextView) findViewById(R.id.saturatesTV);
        SugarsTV = (TextView) findViewById(R.id.sugarsTV);
        SaltsTV = (TextView) findViewById(R.id.saltsTV);


        GreetTV.setText(Operator.Greeting());



        UpdateInformation();
        Log.d(TAG, "First run of Update");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_History:
                    //setContentView(R.layout.activity_main);

                    // <DEBUGGING TEST>

                    ResetUserData();
                    UpdateInformation();

                    Intent resetIntent = new Intent(MainActivity.this, Welcome.class);
                    startActivity(resetIntent);
                    finish();

                    // </DEBUGGING TEST>

                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_Items:
                    Intent foodListIntent = new Intent(MainActivity.this, FoodList.class);
                    foodListIntent.putExtra("User", Operator);
                    startActivityForResult(foodListIntent, 1);
                    return true;
            }
            return false;
        }

    };

    public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            SaveFile();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent a_Intent)
    {
        Log.d(TAG, "Accessed Result");
        super.onActivityResult(requestCode, resultCode, a_Intent);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {

                data = a_Intent.getExtras();
                Operator = data.getParcelable("User");
                BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
                navigation.setSelectedItemId(R.id.navigation_dashboard);
                navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

                UpdateInformation();
                Log.d(TAG, "Second Run");

            }
        }
    }

    private void UpdateInformation()
    {
        KCalProgressBar.setProgress(Math.round(Operator.GetKCalPercent()));
        Log.d(TAG, "Updated KCAl");
        FatsProgressBar.setProgress(Math.round(Operator.GetFatsPercent()));
        Log.d(TAG, "Updated Fats");
        SaturatesProgressBar.setProgress(Math.round(Operator.GetSaturatesPercent()));
        Log.d(TAG, "Updated Sats");
        SugarsProgressBar.setProgress(Math.round((Operator.GetSugarsPercent())));
        Log.d(TAG, "Updated Sugars");
        SaltsProgressBar.setProgress(Math.round(Operator.GetSaltsPercent()));
        Log.d(TAG, "Updated Salts");

        KCalTV.setText("" + Operator.GetKCalPercent() + "%");
        FatsTV.setText("" + Operator.GetFatsPercent() + "%");
        SaturatesTV.setText("" + Operator.GetSaturatesPercent() + "%");
        SugarsTV.setText("" + Operator.GetSugarsPercent() + "%");
        SaltsTV.setText("" + Operator.GetSaltsPercent() + "%");
    }



}
